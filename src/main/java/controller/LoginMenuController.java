package controller;

import enums.Menu;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.util.Duration;
import model.*;
import view.SignInMenu;
import view.UpdateMenu;

public class LoginMenuController {
    private final Admin admin = new Admin();
    public long startTime = -1;
    public int failedTries = 0;
    public Result login(String username, String password) {//changed by mohammad
        User adminUser = new User(username, password);
        if(admin.equals(adminUser)){
            App.setCurrentMenu(Menu.AdminMenu);
            UpdateMenu.toAdminMenu();
            return new Result(true, "Admin logged in");
        }
        Player user = getUserByUsername(username);
        if (user == null) {
            return new Result(false, "No user exists with this username!");
        }
        if (!user.getPassword().equals(password)) {
            return new Result(false, "Password is incorrect!");
        }

        App.setLoggedInUser(user);
        App.setCurrentMenu(Menu.MainMenu);
        UpdateMenu.toMainMenu();
        return new Result(true, "login successfully");
    }

    public Result forgotPassword(String username){
        User user = getUserByUsername(username);
        if (user == null) {
            return new Result(false, "No user exists with this username!");
        }
        App.setCurrentMenu(Menu.ForgotPasswordMenu);
        ForgotPasswordMenuController.keepDate(username);
        UpdateMenu.toForgotPasswordMenu();
        return new Result(true, "next");
    }

    public Result registerMenu(){
        App.setCurrentMenu(Menu.RegisterMenu);
        return new Result(true, "register menu:");
    }

    public void exit() {
        App.setCurrentMenu(Menu.Exit);
    }

    private Player getUserByUsername(String username) {
        return App.players.get(username);
    }


    public void start() {
        startTime = System.currentTimeMillis();
    }

    public long getElapsedTimeInSeconds() {
        if(startTime == -1){
            return 1000;
        }
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - startTime;
        return elapsedTime / 1000;
    }
    public void showTime(SignInMenu signInMenu){
        final Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if(failedTries* 5L - getElapsedTimeInSeconds()>-1){
                if(failedTries* 5L - getElapsedTimeInSeconds()>=2)
                    signInMenu.Error1.setText( "You can try again in " + (failedTries* 5L - getElapsedTimeInSeconds()) + " seconds" );
                else
                    signInMenu.Error1.setText( "You can try again in " + (failedTries* 5L - getElapsedTimeInSeconds()) + " second" );
            } else {
                signInMenu.Error1.setText("");
            }
        }));
        timeline.setCycleCount((int) (failedTries* 5L+1));
        timeline.play();

    }
}
