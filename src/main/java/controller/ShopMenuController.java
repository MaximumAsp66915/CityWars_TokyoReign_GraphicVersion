package controller;

import enums.Menu;
import model.App;
import model.Result;
import model.cards.Card;
import model.cards.Spell;
import model.cards.Troop;


public class ShopMenuController {
    public Result showCards(){
        String output = "" ;
        output+="Troops : \n" ;
        for(Troop troop : Troop.troopHashMap.values()){
            String cardStatics ;
            if(getCardByNameInPlayerCards(troop.getName())!=null){
                cardStatics = "owned" ;
            } else {
                cardStatics = "not-owned" ;
            }
            output+=(troop.getName() + " : " + cardStatics + "\n");
        }
        output+=("\n");
        output+=("Spells : \n");
        for(Spell spell : Spell.spellHashMap.values()){
            String cardStatics ;
            if(getCardByNameInPlayerCards(spell.getName())!=null){
                cardStatics = "owned" ;
            } else {
                cardStatics = "not-owned" ;
            }
            output+=(spell.getSpellType().toString() + " : " + cardStatics + "\n");
        }
        output+=("\n");
        return new Result(true , output);
    }
    public Result selectCard(String input){
        if(Card.cardHashMap.get(input)==null){
            return new Result(false , "card doesn't exist");
        }
        if(getCardByNameInPlayerCards(input)!=null){
            CardMenuController.card = getCardByNameInPlayerCards(input) ;
        } else {
            CardMenuController.card = Card.cardHashMap.get(input);
        }
        App.setCurrentMenu(Menu.CardMenu);
        return new Result(true, "Card menu: ");
    }
    public Result back(){
        App.setCurrentMenu(Menu.MainMenu);
        return new Result(true, "Main menu: ");
    }
    public void exit() {
        App.setCurrentMenu(Menu.Exit);
    }
    public static Card getCardByNameInPlayerCards(String input){
        return App.getLoggedInUser().cards.get(input);
    }
}
