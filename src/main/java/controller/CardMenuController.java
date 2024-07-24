package controller;

import enums.Menu;
import model.App;
import model.Result;
import model.cards.Card;
import model.cards.CardType;
import model.cards.Spell;
import model.cards.Troop;


public class CardMenuController {
    public static Card card ;
    public Result buyCard(){
        if(ShopMenuController.getCardByNameInPlayerCards(card.getName())!=null){
            return new Result(false , "You already have this card!");
        }
        if(card.getCost()<=App.getLoggedInUser().getGold()){
            App.getLoggedInUser().setGold(-(card.getCost()));
            if(card.cardType.equals(CardType.TROOP))
                App.getLoggedInUser().cards.put(card.getName(),new Troop((Troop) card));
            else
                App.getLoggedInUser().cards.put(card.getName(),new Spell((Spell) card));
            card = getCardByNameInPlayerCards(card.getName());
            App.getLoggedInUser().setXp(Math.toIntExact(card.getCost()));
            return new Result(true , "You bought this card");
        }
        return new Result(false , "you don't have enough gold to buy this card!");
    }
    public Result upgradeCard(){
        if(ShopMenuController.getCardByNameInPlayerCards(card.getName())==null){
            return new Result(false , "You don't have this card!");
        }
        if(!card.cardType.equals(CardType.TROOP)){
            return new Result(false , "You can't upgrade spells!");
        }
        Troop troop = (Troop) card ;
        if(troop.getUpgradeLevel()>App.getLoggedInUser().getLevel()){
            return new Result(false , "You don't have enough levels to upgrade this card!");
        }
        if(troop.getUpgradeCost()<=App.getLoggedInUser().getGold()){
            App.getLoggedInUser().setGold(-(troop.getUpgradeCost()));
            App.getLoggedInUser().cards.put(troop.getName(), troop);
            troop.upgradeTroop();
            return new Result(true , "Card has been upgraded");
        }
        return new Result(false , "You don't have enough gold to upgrade this card!");
    }
    public Result back(){
        App.setCurrentMenu(Menu.ShopMenu);
        return new Result(true, "shop menu: ");
    }
    public void exit() {
        App.setCurrentMenu(Menu.Exit);
    }
    public static Card getCardByNameInPlayerCards(String input){
        return App.getLoggedInUser().cards.get(input);
    }
}
