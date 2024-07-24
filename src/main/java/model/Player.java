package model;

import controller.DataController;
import model.cards.Card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Player extends User{
    Color color;
    private String nickname;
    private Email email;
    private int recoveryQuestion;
    private String recoveryAnswer;
    public HashMap<String , Card> cards = new HashMap<>();
    private int level;
    private int xp;
    private double hp;
    private Long gold;
    private String dateOfBirth = "";
    private String monthOfBirth = "";
    private String yearOfBirth = "";
    private String gender = "";
    private double soundVolume ;
    private double effectVolume ;
    private String image ;


    private Player(String username,String password , String email , String nickname , int questionNumber , String answer ){
        super(username, password);
        this.email = new Email(email);
        this.nickname=nickname;
        this.recoveryQuestion = questionNumber;
        this.recoveryAnswer = answer;
        //this.cards = [];
        this.level = 1;
        this.hp = 250.0;
        this.gold = 1000L;
        this.color = null;
        this.xp = 0 ;
        this.soundVolume = 0.3 ;
        this.effectVolume = 0.7 ;
        this.setImage("/image/nullProfile.jpeg");
    }

    private Player(String username,String password , String email , String nickname ,  int level ,int xp ,  double hp , Long gold){
        super(username, password);
        this.email = new Email(email);
        this.nickname=nickname;
        this.recoveryQuestion = 1;
        this.recoveryAnswer = "Apple";
        //this.cards = [];
        this.level = level;
        this.hp = hp;
        this.gold = gold;
        this.color = null;
        this.xp = xp ;
        this.soundVolume = 0.3 ;
        this.effectVolume = 0.7 ;
        this.setImage("/image/nullProfile.jpeg" );
    }
    public static Player addPlayer(String username, String password, String email, String nickname, int questionNumber, String answer){
        Player player = new Player(username,password ,email , nickname ,  questionNumber ,  answer );
        App.players.put(username , player);
        DataController.writePlayers();
        return player ;
    }
    public static Player addPlayerAdmin(String username, String password, String email, String nickname, int level, int hp, long gold){
        Player player = new Player(username,password ,  email ,  nickname ,   level , 0 , hp ,  gold);
        App.players.put(username , player);
        DataController.writePlayers();
        return player ;
    }
    public static void addPlayerCompletely(String username , String password , String nickname , String email , int questionNumber , String answer , int level , int xp , double hp , long gold , List<Card> cards , double soundVolume , double effectVolume , String dateOfBirth , String monthOfBirth , String yearOfBirth , String gender , String image){
        Player player = new Player(username,password ,  email ,  nickname ,   level  , xp, hp ,  gold);
        player.recoveryQuestion = questionNumber ;
        player.recoveryAnswer = answer ;
        player.setSoundVolume(soundVolume);
        player.setEffectVolume(effectVolume);
        player.setDateOfBirth(dateOfBirth);
        player.setMonthOfBirth(monthOfBirth);
        player.setYearOfBirth(yearOfBirth);
        player.setGender(gender);
        player.setImage(image) ;
        App.players.put(username , player);
        for(Card card :cards){
            player.cards.put(card.getName(), card);
        }
    }

    public void setColor(Color color){this.color = color;}
    public Color getColor() {
        return color;
    }
    public String getEmail() {
        return this.email.getEmail();
    }

    public String getNickname() {
        return nickname;
    }

    public int getLevel() {
        return level;
    }

    public double getHp() {
        return hp;
    }
    public void addHp(double amount){
        this.hp += amount;
    }
    public Long getGold() {
        return gold;
    }

    public void setEmail(String email) {
        this.email.setEmail(email);
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setImage(String image) {
        if(Player.class.getResourceAsStream(image)==null)
            image = "/image/nullProfile.png" ;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public void setGold(Long gold) {
        this.gold += gold;
    }

    public void setXp(int xp) {
        this.xp += xp;
        while(this.xp>2*this.getLevel()*this.getLevel()){
            this.xp-=2*this.getLevel()*this.getLevel();
            this.level++ ;
        }
    }

    public void setSoundVolume(double soundVolume) {
        this.soundVolume = soundVolume;
    }

    public void setEffectVolume(double effectVolume) {
        this.effectVolume = effectVolume;
    }

    public double getSoundVolume() {
        return soundVolume;
    }

    public double getEffectVolume() {
        return effectVolume;
    }

    public int getXp() {
        return xp;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getRecoveryQuestion() {
        return recoveryQuestion;
    }

    public String getRecoveryAnswer() {
        return recoveryAnswer;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getMonthOfBirth() {
        return monthOfBirth;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setMonthOfBirth(String monthOfBirth) {
        this.monthOfBirth = monthOfBirth;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setRecoveryQuestion(int recoveryQuestion) {
        this.recoveryQuestion = recoveryQuestion;
    }

    public void setRecoveryAnswer(String recoveryAnswer) {
        this.recoveryAnswer = recoveryAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if(super.equals(o)){
            if (getClass() != o.getClass()) return false;
            Player player = (Player) o;
            return player.nickname.equals(nickname);
        }
        return false;
    }

}
