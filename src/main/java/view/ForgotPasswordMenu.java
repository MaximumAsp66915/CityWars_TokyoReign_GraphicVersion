package view;
import controller.DataController;
import controller.ForgotPasswordMenuController;
import enums.ForgotPasswordMenuCommands;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import model.Result;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
public class ForgotPasswordMenu extends AppMenu implements Initializable {
    public static void main(String[] args) {
        //(new AppView()).run();
        launch(args);}

    @Override
    public void start(Stage stage) throws IOException {
        UpdateMenu.stage = stage;
        UpdateMenu.toForgotPasswordMenu();
    }

    @FXML
    private Button DoSign;

    @FXML
    private Label Error;

    @FXML
    private PasswordField NewPassword;

    @FXML
    private TextField QuestionsAnswer;

    @FXML
    private Label SecurityQuestion;

    @FXML
    private Button SignIn;

    @FXML
    private Button SignUp;

    @FXML
    void doSign(ActionEvent event) {
        UpdateMenu.soundEffectPlay(DataController.button4);
        check("a " + QuestionsAnswer.getText() + " p " + NewPassword.getText());
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

    private final ForgotPasswordMenuController controller = new ForgotPasswordMenuController();


    @Override
    public void check(String input) {
        Matcher matcher;
        if ((matcher = ForgotPasswordMenuCommands.ForgotPassword.getMatcher(input)) != null) {
            Result result = controller.forgotPassword(matcher.group("answer"), matcher.group("newPassword"));
            System.out.println(result);
            Error.setText(result.getMessage());
        }else if ((ForgotPasswordMenuCommands.Back.getMatcher(input)) != null) {
            System.out.println(controller.back());
        }



        else if ((ForgotPasswordMenuCommands.Exit.getMatcher(input)) != null) {
            controller.exit();
        } else {
            System.out.println("invalid command!");
        }
    }

    @Override
    public void MenuDetails() {
        System.out.println(controller.getSecurityQuestion() + "\n" +
                "-as: a <answer> p <newPassword> \n" +
                "-or back as: back");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SecurityQuestion.setText(controller.getSecurityQuestion());
    }
}