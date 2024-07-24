package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.cards.Card;
import model.cards.SpellType;
import model.cards.Troop;
import model.field.Slot;
import view.UpdateMenu;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowSlotInPlaceController implements Initializable {

    @FXML private ImageView SlotBackground;
    @FXML private ImageView SlotImage;
    @FXML private Label SlotName;
    @FXML private AnchorPane AnchorPane ;
    @FXML private VBox VBox ;
    public Card card;
    private Slot slot ;
    public static GameFieldController gameFieldController ;
    public static ShowCardInDeckController showCardInDeckController ;


    @FXML
    void openCard(MouseEvent event) {
        CardMenuController.card = Card.cardHashMap.get(this.SlotName.getText()) ;
        UpdateMenu.toCardMenu();
    }

    @FXML
    void setOnDragDropped(DragEvent event) {
        Dragboard db = event.getDragboard();
        if (db.hasString()) {
            // Handle the dropped card data (e.g., update UI)
            this.card = Card.cardHashMap.get(db.getString());
            System.out.println("Card " + card.getName() + " dropped into slot!");
            //this.SlotImage.setImage(new Image(getClass().getResourceAsStream(card.image)));
            this.SlotImage.setImage(new Image(getClass().getResourceAsStream(card.image)));
            this.SlotBackground.setImage(new Image(getClass().getResourceAsStream(card.imageBackground)));
            this.SlotName.setText(card.getName());
            System.out.println("plan B");
            this.slot.card=card;
            this.slot.empty = false ;
            this.gameFieldController.updateStats();
        }
        event.setDropCompleted(true);
        event.consume();
    }

    @FXML
    void setOnDragOver(DragEvent event) {
        if (event.getGestureSource() != this && event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.MOVE);
        }
        event.consume();
    }

    public void createSlotBox(Slot slot, double prefHeight) {
        this.slot = slot ;
        double prefWidth = prefHeight * 9.0 / 16.0;
        double fontSize = prefHeight * 0.08; // Adjust this factor as needed
        Image imgBack = new Image(getClass().getResourceAsStream("/image/emptyBlock.jpeg"));
        if(slot.isEmpty()){
            imgBack = new Image(getClass().getResourceAsStream("/image/emptyBlock.jpeg"));
            SlotImage.setImage(null);
        } else {
            if(slot.getTroop()==null){
            } else {
            }
            SlotImage.setImage(new Image(getClass().getResourceAsStream(slot.getCard().image)));
            SlotName.setText(slot.getCard().getName());
        }
        SlotName.setFont(Font.font(fontSize));
        SlotName.setLayoutY(prefHeight * 110.0 / 320.0);
        SlotName.setPrefSize(prefWidth, prefHeight);
        SlotImage.setFitHeight(prefHeight);
        SlotImage.setFitWidth(prefWidth);
        SlotImage.setLayoutY(prefHeight * 50.0 / 320.0);
        SlotBackground.setImage(imgBack);
        SlotBackground.setFitHeight(prefHeight);
        SlotBackground.setFitWidth(prefWidth);
        AnchorPane.setPrefSize(prefWidth, prefHeight);
        VBox.setPrefSize(prefWidth, prefHeight);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SlotImage.setImage(null);
    }
}
