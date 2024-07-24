package model.field;

import model.Color;
import model.Game;
import model.Output;
import model.cards.*;

public class Slot {
    private Color color;
    private static Game game;
    private final int index;
    private Spell spell = null;
    private Troop troop = null;
    public Card card ;
    private boolean active = true;
    public boolean empty = true;
    private int propertyIndex;
    private boolean destroyed = false;
    private double damage = 0;
    Slot(int index , Color color){
        this.index = index;
        this.color = color;
    }
    public Troop getTroop() {
        return this.troop;
    }
    public Spell getSpell() {
        return this.spell;
    }
    public Card getCard(){
        return this.card ;
//        if(troop != null){
//            return troop;
//        }else {
//            return spell;
//        }
    }
    public void setCard(Card card) {
        if(isActive() && isEmpty()) {
            if (card.cardType == CardType.SPELL) {
                //game.takeSpellAction(this.spell.spellType , index);
                //Output.spellAction(this.spell.spellType);
                if(spell.spellType == SpellType.SHIELD || spell.spellType == SpellType.HEAL || spell.spellType == SpellType.HOLE_CHANGE){
                    this.spell = (Spell) card;
                    empty = false;
                }
            } else if (card.cardType == CardType.TROOP) {
                this.troop = (Troop) card;
                empty = false;

                Slot wpSlot = game.field.getFieldByColor(game.wp.getColor()).get(index);
                if(wpSlot.getTroop() != null) {
                    if (wpSlot.getTroop().getAttack() > this.troop.getAttack()) {
                        destroy();
                        Output.troopFight(color, index);
                        game.addPlayerDamage(wpSlot.getTroop().getEffectiveDamage(), wpSlot.color);
                        wpSlot.setDamage();
                    } else if (wpSlot.getTroop().getAttack() < this.troop.getAttack()) {
                        wpSlot.destroy();
                        Output.troopFight(wpSlot.color, wpSlot.index);
                        game.addPlayerDamage(troop.getEffectiveDamage(), this.color);
                        this.setDamage();
                    } else {
                        destroy();
                        Output.troopFight(color, index);
                        wpSlot.destroy();
                        Output.troopFight(wpSlot.color, wpSlot.index);
                    }
                }
            }
        }
    }
    public void inactive(){

        this.active = false;
    }
    public void active(){
        this.active = false;
    }
    public boolean isActive(){
        return this.active;
    }

    public void destroy() {
        if(isActive() && !this.isEmpty())
            this.destroyed = true;
    }
    public boolean isDestroyed() {
        return this.destroyed;
    }

    public boolean isEmpty() {
        return empty;
    }

    public int getIndex() {
        return index;
    }
    public static void setGame(Game inp){game = inp;}

    public double getEffective() {
        return damage;
    }

    public void setDamage() {
        this.damage = this.troop.getDamage();
    }
}
