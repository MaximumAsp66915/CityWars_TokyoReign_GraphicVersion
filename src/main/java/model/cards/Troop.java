package model.cards;

import controller.DataController;
import model.App;

import java.util.HashMap;

public class Troop extends Card{
    public static HashMap<String , Troop> troopHashMap = new HashMap<>();
    private double attack = 0;
    private double damage = 0;
    private double effectiveDamage = damage/duration;
    private int upgradeLevel = 0;
    private Long upgradeCost = 0l;
    public Troop(String name , double attack , int duration , double damage , int upgradeLevel , Long upgradeCost , Long cost){
        super(CardType.TROOP , name , cost , duration);
        this.attack = attack ;
        this.duration = duration ;
        this.damage = damage ;
        this.upgradeLevel = upgradeLevel ;
        this.upgradeCost = upgradeCost ;
        effectiveDamage = damage/duration;

    }
    public Troop(Troop troop){
        super(troop);
        this.attack = troop.attack ;
        this.damage = troop.damage ;
        this.effectiveDamage = troop.effectiveDamage ;
        this.upgradeCost = troop.upgradeCost ;
        this.upgradeLevel = troop.upgradeLevel;
    }
    public static void addTroopDataReading(String name , double attack , int duration , double damage , int upgradeLevel , long upgradeCost , long cost , String image , String imageBackground){
        Troop troop = new Troop(name , attack , duration , damage , upgradeLevel , upgradeCost , cost);
        if(Troop.class.getResourceAsStream(image)==null)
            image = "/image/nullImage.jpeg";
        if(Troop.class.getResourceAsStream(imageBackground)==null)
            imageBackground = "/image/nullBackgroundImage.jpeg";
        troop.image = image ;
        troop.imageBackground = imageBackground ;
        troopHashMap.put(name , troop);
        cardHashMap.put(name , troop);
    }
    public void upgradeTroop(){
        this.attack = this.attack*1.1 + 10;
        this.damage= this.damage*1.1 + 15;
        this.effectiveDamage = damage/duration ;
        App.getLoggedInUser().setXp(Math.toIntExact(upgradeCost));
        upgradeCost = (long) (upgradeCost * 1.5 + 0.2*this.getCost() + 5);
    }
    public static Troop getTroopByName(String name){
        return troopHashMap.get(name);
    }
    public double getAttack() {
        return attack;
    }

    public void setAttack(double attack) {
        this.attack = attack;
    }

    public void addAttack(double amount) {
        this.attack += amount;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public void addDamage(double amount) {
        this.damage += amount;
    }

    public int getUpgradeLevel() {
        return upgradeLevel;
    }

    public void setUpgradeLevel(int upgradeLevel) {
        this.upgradeLevel = upgradeLevel;
    }

    public Long getUpgradeCost() {
        return upgradeCost;
    }

    public void setUpgradeCost(Long upgradeCost) {
        this.upgradeCost = upgradeCost;
    }

    public double getEffectiveDamage() {
        return effectiveDamage;
    }
}
