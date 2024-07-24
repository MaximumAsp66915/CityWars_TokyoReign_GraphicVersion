package controller;

import enums.Menu;
import model.*;
import view.UpdateMenu;

public class SecurityMenuController {
    public static String username;
    public static String password;
    public static String email;
    public static String nickname;

    public static void KeepData(String username1 , String password1 , String email1 , String nickname1){
        username = username1;
        password = password1;
        email = email1;
        nickname = nickname1;
    }

    public void exit() {
        App.setCurrentMenu(Menu.Exit);
    }

    public Result security(int questionNumber , String answer , String answerConfirmation , int captcha , int captchaAnswer){
        if (questionNumber>3 || questionNumber<1){
            return new Result(false, "Choose one of the questions!");
        }
        if (!answer.equals(answerConfirmation)){
            return new Result(false, "Answer confirmation is wrong!");
        }
        if (captcha != (captchaAnswer)){
            return new Result(false, "Captcha is wrong!");
        }
        if(!password.equals("random")){
            App.setLoggedInUser(Player.addPlayer(username, password , email , nickname , questionNumber , answer ));
            App.setCurrentMenu(Menu.LoginMenu);
            UpdateMenu.toMainMenu();
            System.out.println(App.getLoggedInUser().getUsername());
            return new Result(true, "player created successfully");
        }
        PasswordBuilderMenuController.KeepData(username , password , email , nickname , questionNumber , answer);
        App.setCurrentMenu(Menu.PasswordBuilderMenu);
        UpdateMenu.toPasswordBuilderMenu();
        return new Result(true, "next");
    }
    public Result back(){
        App.setCurrentMenu(Menu.RegisterMenu);
        return new Result(true, "register menu: ");
    }
}
