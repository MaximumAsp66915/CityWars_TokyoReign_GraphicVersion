package controller;

import enums.Menu;
import model.*;
import view.UpdateMenu;

public class RegisterMenuController {

    public Result register(String username, String password , String passwordConfirmation, String email , String nickname) {
        if (!username.matches("[\\w\\d_]+")){
            return new Result(false, "user name incorrect pattern");
        }
        if (!isUsernameUnique(username)){
            return new Result(false, "a user exists with this username");
        }
        if ((password.length()<8 || password.length()>30) && !password.equals("random")){
            return new Result(false, "password is too short or too long");
        }
        if ((!password.matches(".*[a-z].*") || !password.matches(".*[A-Z].*") || !password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{}/?<>~].*")) && !password.equals("random")){
            return new Result(false, "weak password");
        }
        if(!password.equals(passwordConfirmation)){
            return new Result(false, "password confirmation is wrong");
        }
        if(!Email.checkFormat(email)){
            return new Result(false, "email format is incorrect");
        }
        SecurityMenuController.KeepData(username , password , email , nickname);
        App.setCurrentMenu(Menu.SecurityMenu);
        UpdateMenu.toSecurityMenu();
        return new Result(true, "next");
    }

    public Result loginMenu() {
        App.setCurrentMenu(Menu.LoginMenu);
        return new Result(true, "login menu: ");
    }

    public void exit() {
        App.setCurrentMenu(Menu.Exit);
    }

    public static boolean isUsernameUnique(String username) {
        if (getPlayerByUsername(username) != null){
            return false;
        }
        return App.getAdmin() == null || !App.getAdmin().getUsername().equals(username);
    }

    public static Player getPlayerByUsername(String username) {
        return App.players.get(username);
    }


}
