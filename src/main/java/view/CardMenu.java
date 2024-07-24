package view;

import controller.*;
import enums.CardMenuCommands;
import enums.MainMenuCommands;
import enums.ShopMenuCommands;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import model.App;
import model.Result;
import model.cards.Card;
import model.cards.CardType;
import model.cards.Spell;
import model.cards.Troop;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;

public class CardMenu extends AppMenu implements Initializable {
    public static void main(String[] args) {
        //(new AppView()).run();
        launch(args);}

    @Override
    public void start(Stage stage) throws IOException {
        UpdateMenu.stage = stage;
        UpdateMenu.toCardMenu();
    }
    @FXML private Label Parameter1;
    @FXML private Label Parameter2;
    @FXML private Label Parameter3;
    @FXML private Label Parameter4;
    @FXML private Label Parameter5;
    @FXML private Label Parameter6;
    @FXML private Label Error;
    @FXML private Label Gold;
    @FXML private Label Level;
    @FXML private HBox CardContainer;
    @FXML private Button SetAsProfile ;

    @FXML
    void buy(ActionEvent event) {
        UpdateMenu.soundEffectPlay(DataController.button2);
        check("buy card");
        DataController.updateData();
    }

    @FXML
    void upgrade(ActionEvent event) {
        UpdateMenu.soundEffectPlay(DataController.button2);
        check("upgrade card");
        DataController.updateData();
    }

    @FXML
    void setAsProfile(ActionEvent event) {
        UpdateMenu.soundEffectPlay(DataController.button2);
        App.getLoggedInUser().setImage(CardMenuController.card.image);
        Error.setText("Your Profile has been changed");
    }
    private final CardMenuController controller = new CardMenuController();
    private static VBox vBox ;

    @Override
    public void check(String input) {
        input = StaticMethods.find(input , Arrays.asList("buy" , "upgrade", "back"));
        if (CardMenuCommands.BuyCard.getMatcher(input) != null) {
            Result result = controller.buyCard() ;
            Error.setText(result.getMessage());
            if(result.isSuccessful())
                SetAsProfile.setDisable(false);
            updateStats();
        } else if (CardMenuCommands.UpgradeCard.getMatcher(input) != null) {
            Error.setText(controller.upgradeCard().getMessage());
            updateStats();
        }



        else if (ShopMenuCommands.Back.getMatcher(input) != null) {
            System.out.println(controller.back());
        } else if (MainMenuCommands.Exit.getMatcher(input) != null) {
            controller.exit();
        } else {
            System.out.println("invalid command!");
        }
    }

    @Override
    public void MenuDetails() {
        Card card = CardMenuController.card;
        String output = "" ;
        if(card.cardType.equals(CardType.TROOP)){
            Troop troop = (Troop) card ;
            output+=(troop.getName() + " :\n");
            output+=("attack : " + troop.getAttack() + "\n");
            output+=("duration : " + troop.getDuration() + "\n");
            output+=("damage : " + troop.getDamage() + "\n");
            output+=("upgradeLevel : " + troop.getUpgradeLevel() + "\n");
            output+=("upgradeCost : " + troop.getUpgradeCost() + "\n");
            output+=("cost : " + troop.getCost() + "\n");
            output+=("\n");
        } else {
            Spell spell = (Spell) card ;
            output+=(spell.getSpellType().toString() + " :\n");
            output+=("cost : " + spell.getCost() + "\n");
            output+=("\n");
        }
        System.out.println(output);
        System.out.println("""
                            buy card as :\s
                            buy card\s
                            upgrade card as :\s
                            upgrade card\s
                            -back as:\s
                            back \s""");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateStats();
    }

    private void updateStats(){
        Gold.setText("Golds : " + App.getLoggedInUser().getGold());
        Level.setText("Level : " + App.getLoggedInUser().getLevel());
        CardContainer.getChildren().remove(vBox);
        try{
            SetAsProfile.setDisable(true);
            if(ShopMenuController.getCardByNameInPlayerCards(CardMenuController.card.getName())!=null){
                CardMenuController.card = ShopMenuController.getCardByNameInPlayerCards(CardMenuController.card.getName());
                SetAsProfile.setDisable(false);
            }
            if(CardMenuController.card.cardType.equals(CardType.TROOP)){
                Troop troop = (Troop) CardMenuController.card;
                FXMLLoader loader = new FXMLLoader(UpdateMenu.class.getResource("/FXML/ShowCardsVBox.fxml"));
                Scene scene = new Scene(loader.load());
                vBox = new VBox(scene.getRoot());
                ShowCardsVboxController showCardsVboxController = loader.getController();
                showCardsVboxController.createCardBox(troop , 250 , true);
                CardContainer.getChildren().add(vBox);

                Parameter1.setText("Attack : " + troop.getAttack());
                Parameter2.setText("Damage : " + troop.getDamage());
                Parameter3.setText("Upgrade Level : " + troop.getUpgradeLevel());
                Parameter4.setText("upgradeCost : " + troop.getUpgradeCost());
                Parameter5.setText("Cost : " + troop.getCost());
                Parameter6.setText("duration : " + troop.getDuration());
            } else {
                Spell spell = (Spell) CardMenuController.card;
                FXMLLoader loader = new FXMLLoader(UpdateMenu.class.getResource("/FXML/ShowCardsVBox.fxml"));
                Scene scene = new Scene(loader.load());
                vBox = new VBox(scene.getRoot());
                ShowCardsVboxController showCardsVboxController = loader.getController();
                showCardsVboxController.createCardBox(spell , 250 , true);
                CardContainer.getChildren().add(vBox);

                Parameter1.setText("Cost : " + spell.getCost());
            }
        } catch (IOException e) {e.printStackTrace();}
    }
}
