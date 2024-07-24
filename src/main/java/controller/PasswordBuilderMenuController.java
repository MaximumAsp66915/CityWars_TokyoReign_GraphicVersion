package controller;

import enums.Menu;
import model.*;
import view.UpdateMenu;

import java.security.SecureRandom;

public class PasswordBuilderMenuController {
    public String newPassword;

    private static String username;
    private static String password;
    private static String email;
    private static String nickname;
    private static int questionNumber;
    private static String answer;

    public static void KeepData(String username1 , String password1 , String email1 , String nickname1 , int questionNumber1 , String answer1){
        username = username1;
        password = password1;
        email = email1;
        nickname = nickname1;
        questionNumber = questionNumber1;
        answer = answer1;
    }
    public Result confirmPassword(String password, String passwordConfirmation ) {
        if (!password.equals(passwordConfirmation)) {
            return new Result(false, "password confirmation was wrong");
        }
        App.setLoggedInUser(Player.addPlayer(username, password , email , nickname , questionNumber , answer));
        App.setCurrentMenu(Menu.LoginMenu);
        UpdateMenu.toMainMenu();
        SecurityMenuController.username = null ;
        SecurityMenuController.password = null ;
        SecurityMenuController.email = null ;
        SecurityMenuController.nickname = null ;
        return new Result(true, "player created successfully");
    }
    public Result newPassword() {
        String oldPassword = newPassword;
        while(oldPassword.equals(newPassword)){
            newPassword = CustomPasswordGenerator();
        }

        return new Result(true, "another password: ");
    }

    public void exit() {
        App.setCurrentMenu(Menu.Exit);
    }

    public String CustomPasswordGenerator() {
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String specialCharacters = "!@#$%^&*()_+?<>{}][";

        String allCharacters = upperCaseLetters + lowerCaseLetters + digits + specialCharacters;
        int minLength = 8;
        int maxLength = 30;

        SecureRandom rand = new SecureRandom();
        int pwLength = minLength + rand.nextInt(maxLength - minLength + 1);

        StringBuilder password = new StringBuilder();
        password.append(upperCaseLetters.charAt(rand.nextInt(upperCaseLetters.length())));
        password.append(lowerCaseLetters.charAt(rand.nextInt(lowerCaseLetters.length())));
        password.append(digits.charAt(rand.nextInt(digits.length())));

        for (int i = 3; i < pwLength; i++) {
            password.append(allCharacters.charAt(rand.nextInt(allCharacters.length())));
        }

        return password.toString();
    }
}
