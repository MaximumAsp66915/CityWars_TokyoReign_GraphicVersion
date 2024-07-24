package controller;

import enums.Menu;
import model.*;

import java.util.ArrayList;


public class AdminPlayerMenuController {
    public Result addPlayer(String username , String password , String nickname , String email , int level , double hp , long gold , String date, String month , String year , String gender , String image , int recoverQ , String recoveryA){
        if (!username.matches("[\\w\\d_]+")){
            return new Result(false, "user name incorrect pattern");
        }
        if (!RegisterMenuController.isUsernameUnique(username)){
            return new Result(false, "a user exists with this username");
        }
        if ((password.length()<8 || password.length()>30) && !password.equals("random")){
            return new Result(false, "password is too short or too long");
        }
        if ((!password.matches(".*[a-z].*") || !password.matches(".*[A-Z].*") || !password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{}?<>/~].*")) && !password.equals("random")){
            return new Result(false, "weak password");
        }
        if(!Email.checkFormat(email)){
            return new Result(false, "email format is incorrect");
        }

        Player.addPlayerCompletely(username, password , nickname , email , recoverQ , recoveryA , level , 0 , hp , gold , new ArrayList<>() , 0.3 , 0.7 , date , month , year , gender , image);
        return new Result(true , "added successfully");
    }
    public Result editPlayer(String username , String password , String nickname , String email , int level , double hp , long gold , String date, String month , String year , String gender , String image , int recoverQ , String recoveryA){
        if (!username.matches("[\\w\\d_]+")){
            return new Result(false, "user name incorrect pattern");
        }
        Player player = App.players.get(username);
        if (player==null){
            return new Result(false, "there is no user with this username");
        }
        if ((password.length()<8 || password.length()>30) && !password.equals("random")){
            return new Result(false, "password is too short or too long");
        }
        if ((!password.matches(".*[a-z].*") || !password.matches(".*[A-Z].*") || !password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{}?<>/~].*")) && !password.equals("random")){
            return new Result(false, "weak password");
        }
        if(!Email.checkFormat(email)){
            return new Result(false, "email format is incorrect");
        }
        player.setPassword(password);
        player.setNickname(nickname);
        player.setEmail(email);
        player.setLevel(level);
        player.setHp(hp);
        player.setGold(gold);
        player.setDateOfBirth(date);
        player.setMonthOfBirth(month);
        player.setYearOfBirth(year);
        player.setGender(gender);
        player.setRecoveryQuestion(recoverQ);
        player.setRecoveryAnswer(recoveryA);
        player.setImage(image);
        return new Result(true , "edited successfully");
    }
    public Result removePlayer(String username){
        Player player = App.players.get(username);
        if(player==null){
            return new Result(false , "no card exists with this name");
        }
        App.players.remove(username);
        return new Result(false , "removed successfully");
    }
    public Result showPlayers(){
        System.out.println("Players : \n");
        for(Player player : App.players.values()){
            System.out.println(player.getUsername() + " :");
            System.out.println("password : " + player.getPassword());
            System.out.println("nickname : " + player.getNickname());
            System.out.println("email : " + player.getEmail());
            System.out.println("level : " + player.getLevel());
            System.out.println("hp : " + player.getHp());
            System.out.println("gold : " + player.getGold());
            System.out.println();
        }
        System.out.println();
        return new Result(true , "");
    }
    public Result back(){
        App.setCurrentMenu(Menu.AdminMenu);
        return new Result(true, "Admin menu: ");
    }
    public void exit() {
        App.setCurrentMenu(Menu.Exit);
    }

}
