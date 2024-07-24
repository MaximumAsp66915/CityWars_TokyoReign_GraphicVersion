package model;

import controller.GameFieldController;
import model.cards.Card;
import model.cards.CardType;
import model.cards.SpellType;
import model.cards.Troop;
import model.field.GameField;
import model.field.Slot;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    public Player bluePlayer;
    public Player redPlayer;
    private double bluePlayerDamage;
    private double redPlayerDamage;
    public Player ap; // active player
    public Player wp; // waiting player
    private Color turn = null;
    public GameField field ;
    private int roundsCount;
    public boolean end = false;
    private Color winner = null;
    public Game(Player bluePlayer, Player redPlayer, int roundsCount){
        GameFieldController.redPlayer = redPlayer ;
        GameFieldController.bluePlayer = bluePlayer ;
        field = new GameField(this);

        this.bluePlayer = bluePlayer;
        this.redPlayer = redPlayer;

        this.bluePlayerDamage = this.bluePlayer.getHp();
        this.redPlayerDamage = this.redPlayer.getHp();

        this.turn = Color.getRandomColor();
        specifyActivePlayer();

        this.roundsCount = 2*roundsCount;
    }
    private void specifyActivePlayer(){
        if(this.turn == Color.BLUE){
            ap = bluePlayer;
            wp = redPlayer;
        }else if(this.turn == Color.RED){
            ap = redPlayer;
            wp = bluePlayer;
        }
    }
    private void changeTurn(){
        if(roundsCount == 0){
            this.end = true;
            Output.gameEnd();
            endGame();
            return;
        }
        if(this.turn == Color.BLUE){
            this.turn = Color.RED;
            roundsCount--;
        }else if(this.turn == Color.RED){
            this.turn = Color.BLUE;
            roundsCount--;
        }
        specifyActivePlayer();
        Output.turnChanged(turn);
    }

    private void endGame() {
        for(int i = 0 ; i<field.getFieldByColor(Color.BLUE).size() ; i++){
            Slot blueSlot = field.getFieldByColor(Color.BLUE).get(i);
            if(!blueSlot.isDestroyed()){
                if(blueSlot.getTroop() != null){
                    redPlayer.addHp(blueSlot.getTroop().getDamage());
                }
            }
            Slot redSlot = field.getFieldByColor(Color.RED).get(i);
            if(!redSlot.isDestroyed()){
                if(redSlot.getTroop() != null){
                    bluePlayer.addHp(redSlot.getTroop().getDamage());
                }
            }
            if(bluePlayer.getHp() <= 0){
                setWinner(Color.RED);
                return;
            }
            if(redPlayer.getHp() <= 0){
                setWinner(Color.RED);
                return;
            }
        }
    }

    public void takeSpellAction(SpellType spellType , int slotIndex){
        switch (spellType){
            case COPY -> {
                int index = 0; // input
                ap.cards.put(ap.cards.get(index).getName() , ap.cards.get(index));
            }
            case DEBILITATE -> {
                boolean flag = true;
                while(flag){
                    int randomIndex = new Random().nextInt(wp.cards.size()) - 1;
                    if(wp.cards.get(randomIndex).cardType == CardType.TROOP){
                        flag = false;
                        Troop troop = (Troop) wp.cards.get(randomIndex);
                        double amount = 0; // ?
                        troop.addAttack(amount);
                    }
                }
                flag = true;
                while(flag){
                    int randomIndex = new Random().nextInt(wp.cards.size()) - 1;
                    if(wp.cards.get(randomIndex).cardType == CardType.TROOP){
                        flag = false;
                        Troop troop = (Troop) wp.cards.get(randomIndex);
                        double amount = 0; // ?
                        troop.addDamage(amount);
                    }
                }
            }
            case HOLE_CHANGE -> {
                holeChange(Color.BLUE);
                holeChange(Color.RED);
            }
            case POWER_BOOST -> {
                boolean flag = true;
                while(flag){
                    int randomIndex = new Random().nextInt(ap.cards.size()) - 1;
                    if(ap.cards.get(randomIndex).cardType == CardType.TROOP){
                        flag = false;
                        Troop troop = (Troop) ap.cards.get(randomIndex);
                        double amount = 0; // ?
                        troop.addAttack(amount);
                    }
                }
            }
            case REMOVE -> {
                int randomIndex = new Random().nextInt(wp.cards.size()) - 1;
                ap.cards.put(wp.cards.get(randomIndex).getName() , wp.cards.get(randomIndex));
            }
            case REPAIR -> {
                if(!field.getFieldByColor(ap.color).get(slotIndex).isActive()){
                    field.getFieldByColor(ap.color).get(slotIndex).active();
                }
            }
            case SHIELD -> {
                field.getFieldByColor(wp.color).get(slotIndex).destroy();
            }
            case HEAL -> {
                int amount = 0; // ?
                ap.addHp(amount);
            }
            case ROUND_REDUCER -> {
                roundsCount--;
            }
        }
    }
    private void holeChange(Color color){
        ArrayList<Integer> slotsToChoose = new ArrayList<Integer>();
        for(Slot s : field.getFieldByColor(color)){
            if(s.isActive() && s.isEmpty()){
                slotsToChoose.add(s.getIndex());
            }
            if(!s.isActive()){
                s.active();
            }
        }
        int randomIndex = new Random().nextInt(slotsToChoose.size()) - 1;
        field.getFieldByColor(color).get(randomIndex).inactive();
    }
    public void placeCard(Card card , int index , Color color){
        Slot slot = field.getFieldByColor(color).get(index);
        for(int i=index ; i < index + card.getDuration() ; i++){
            if(!field.getFieldByColor(color).get(i).isActive() || !field.getFieldByColor(color).get(i).isEmpty()){
                Output.cardIsMisplaced(index);
                return;
            }
        }
        for(int i=index ; i < index + card.getDuration() ; i++){
            field.getFieldByColor(color).get(i).setCard(card);
        }
        Output.cardPlacedSuccessfully(index);
        changeTurn();
    }

    public Color getTurn(){
        return this.turn;
    }

    public double getBluePlayerDamage() {
        return bluePlayerDamage;
    }

    public double getRedPlayerDamage() {
        return redPlayerDamage;
    }
    public void addPlayerDamage(double amount , Color color) {
        if(color == Color.BLUE){
            bluePlayerDamage += amount;
        }else {
            redPlayerDamage += amount;
        }
    }
    public Color getWinner() {
        return winner;
    }

    public void setWinner(Color winner) {
        this.winner = winner;
    }
}
