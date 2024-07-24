package model;

import model.cards.SpellType;

public interface Output {
    static void cardIsMisplaced(int index){
        System.out.println("card can not be placed in slot with number "+(index+1)+" !");
    }
    static void cardPlacedSuccessfully(int index){
        System.out.println("card Placed successfully in slot with index "+index+" !");
    }
    static void spellAction(SpellType spellType){
        System.out.println(spellType+" took action successfully !");
    }
    static void turnChanged(Color color){
        System.out.println("Turn changed and now it's turn of "+color+" player.");
    }
    static void troopFight(Color color, int index) {
        System.out.println("Slot with index "+index+" of "+color+" player destroyed !");
    }
    static void gameEnd() {
        System.out.println("Game End !");
    }
}
