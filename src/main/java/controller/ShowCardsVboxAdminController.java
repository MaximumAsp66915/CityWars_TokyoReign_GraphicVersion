package controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import model.App;
import model.cards.Card;
import model.cards.CardType;
import model.cards.Spell;
import model.cards.Troop;
import view.AdminCardMenu;

import java.io.IOException;
import java.util.jar.Attributes;

public class ShowCardsVboxAdminController {

    @FXML public Label Name ;
    @FXML public ImageView CardBackground;
    @FXML public ImageView CardImage;
    @FXML public Label CardName;
    @FXML public TextField CostT;
    @FXML public Label Attack;
    @FXML public TextField AttackT;
    @FXML public Label Damage;
    @FXML public TextField DamageT;
    @FXML public Label Duration;
    @FXML public TextField DurationT;
    @FXML public TextField ImageBackgroundT;
    @FXML public TextField ImageT;
    @FXML public TextField NameT;
    @FXML public Label SpellType;
    @FXML public TextField SpellTypeT;
    @FXML public Label UpgradeCost;
    @FXML public TextField UpgradeCostT;
    @FXML public Label UpgradeLevel;
    @FXML public TextField UpgradeLevelT;
    @FXML public GridPane GridPane ;
    @FXML public AnchorPane AnchorPane ;
    @FXML public VBox VBox ;
    private boolean addCardHasBeenActivated = false ;
    public static boolean cardHasBeenSelected = false ;


    @FXML
    void addCard(MouseEvent event) {
        if(!addCardHasBeenActivated && CardName.getText().equals("Character")){
            addCardHasBeenActivated = true ;
            AnchorPane.getChildren().add(GridPane);
            AnchorPane.getChildren().add(CardImage);
            AnchorPane.getChildren().add(CardName);
            CardBackground.setImage(new Image(getClass().getResourceAsStream("/image/smoothBackground.jpeg")));
            CardImage.setImage(new Image(getClass().getResourceAsStream("/image/pinkBackground.png")));
        }
        AdminCardMenu.showCardsVboxAdminController = this ;
        cardHasBeenSelected = true;
    }

    public void createCardBox(Card card) {
        if (card == null) {
            CardBackground.setImage(new Image(getClass().getResourceAsStream("/image/addIcon.png")));
            AnchorPane.getChildren().remove(GridPane);
            AnchorPane.getChildren().remove(CardImage);
            AnchorPane.getChildren().remove(CardName);
        } else {
            NameT.setDisable(true);
            SpellTypeT.setDisable(true);
            if (card.cardType.equals(CardType.TROOP)) {
                Troop troop = (Troop) card;
                SpellType.setText("");
                AttackT.setPromptText(String.valueOf(troop.getAttack()));
                DamageT.setPromptText(String.valueOf(troop.getDamage()));
                DurationT.setPromptText(String.valueOf(troop.getDuration()));
                UpgradeLevelT.setPromptText(String.valueOf(troop.getUpgradeLevel()));
                UpgradeCostT.setPromptText(String.valueOf(troop.getUpgradeCost()));
            } else {
                NameT.setText("");
                Spell spell = (Spell) card;
                SpellTypeT.setPromptText(spell.spellType.name());
                Attack.setText("");
                AttackT.setDisable(true);
                Damage.setText("");
                DamageT.setDisable(true);
                Duration.setText("");
                DurationT.setDisable(true);
                UpgradeLevel.setText("");
                UpgradeLevelT.setDisable(true);
                UpgradeCost.setText("");
                UpgradeCostT.setDisable(true);
            }
            if(getClass().getResourceAsStream(card.image)!=null)
                CardImage.setImage(new Image(getClass().getResourceAsStream(card.image)));
            else
                CardImage.setImage(new Image(getClass().getResourceAsStream("/image/nullImage.jpeg")));
            if(getClass().getResourceAsStream(card.imageBackground)!=null)
                CardBackground.setImage(new Image(getClass().getResourceAsStream(card.imageBackground)));
            else
                CardBackground.setImage(new Image(getClass().getResourceAsStream("/image/nullBackgroundImage.jpeg")));
            CardName.setText(card.getName());
            NameT.setPromptText(String.valueOf(card.getName()));
            CostT.setPromptText(String.valueOf(card.getCost()));
            ImageT.setPromptText(card.image);
            ImageBackgroundT.setPromptText(card.imageBackground);
        }
    }

}
