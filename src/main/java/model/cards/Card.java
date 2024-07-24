package model.cards;

import javafx.scene.image.Image;

import java.util.HashMap;

public class Card {
    public static HashMap<String , Card> cardHashMap = new HashMap<>();
    private String name = "none";
    public CardType cardType;
    protected Long cost = 0l;
    protected int duration = 1; // must be 1
    public String image ;
    public String imageBackground ;
    Card(CardType cardType , String name , Long cost , int duration){
        this.cardType = cardType;
        this.cost = cost;
        this.duration = duration;
        this.name = name;
        this.image = "/image/nullImage.jpeg" ;
        this.imageBackground = "/image/nullBackgroundImage.jpeg" ;
    }
    Card(CardType cardType , String name , Long cost){
        this.cardType = cardType;
        this.cost = cost;
        this.name = name;
        this.image = "/image/nullImage.jpeg" ;
        this.imageBackground = "/image/nullBackgroundImage.jpeg" ;
    }
    Card(Card card){
        this.cardType = card.cardType;
        this.cost = card.cost;
        this.duration = card.duration;
        this.name = card.name;
        this.image = card.image; ;
        this.imageBackground = card.imageBackground; ;
    }
    public Long getCost() {
        return cost;
    }
    public void setCost(Long cost) {
        this.cost = cost;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
