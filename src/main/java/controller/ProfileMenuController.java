package controller;

import enums.Menu;
import model.*;

import static controller.RegisterMenuController.isUsernameUnique;


public class ProfileMenuController {
    Player player ;
    public Result showInformation(){
        player = App.getLoggedInUser();
        return new Result(true, ("player name: " + player.getUsername() + "\n" +
                "player email: " + player.getEmail() + "\n" +
                "player nickname: " + player.getNickname() + "\n" +
                "player level: " + player.getLevel() + "\n" +
                "player hp: " + player.getHp() + "\n" +
                "player gold: " + player.getGold()));
    }
    public Result changeUsername(String newUsername){
        if (!newUsername.matches("[\\w\\d_]+")){
            return new Result(false, "username incorrect pattern");
        }
        if (!isUsernameUnique(newUsername)){
            return new Result(false, "a user exists with this username");
        }
        App.players.remove(App.getLoggedInUser().getUsername());
        App.getLoggedInUser().setUsername(newUsername);
        App.players.put(newUsername , App.getLoggedInUser());
        return new Result(true , "username has been changed to " + newUsername);
    }
    public Result changeNickname(String newNickname){
        App.getLoggedInUser().setNickname(newNickname);
        return new Result(true , "nickname has been changed to " + newNickname);
    }
    public Result changePassword(String oldPassword , String newPassword){
        if(!App.getLoggedInUser().getPassword().equals(oldPassword)){
            return new Result(false , "current password is wrong");
        }
        if ((newPassword.length()<8 || newPassword.length()>30) && !newPassword.equals("random")){
            return new Result(false, "password is too short or too long");
        }
        if ((!newPassword.matches(".*[a-z].*") || !newPassword.matches(".*[A-Z].*") || !newPassword.matches(".*[!@#$%^&*()_+\\-=\\[\\]{}/?<>~].*"))){
            return new Result(false, "weak password");
        }
        App.getLoggedInUser().setPassword(newPassword);
        return new Result(true , "password has been changed to " + newPassword);
    }
    public Result changeEmail(String newEmail){
        if(!Email.checkFormat(newEmail)){
            return new Result(false, "email format is incorrect");
        }
        App.getLoggedInUser().setEmail(newEmail);
        return new Result(true,"email has been changed to " + newEmail);
    }
    public Result back(){
        App.setCurrentMenu(Menu.MainMenu);
        return new Result(true, "Main menu: ");
    }
    public void exit() {
        App.setCurrentMenu(Menu.Exit);
    }

}
