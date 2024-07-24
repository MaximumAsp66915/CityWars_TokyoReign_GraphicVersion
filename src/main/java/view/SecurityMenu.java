package view;

import controller.DataController;
import controller.SecurityMenuController;
import enums.SecurityMenuCommands;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.Random;
import javafx.fxml.FXML;
import model.Result;

public class SecurityMenu extends AppMenu implements Initializable {
    public static void main(String[] args) {
        //(new AppView()).run();
        launch(args);}

    @Override
    public void start(Stage stage) throws IOException {
        UpdateMenu.stage = stage;
        UpdateMenu.toSecurityMenu();
    }

    @FXML private TextField Answer;
    @FXML private TextField AnswerConfirmation;
    @FXML private Label Captcha;
    @FXML private TextField CaptchaAnswer;
    @FXML private Button DoSign;
    @FXML private Label Error;
    @FXML private Button Exit;
    @FXML private Button SignIn;
    @FXML private Button SignUp;
    @FXML private RadioButton option1;
    @FXML private RadioButton option2;
    @FXML private RadioButton option3;

    @FXML
    void doSign(ActionEvent event) {
        UpdateMenu.soundEffectPlay(DataController.button4);
        check("pick q " + optionNumber + " a " + Answer.getText() + " " + AnswerConfirmation.getText() + " c " + CaptchaAnswer.getText());
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

    @FXML
    void newCaptcha(ActionEvent event) {
        UpdateMenu.soundEffectPlay(DataController.button4);
        captchaCode = Random5DigitNumber();
        Captcha.setText(String.valueOf(captchaCode));
    }

    public final static String[] SecurityQuestion = {"1-What was your first pet's name?", "2-What is your favorite football team?","3-What is your favorite color?"};
    private int captchaCode = Random5DigitNumber();
    private int optionNumber = 0;
    private final SecurityMenuController controller = new SecurityMenuController();



    @Override
    public void check(String input) {
        Matcher matcher;
        if ((matcher = SecurityMenuCommands.Security.getMatcher(input)) != null) {
            Result result = controller.security(Integer.parseInt(matcher.group("questionNumber")), matcher.group("answer"), matcher.group("answerConfirmation"), Integer.parseInt(matcher.group("captcha")) , captchaCode);
            Error.setText(result.getMessage());
            if(!result.isSuccessful()){
                captchaCode = Random5DigitNumber();
                Captcha.setText(String.valueOf(captchaCode));
            }
        } else if ((SecurityMenuCommands.Back.getMatcher(input)) != null) {
            System.out.println(controller.back());
        }


        else if ((SecurityMenuCommands.Exit.getMatcher(input)) != null) {
            controller.exit();
        } else {
            System.out.println("invalid command!");
        }
    }

    @Override
    public void MenuDetails() {
        System.out.println("-choose your security question as: \n" +
                SecurityQuestion[0] + "\n" +
                SecurityQuestion[1] + "\n" +
                SecurityQuestion[2] + "\n" +
                "captcha: " + captchaCode + "\n" +
                "pick q <questionNumber> a <answer> <answerConfirmation> c <captcha> \n" +
                "-back as: \n" +
                "back");
    }

    private int Random5DigitNumber() {
        Random random = new Random();
        return 10000 + random.nextInt(90000);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        option1.setText(SecurityQuestion[0]);
        option2.setText(SecurityQuestion[1]);
        option3.setText(SecurityQuestion[2]);
        Captcha.setText(String.valueOf(captchaCode));


        ToggleGroup toggleGroup = new ToggleGroup();
        option1.setToggleGroup(toggleGroup);
        option2.setToggleGroup(toggleGroup);
        option3.setToggleGroup(toggleGroup);

        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                UpdateMenu.soundEffectPlay(DataController.button4);
                RadioButton selectedRadioButton = (RadioButton) newValue;
                String selectedOption = selectedRadioButton.getText();
                optionNumber = getQuestionNumber(selectedOption) ;
            }
        });
    }
    private static int getQuestionNumber(String input){
        for(int i=0 ; i< SecurityQuestion.length ; i++){
            if(SecurityQuestion[i].equals(input)){
                return i+1;
            }
        }
        return -1 ;
    }
}

