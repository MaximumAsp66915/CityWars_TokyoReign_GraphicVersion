package view;

import controller.DataController;
import controller.RegisterMenuController;
import controller.SecurityMenuController;
import controller.StaticMethods;
import enums.RegisterMenuCommands;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Matcher;

public class SignUpMenu extends AppMenu implements Initializable {
        @FXML private Button DoSign;
        @FXML private TextField Email;
        @FXML private Label Error;
        @FXML private Button Exit;
        @FXML private TextField Nickname;
        @FXML private PasswordField Password;
        @FXML private PasswordField PasswordConfirmation;
        @FXML private Button SignIn;
        @FXML private Button SignUp;
        @FXML private TextField Username;

        @FXML
        void doSign(ActionEvent event) {
            UpdateMenu.soundEffectPlay(DataController.button4);
            check("register u " + Username.getText() + " p " + Password.getText() + " " + PasswordConfirmation.getText() + " e " + Email.getText() + " n " + Nickname.getText());
        }

        @FXML
        void signIn(ActionEvent event) {
            UpdateMenu.soundEffectPlay(DataController.button4);
            UpdateMenu.toSignInMenu();
        }

        @FXML
        void signUp(ActionEvent event) {}

    public static void main(String[] args) {
        //(new AppView()).run();
        launch(args);}
    @Override
    public void start(Stage stage) throws Exception {
        UpdateMenu.stage = stage;
        UpdateMenu.toSignUpMenu();
    }



    private final RegisterMenuController controller = new RegisterMenuController();

    @Override
    public void check(String input) {
        input = StaticMethods.find(input , Arrays.asList("register" , "login"));
        Matcher matcher;
        if ((matcher = RegisterMenuCommands.Register.getMatcher(input)) != null) {
            Error.setText(controller.register(matcher.group("username"), matcher.group("password") , matcher.group("passwordConfirmation") , matcher.group("email") , matcher.group("nickname")).getMessage());
        } else if ((RegisterMenuCommands.LoginMenu.getMatcher(input)) != null) {
            System.out.println(controller.loginMenu());
        }



        else if ((RegisterMenuCommands.Exit.getMatcher(input)) != null) {
            controller.exit();
        } else {
            System.out.println("invalid command!");
        }
    }

    @Override
    public void MenuDetails() {
        System.out.println("""
                -Register as:\s
                register u <username> p <password> <passwordConfirmation> e <email> n <nickname>
                -login menu as:\s
                login menu""");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(SecurityMenuController.username!=null){
            Username.setText(SecurityMenuController.username);
        }
        if(SecurityMenuController.password!=null){
            Password.setText(SecurityMenuController.password);
            PasswordConfirmation.setText(SecurityMenuController.password);
        }
        if(SecurityMenuController.nickname!=null){
            Nickname.setText(SecurityMenuController.nickname);
        }
        if(SecurityMenuController.email!=null){
            Email.setText(SecurityMenuController.email);
        }
    }
}
