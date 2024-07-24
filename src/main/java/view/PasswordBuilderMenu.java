package view;

import controller.DataController;
import controller.PasswordBuilderMenuController;
import enums.PasswordBuilderMenuCommands;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import model.Result;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Matcher;

public class PasswordBuilderMenu extends AppMenu implements Initializable {
    public static void main(String[] args) {
        //(new AppView()).run();
        launch(args);}

    @Override
    public void start(Stage stage) throws IOException {
        UpdateMenu.stage = stage;
        UpdateMenu.toPasswordBuilderMenu();
    }


    @FXML private Button Back;
    @FXML private Button DoSign;
    @FXML private Label Error;
    @FXML private Button Exit;
    @FXML private Button NewPasswordButton;
    @FXML private Label NewPassword;
    @FXML private TextField Password;
    @FXML private TextField PasswordConfirmation;
    @FXML private Button SignIn;
    @FXML private Button SignUp;

    @FXML
    void doSign(ActionEvent event) {
        UpdateMenu.soundEffectPlay(DataController.button4);
        check("confirm p " + Password.getText() + " " + PasswordConfirmation.getText());
    }

    @FXML
    void newPassword(ActionEvent event) {
        UpdateMenu.soundEffectPlay(DataController.button4);
        check("new password");
    }

    @FXML
    void signIn(ActionEvent event) {
        UpdateMenu.soundEffectPlay(DataController.button4);
        UpdateMenu.toSignInMenu();
    }

    @FXML
    void signUp(ActionEvent event) {
        UpdateMenu.soundEffectPlay(DataController.button4);
        UpdateMenu.toSignUpMenu();
    }
    private final PasswordBuilderMenuController controller = new PasswordBuilderMenuController();



    @Override
    public void check(String input) {
        Matcher matcher;
        if ((matcher = PasswordBuilderMenuCommands.confirmPassword.getMatcher(input)) != null) {
            Result result = controller.confirmPassword(matcher.group("password"), matcher.group("passwordConfirmation"));
            System.out.println(result);
            Error.setText(result.getMessage());
        } else if ((PasswordBuilderMenuCommands.newPassword.getMatcher(input)) != null) {
            System.out.println(controller.newPassword());
            NewPassword.setText(controller.newPassword);
        } else if ((PasswordBuilderMenuCommands.Exit.getMatcher(input)) != null) {
            controller.exit();
        } else {
            System.out.println("invalid command!");
        }
    }

    @Override
    public void MenuDetails() {
        controller.newPassword = controller.CustomPasswordGenerator();
        System.out.println("Your password will be: " + controller.newPassword + "\n" +
                "-confirm it as: confirm p <password> <passwordConfirmation> \n" +
                "new password as: new password");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        controller.newPassword = controller.CustomPasswordGenerator();
        NewPassword.setText(controller.newPassword);
    }
}

