package view;

import controller.DataController;
import controller.GameMenuController;
import controller.StaticMethods;
import enums.GameMenuCommands;
import enums.MainMenuCommands;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.App;
import model.Result;
import model.cards.Card;
import javafx.fxml.FXML;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu extends AppMenu{
    public static void main(String[] args) {
        //(new AppView()).run();
        launch(args);}

    @Override
    public void start(Stage stage) throws IOException {
        UpdateMenu.stage = stage;
        UpdateMenu.toGameMenu();
    }
    @FXML private Label Error;
    @FXML private PasswordField Password;
    @FXML private TextField Username;
    @FXML
    void startGame(ActionEvent event) {
        UpdateMenu.soundEffectPlay(DataController.button3);
        check("start game u " + Username.getText() + " p " + Password.getText());
    }
    @FXML
    void startBettingGame(ActionEvent event) {
        UpdateMenu.soundEffectPlay(DataController.button3);
        check("start betting game u " + Username.getText() + " p " + Password.getText());
    }
    private final GameMenuController controller = new GameMenuController();

    boolean gameStarted = false;
    boolean cardSelected = false;

    @Override
    public void check(String input) {
        if(gameStarted){
            controller.showGameStatus();
        }
        Matcher matcher ;
        input = StaticMethods.find(input , Arrays.asList("start" , "back" , "select" , "place"));
        if ((matcher = GameMenuCommands.StartGame.getMatcher(input)) != null) {
            Result result = controller.run(matcher.group("username") , matcher.group("password"));
            gameStarted = result.isSuccessful();
            if(result.isSuccessful()){
                UpdateMenu.toGameField();
            }
            System.out.println(result);
            Error.setText(result.getMessage());
        } else if ((matcher = GameMenuCommands.StartBettingGame.getMatcher(input)) != null) {
            boolean flag = true ;
            if(App.getLoggedInUser().getGold()<5 || App.players.get(matcher.group("username")).getGold()<5){
                Error.setText(new Result(false , "One of the players don't have enough gold!").getMessage());
                flag = false ;
            }
            if(flag) {
                Result result = controller.run(matcher.group("username"), matcher.group("password"));
                gameStarted = result.isSuccessful();
                if (result.isSuccessful()) {
                    App.getLoggedInUser().setGold(-5L);
                    App.players.get(matcher.group("username")).setGold(-5L);
                    UpdateMenu.toGameField();
                }
                System.out.println(result);
                Error.setText(result.getMessage());
            }
        } else if ((matcher = GameMenuCommands.SelectCard.getMatcher(input)) != null) {
            Result result = controller.selectCard(Integer.parseInt(matcher.group("cardNumber")) , matcher.group("playerColor"));
            cardSelected = result.isSuccessful();
            System.out.println(result);
        } else if ((matcher = GameMenuCommands.PlaceCard.getMatcher(input)) != null) {
            Result result = controller.placeCard(Integer.parseInt(matcher.group("cardNumber")) , Integer.parseInt(matcher.group("slotNumber")));
            if(result.isSuccessful()){
                cardSelected = false;
            }
            System.out.println(result);
        }
        else if (GameMenuCommands.Back.getMatcher(input) != null) {
            System.out.println(controller.back());
        } else if (MainMenuCommands.Exit.getMatcher(input) != null) {
            controller.exit();
        } else {
            System.out.println("invalid command!");
        }
    }

    @Override
    public void MenuDetails() {
        if (!gameStarted) {
            System.out.println("""
                    -start game as:\s
                    start game u <username> p <password>\s
                    -back as:\s
                    back \s""");
        } else if (!cardSelected) {
            System.out.println("""
                    -select card as: \s
                    select card number <cardNumber> player <playerColor>\s
                    -back as:\s
                    back \s""");
        } else {
            System.out.println("""
                    -select card as: \s
                    select card number <cardNumber> player <playerColor>\s
                    -place card as: \s
                    place card number <cardNumber> in slot <slotNumber>\s
                    -back as:\s
                    back \s""");
        }
    }
}
