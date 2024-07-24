package model.cards;

import controller.DataController;

import java.util.HashMap;

public class Spell extends Card{
    public static HashMap<SpellType , Spell> spellHashMap = new HashMap<>();
    public SpellType spellType;
    public Spell(SpellType spellType , Long cost){
        super(CardType.SPELL , String.valueOf(spellType) , cost);
        this.spellType = spellType;
    }
    public Spell(Spell spell){
        super(spell);
        this.spellType = spell.spellType ;
    }
    public static void addSpellDataReading(SpellType spellType , long cost , String image , String imageBackground){
        Spell spell = new Spell(spellType , cost);
        spell.image = image ;
        spell.imageBackground = imageBackground ;
        Card.cardHashMap.put(spell.getName(), spell);
        spellHashMap.put(spellType , spell);
    }
    public static Spell getSpellBySpellType(SpellType spellType){
            return spellHashMap.get(spellType);
    }

    public SpellType getSpellType() {
        return spellType;
    }
}
