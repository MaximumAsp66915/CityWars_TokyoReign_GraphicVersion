package view;

import controller.ShopMenuController;
import controller.ShowCardsVboxController;
import controller.StaticMethods;
import enums.MainMenuCommands;
import enums.ShopMenuCommands;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import model.App;
import model.Player;
import model.cards.Card;
import model.cards.Spell;
import model.cards.SpellType;
import model.cards.Troop;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Matcher;

public class ShopMenu extends AppMenu implements Initializable {
    public static void main(String[] args) {
        //(new AppView()).run();
        launch(args);}

    @Override
    public void start(Stage stage) throws IOException {
        UpdateMenu.stage = stage;
        UpdateMenu.toShopMenu();
    }
    @FXML private HBox CardHBox;

    private final ShopMenuController controller = new ShopMenuController();

    @Override
    public void check(String input) {
        input = StaticMethods.find(input , Arrays.asList("show" , "select", "back"));
        Matcher matcher ;
        if (ShopMenuCommands.ShowCards.getMatcher(input) != null) {
            System.out.println(controller.showCards());
        } else if ((matcher = ShopMenuCommands.SelectCard.getMatcher(input)) != null) {
            System.out.println(controller.selectCard(matcher.group("cardName")));
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
        System.out.println("""
                            show cards as :\s
                            show cards\s
                            select card as :\s
                            select card <cardName>\s
                            -back as:\s
                            back \s""");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            for(Troop troop : Troop.troopHashMap.values()){
                FXMLLoader loader = new FXMLLoader(UpdateMenu.class.getResource("/FXML/ShowCardsVBox.fxml"));
                Scene scene = new Scene(loader.load());
                VBox vBox = new VBox(scene.getRoot());
                ShowCardsVboxController showCardsVboxController = loader.getController();
                showCardsVboxController.createCardBox(troop , 250 , true);
                CardHBox.getChildren().add(vBox);
            }
            for(Spell spell : Spell.spellHashMap.values()){
                FXMLLoader loader = new FXMLLoader(UpdateMenu.class.getResource("/FXML/ShowCardsVBox.fxml"));
                Scene scene = new Scene(loader.load());
                VBox vBox = new VBox(scene.getRoot());
                ShowCardsVboxController showCardsVboxController = loader.getController();
                showCardsVboxController.createCardBox(spell , 250 , true);
                CardHBox.getChildren().add(vBox);
            }
        } catch (IOException e) {e.printStackTrace();}
    }
}

