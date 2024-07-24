package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.PopupWindow;
import model.App;
import model.cards.Card;
import view.CardMenu;
import view.UpdateMenu;

public class ShowCardsVboxController {

    @FXML private ImageView CardBackground;
    @FXML private ImageView CardImage;
    @FXML private Label CardName;
    @FXML private Label Owned;
    @FXML private AnchorPane AnchorPane ;
    @FXML private VBox VBox ;


    @FXML
    void openCard(MouseEvent event) {
        UpdateMenu.soundEffectPlay(DataController.button1);
        CardMenuController.card = Card.cardHashMap.get(this.CardName.getText()) ;
        UpdateMenu.toCardMenu();
    }

    public void createCardBox(Card card, double prefHeight, boolean showOwned) {
        Image img = new Image(getClass().getResourceAsStream(card.image));
        Image imgBack = new Image(getClass().getResourceAsStream(card.imageBackground));
        CardImage.setImage(img);
        CardBackground.setImage(imgBack);
        CardName.setText(card.getName());

        // Calculate the preferred width based on the 5:4 aspect ratio
        double prefWidth = prefHeight * 4.0 / 5.0;

        // Set the preferred height for CardImage and CardBackground
        CardImage.setFitHeight(prefHeight);
        CardImage.setFitWidth(prefWidth);
        CardBackground.setFitHeight(prefHeight);
        CardBackground.setFitWidth(prefWidth);
        double fontSize = prefHeight * 0.056; // Adjust this factor as needed
        CardName.setFont(Font.font(fontSize));
        CardName.setLayoutY(prefHeight* 1.8 / 5.0);
        CardName.setPrefHeight(prefHeight);
        CardName.setPrefWidth(prefWidth);
        Owned.setFont(Font.font(fontSize));
        Owned.setLayoutY(prefHeight* 2.2 / 5.0);
        Owned.setPrefHeight(prefHeight);
        Owned.setPrefWidth(prefWidth);

        AnchorPane.setPrefSize(prefWidth, prefHeight);
        VBox.setPrefSize(prefWidth , prefHeight);

        if (showOwned) {
            String owned = "Not-Owned";
            for (Card card1 : App.getLoggedInUser().cards.values()) {
                if (card1.getName().equals(card.getName())) {
                    owned = "Owned";
                    break;
                }
            }
            Owned.setText(owned);
        } else {
            Owned.setText("");
        }
    }
}
