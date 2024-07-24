package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.App;
import model.cards.Card;
import model.cards.CardType;
import model.cards.Troop;
import view.UpdateMenu;

public class ShowCardInDeckController {

    @FXML private ImageView CardBackground;
    @FXML private ImageView CardImage;
    @FXML private Label CardName;
    @FXML private Label Attack;
    @FXML private AnchorPane AnchorPane ;
    @FXML private VBox VBox ;


    @FXML
    void openCard(MouseEvent event) {
        CardMenuController.card = Card.cardHashMap.get(this.CardName.getText()) ;
        UpdateMenu.toCardMenu();
    }
    @FXML
    void setOnDragDetected(MouseEvent event) {
        Dragboard db = this.VBox.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        content.putString(this.CardName.getText()); // You can use any data here
        db.setContent(content);
        ShowSlotInPlaceController.showCardInDeckController = this ;
        event.consume();
    }

    public void createCardBox(Card card, double prefHeight) {
        Image img = new Image(getClass().getResourceAsStream(card.image));
        Image imgBack = new Image(getClass().getResourceAsStream(card.imageBackground));
        CardImage.setImage(img);
        CardBackground.setImage(imgBack);
        CardName.setText(card.getName());

        double prefWidth = prefHeight * 9.0 / 16.0;


        AnchorPane.setPrefSize(prefWidth, prefHeight);
        VBox.setPrefSize(prefWidth, prefHeight);

        CardImage.setFitHeight(prefHeight);
        CardImage.setFitWidth(prefWidth);
        CardImage.setLayoutY(prefHeight * 50.0 / 320.0);
        CardBackground.setFitHeight(prefHeight);
        CardBackground.setFitWidth(prefWidth);
        double fontSize = prefHeight * 0.08; // Adjust this factor as needed
        CardName.setFont(Font.font(fontSize));
        CardName.setLayoutY(prefHeight * 110.0 / 320.0);
        CardName.setPrefSize(prefWidth , prefHeight);
        System.out.println(Attack.getLayoutY());
        Attack.setFont(Font.font(fontSize));
        Attack.setLayoutY(-prefHeight * 130 / 320.0);
        Attack.setPrefSize(prefWidth , prefHeight);
        System.out.println(Attack.getLayoutY());
        if(card.cardType.equals(CardType.TROOP)) {
            Troop troop = (Troop)card ;
            Attack.setText("Attack : " + troop.getAttack());
        } else {
            Attack.setText("");
        }
    }
}
