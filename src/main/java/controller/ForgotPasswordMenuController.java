package controller;

import enums.Menu;
import model.*;
import view.SecurityMenu;
import view.UpdateMenu;

public class ForgotPasswordMenuController {
    public static String username;

    public static void keepDate(String username1){
        username = username1;
    }

    public Result forgotPassword(String answer , String password){
        if (!answer.equals(getSecurityAnswer())) {
            return new Result(false, "answer is wrong");
        }
        if ((password.length()<8 || password.length()>30)){
            return new Result(false, "password is too short or too long");
        }
        if ((!password.matches(".*[a-z].*") || !password.matches(".*[A-Z].*") || !password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{}?<>/~].*")) && !password.equals("random")){
            return new Result(false, "weak password");
        }
        getUserByUsername(username).setPassword(password);
        App.setLoggedInUser(getUserByUsername(username));
        App.setCurrentMenu(Menu.MainMenu);
        UpdateMenu.toMainMenu();
        username = null ;
        return new Result(true, "login successfully");
    }

    public Result back(){
        App.setCurrentMenu(Menu.LoginMenu);
        return new Result(true, "login menu: ");
    }

    public void exit() {
        App.setCurrentMenu(Menu.Exit);
    }

    public String getSecurityQuestion(){
        return SecurityMenu.SecurityQuestion[(getUserByUsername(username).getRecoveryQuestion()-1)];
    }
    private String getSecurityAnswer(){
        return getUserByUsername(username).getRecoveryAnswer();
    }
    private Player getUserByUsername(String username) {
        return App.players.get(username);
    }
}
