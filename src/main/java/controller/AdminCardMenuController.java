package controller;

import enums.Menu;
import model.App;
import model.Player;
import model.Result;
import model.cards.Card;
import model.cards.Spell;
import model.cards.SpellType;
import model.cards.Troop;


public class AdminCardMenuController {
    public Result addCardTroop(String name , double attack , int duration , double damage , int upgradeLevel , long upgradeCost , Long cost , String image , String imageBackground){
        if(Troop.getTroopByName(name)!=null){
            return new Result(false , "Troop already exists!");
        }
        if(100<attack || 10>attack){
            return new Result(false , "Attack should be in [10,100]!");
        }
        if(1>duration || 5<duration){
            return new Result(false , "Duration should be in [1,5]!");
        }
        if(10>damage || 50<damage){
            return new Result(false , "Damage should be in [10,50]!");
        }
        Troop.addTroopDataReading(name , attack , duration , damage , upgradeLevel , upgradeCost , cost , image , imageBackground);
        return new Result(true , "Added successfully");
    }
    public Result addCardSpell(String spellType , Long cost , String image , String imageBackground){
        SpellType spellType1 = SpellType.spellType(spellType);
        if(spellType1==null){
            return new Result(false , "SpellType is null!");
        }
        if(Spell.getSpellBySpellType(spellType1)!=null){
            return new Result(false , "Spell already exists!");
        }
        Spell.addSpellDataReading(spellType1, cost , image , imageBackground);
        return new Result(true , "Added successfully");
    }
    public Result editCardTroop(String name , double attack , int duration , double damage , int upgradeLevel , long upgradeCost , Long cost , String image , String imageBackground){
        Troop troop = Troop.getTroopByName(name) ;
        if(troop==null){
            return new Result(false , "Troop is null!");
        }
        if(100<attack || 10>attack){
            return new Result(false , "Attack should be in [10,100]!");
        }
        if(1>duration || 5<duration){
            return new Result(false , "Duration should be in [1,5]!");
        }
        if(10>damage || 50<damage){
            return new Result(false , "Damage should be in [10,50]!");
        }
        troop.setAttack(attack);
        troop.setDuration(duration);
        troop.setDamage(damage);
        troop.setUpgradeLevel(upgradeLevel);
        troop.setUpgradeCost(upgradeCost);
        troop.setCost(cost);
        troop.image = image ;
        troop.imageBackground = imageBackground ;
        System.out.println(troop.getUpgradeCost());
        return new Result(true , "Edited successfully");
    }
    public Result editCardSpell(String spellType , Long cost , String image , String imageBackground){
        Spell spell = Spell.getSpellBySpellType(SpellType.spellType(spellType));
        if(spell==null){
            return new Result(false , "SpellType is null!");
        }
        if(Spell.getSpellBySpellType(SpellType.spellType(spellType))==null){
            return new Result(false , "Spell already exists!");
        }

        spell.setCost(cost);
        spell.image = image;
        spell.imageBackground = imageBackground;
        return new Result(true , "Edited successfully");
    }
    public Result removeCard(String input){
        Spell spell = Spell.getSpellBySpellType(SpellType.spellType(input));
        if(spell!=null){
            for(Player player : App.players.values()){
                player.cards.remove(input);
            }
            Card.cardHashMap.remove(Spell.getSpellBySpellType(SpellType.spellType(input)).getName());
            Spell.spellHashMap.remove(SpellType.spellType(input));
            return new Result(true , "Removed successfully");
        }
        Troop troop = Troop.getTroopByName(input);
        if(troop!=null){
            for(Player player : App.players.values()) {
                player.cards.remove(input);
            }
            Card.cardHashMap.remove(Troop.getTroopByName(input).getName());
            Troop.troopHashMap.remove(input);
            return new Result(true , "Removed successfully");
        }
        return new Result(false , "No card exists with this name!");

    }
    public Result showCard(){
        String output = "" ;
        output+="Troops : \n" ;
        for(Troop troop : Troop.troopHashMap.values()){
            output+=(troop.getName() + " :\n");
            output+=("attack : " + troop.getAttack() + "\n");
            output+=("duration : " + troop.getDuration() + "\n");
            output+=("damage : " + troop.getDamage() + "\n");
            output+=("upgradeLevel : " + troop.getUpgradeLevel() + "\n");
            output+=("upgradeCost : " + troop.getUpgradeCost() + "\n");
            output+=("cost : " + troop.getCost() + "\n");
            output+=("\n");
        }
        output+=("\n");
        output+=("Spells : \n");
        for(Spell spell : Spell.spellHashMap.values()){
            output+=(spell.getSpellType().toString() + " :\n");
            output+=("cost : " + spell.getCost() + "\n");
            output+=("\n");
        }
        output+=("\n");
        return new Result(true , output);
    }
    public Result back(){
        App.setCurrentMenu(Menu.AdminMenu);
        return new Result(true, "Admin menu: ");
    }
    public void exit() {
        App.setCurrentMenu(Menu.Exit);
    }

}
