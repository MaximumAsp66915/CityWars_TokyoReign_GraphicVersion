package view;

import controller.DataController;
import controller.ForgotPasswordMenuController;
import controller.LoginMenuController;
import controller.StaticMethods;
import enums.LoginMenuCommands;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.Result;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.regex.Matcher;

import static javafx.application.Application.launch;

public class SignInMenu extends AppMenu implements Initializable {
    public static void main(String[] args) {
        //(new AppView()).run();
        launch(args);}

    @Override
    public void start(Stage stage) throws IOException {
        UpdateMenu.stage = stage;
        UpdateMenu.toSignInMenu();
    }
    @FXML private Button DoSign;
    @FXML private Label Error;
    @FXML public Label Error1;
    @FXML private Button Exit;
    @FXML private Button ForgotPassword;
    @FXML private PasswordField Password;
    @FXML private Button SignIn;
    @FXML private Button SignUp;
    @FXML private TextField Username;
    private static boolean gameHasBeenInitialize = false ;

    @FXML
    void doSign(ActionEvent event) {
        UpdateMenu.soundEffectPlay(DataController.button4);
        check("login u " + Username.getText() + " p " + Password.getText());
        controller.showTime(this);
    }

    @FXML
    void forgotPassword(ActionEvent event) {
        UpdateMenu.soundEffectPlay(DataController.button4);
        check("forgot password u " + Username.getText());
    }

    @FXML
    void signIn(ActionEvent event) {}

    @FXML
    void signUp(ActionEvent event) {
        UpdateMenu.soundEffectPlay(DataController.button4);
        UpdateMenu.toSignUpMenu();
    }


    private final LoginMenuController controller = new LoginMenuController();

    @Override
    public void check(String input) {
        input = StaticMethods.find(input , Arrays.asList("login" , "forgot" , "register"));
        Matcher matcher;
        if ((matcher = LoginMenuCommands.Login.getMatcher(input)) != null) {
            Result tempResult = controller.login(matcher.group("username"), matcher.group("password"));
            if(!tempResult.isSuccessful()){
                controller.start();
                controller.failedTries++;
            } else {
                controller.startTime = -1;
                controller.failedTries = 0;
            }
            Error.setText(tempResult.getMessage());
        } else if ((matcher = LoginMenuCommands.ForgotPassword.getMatcher(input)) != null) {
            Error.setText(controller.forgotPassword(matcher.group("username")).getMessage());
        } else if ((LoginMenuCommands.RegisterMenu.getMatcher(input)) != null) {
            System.out.println(controller.registerMenu());
        }


        else if ((LoginMenuCommands.Exit.getMatcher(input)) != null) {
            controller.exit();
        } else {
            System.out.println("invalid command!");
        }
    }

    @Override
    public void MenuDetails() {
        System.out.println("""
                -login as:\s
                login u <username> p <password>\s
                -or forgot password as:\s
                forgot password u <username>\s
                -or register menu as:\s
                register menu""");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(ForgotPasswordMenuController.username!=null){
            Username.setText(ForgotPasswordMenuController.username);
        }
        if(!gameHasBeenInitialize){
            try {
                oneTimeInitialize();
            } catch (IOException e) {
                e.printStackTrace();
            }
            gameHasBeenInitialize = true ;
        }
    }
    private static void oneTimeInitialize() throws IOException {
        //        Player.addPlayerAdmin("player1" , "Password1234@" , "email@gmail.com" , "player" , 100 , 100 , 100L);
//        Player.addPlayerAdmin("player2" , "Password1234@" , "email@gmail.com" , "player" , 100 , 100 , 100L);
//        Troop.addTroop("a" , 15 , 3 , 15 , 3 , 3l , 3l);
//        Troop.addTroop("b" , 15 , 3 , 15 , 3 , 3l , 3l);
//        Spell.addSpell(SpellType.SHIELD , 3l);
//        Spell.addSpell(SpellType.HEAL , 3l);
//        App.players.get("player1").cards.put("a" , Troop.getTroopByName("a"));
//        App.players.get("player1").cards.put(SpellType.SHIELD.name() , Spell.getSpellBySpellType(SpellType.SHIELD));
//        App.players.get("player2").cards.put("b" , Troop.getTroopByName("b"));
//        App.players.get("player2").cards.put(SpellType.HEAL.name() , Spell.getSpellBySpellType(SpellType.HEAL));
        DataController.button1 = new Media(SignInMenu.class.getResource("/media/button1.mp3").toExternalForm());
        DataController.button2 = new Media(SignInMenu.class.getResource("/media/button2.mp3").toExternalForm());
        DataController.button3 = new Media(SignInMenu.class.getResource("/media/button3.mp3").toExternalForm());
        DataController.button4 = new Media(SignInMenu.class.getResource("/media/button4.mp3").toExternalForm());
        DataController.gameLost = new Media(SignInMenu.class.getResource("/media/gameLost.mp3").toExternalForm());
        DataController.gameOver = new Media(SignInMenu.class.getResource("/media/gameOver.mp3").toExternalForm());
        DataController.inGameSong = new Media(SignInMenu.class.getResource("/media/inGameSong.mp3").toExternalForm());
        DataController.ShopMenu = new Media(SignInMenu.class.getResource("/media/ShopMenu").toExternalForm());
        DataController.MainMenu = new Media(SignInMenu.class.getResource("/media/MainMenu").toExternalForm());
        DataController.GameField = new Media(SignInMenu.class.getResource("/media/GameField").toExternalForm());
    }
}
