package controller;

import enums.GameMenuCommands;
import enums.Menu;
import model.*;
import model.cards.Card;
import model.cards.CardType;
import model.cards.Spell;
import model.cards.Troop;
import model.field.Slot;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenuController {

    Player bluePlayer;
    Player redPlayer;
    Game game;
    boolean cardISSelected = false;
    Color selectedCardColor = null;
    public Result run(String username ,String password) {
        if(App.players.get(username)==null){
            return new Result(false , "No Player exists with this username!");
        }
        if(!App.players.get(username).getPassword().equals(password)){
            return new Result(false , "Password is incorrect!");
        }
        bluePlayer = App.getLoggedInUser();
        bluePlayer.setColor(Color.BLUE);
        redPlayer = App.players.get(username);
        redPlayer.setColor(Color.RED);

        game = new Game(bluePlayer,redPlayer,5);
        return new Result(true , "Game started");
    }

    public Result placeCard(int cardNumber, int slotNumber) {
        if(!cardISSelected) {
            return new Result(false , "You haven't chosen any card yet !");
        }
        if(game.getTurn() != selectedCardColor){
            return new Result(false ,"It's not your turn !");
        }
        game.placeCard(getPlayerByColor(selectedCardColor).cards.get(cardNumber-1) , slotNumber-1,selectedCardColor);
        return new Result(true , "card placed");
    }

    public Result selectCard(int cardNumber , String colorStr){
        Color playerColor = null;
        if(Color.turnStringToColor(colorStr) == null){
            return new Result(false , String.format("Error while showing card with color %s", colorStr));
        }
        playerColor = Color.turnStringToColor(colorStr);
        Result result = showCardInfo(cardNumber , playerColor);
        if(result.isSuccessful()){
            cardISSelected = true;
            selectedCardColor = playerColor;
            return result;
        }else{
            cardISSelected = false;
            selectedCardColor = playerColor;
            return result;
        }
    }
    private Result showCardInfo(int cardNumber, Color playerColor) {
        int cardIndex = cardNumber - 1;
        try {
            Card card = getPlayerByColor(playerColor).cards.get(cardIndex);
            if (card.cardType == CardType.TROOP) {
                Troop troop = (Troop) card;
                System.out.println(troop.getName() + " :");
                System.out.println("attack: " + troop.getAttack());
                System.out.println("duration: " + troop.getDuration());
                System.out.println("damage: " + troop.getDamage());
                System.out.println("upgradeLevel: " + troop.getUpgradeLevel());
                System.out.println("upgradeCost: " + troop.getUpgradeCost());
                System.out.println("cost: " + troop.getCost());
                System.out.println();
            } else {
                Spell spell = (Spell) card;
                System.out.println("Spells:\n");
                System.out.println(spell.getSpellType().toString() + " :");
                System.out.println("cost: " + spell.getCost());
                System.out.println();
            }
            return new Result(true , "Card Information showed successfully !\nCard selected successfully !");
        } catch (Exception e) {
           return new Result(false , String.format("Error while showing card with index %d", cardIndex));
        }
    }
    private Player getPlayerByColor(Color color){
        if(color == Color.BLUE){
            return bluePlayer;
        }else {
            return redPlayer;
        }
    }
    public Result back(){
        App.setCurrentMenu(Menu.MainMenu);
        return new Result(true, "Main menu: ");
    }
    public void exit(){
        App.setCurrentMenu(Menu.Exit);
    }

    public void showGameStatus(){
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("It's turn of "+game.getTurn()+" player.");
        if(cardISSelected) {
            System.out.println("Card is selected by " + selectedCardColor + " player.");
        }
        System.out.println();
        int i = 0;
        for(Card card : bluePlayer.cards.values()){
            i++;
            System.out.print(i+"."+card.getName()+" | ");
        }System.out.println();System.out.println();
        i = 0;
        for(Slot slot : game.field.getFieldByColor(Color.BLUE)){
            i++;
            if(slot.isActive()){
                if(slot.isEmpty()){
                    System.out.print(i+".[___] ");
                }else{
                    if(slot.isDestroyed()){
                        System.out.print(i+".[x"+slot.getCard().getName()+"x] ");
                    }else {
                        System.out.print(i+".["+slot.getCard().getName()+"] ");
                    }
                }
            }else{
                System.out.print(i+".[XXX] ");
            }
        }System.out.println();System.out.println();
        i = 0;
        for(Slot slot : game.field.getFieldByColor(Color.RED)){
            i++;
            if(slot.isActive()){
                if(slot.isEmpty()){
                    System.out.print(i+".[___] ");
                }else{
                    if(slot.isDestroyed()){
                        System.out.print(i+".[x"+slot.getCard().getName()+"x] ");
                    }else {
                        System.out.print(i+".["+slot.getCard().getName()+"] ");
                    }
                }
            }else{
                System.out.print(i+".[XXX] ");
            }
        }System.out.println();System.out.println();
        i = 0;
        for(Card card : redPlayer.cards.values()){
            i++;
            System.out.print(i+"."+card.getName()+" | ");
        }System.out.println();System.out.println();
        System.out.println("---------------------------------------------------------------------------------------");
    }
}


// Your code before merge :
//package controller;
//
//        import enums.GameMenuCommands;
//        import enums.Menu;
//        import model.*;
//
//        import java.util.Scanner;
//        import java.util.regex.Matcher;
//
//public class GameMenuController {
//
//    Player bluePlayer;
//    Player redPlayer;
//    Game game;
//    boolean cardISSelected = false;
//    Color selectedCardColor = null;
//    public void run(Player player1 , Player player2) {
//        Scanner sc = new Scanner(System.in);
//        String command;
//        Matcher matcher;
//
//        bluePlayer = player1;
//        bluePlayer.setColor(Color.BLUE);
//        redPlayer = player2;
//        redPlayer.setColor(Color.RED);
//
//        game = new Game(bluePlayer,redPlayer,5);
//
//
//        while (!game.end) {
//            command = sc.nextLine().trim();
//            matcher = GameMenuCommands.SelectCard.pattern.matcher(command);
//            if (matcher.matches()) {
//                int cardNumber = Integer.parseInt(matcher.group("cardNumber"));
//                String colorStr = matcher.group("PlayerColor");
//                Color playerColor = Color.turnStringToColor(colorStr);
//
//                showCardInfo(cardNumber , playerColor);
//
//                cardISSelected = true;
//                selectedCardColor = playerColor;
//
//                continue;
//            }
//
//            matcher = GameMenuCommands.placeCard.pattern.matcher(command);
//            if (matcher.matches()) {
//                int cardNumber = Integer.parseInt(matcher.group("cardNumber"));
//                int slotNumber = Integer.parseInt(matcher.group("slotNumber"));
//
//                if(!cardISSelected) {
//                    System.out.println("You haven't chosen any card yet !");
//                    continue;
//                }
//                if(game.getTurn() != selectedCardColor){
//                    System.out.println("It's not your turn !");
//                    continue;
//                }
//                placeCard(cardNumber , slotNumber);
//
//
//                continue;
//            }
//
//            if (command.equals("end")) {
//                return;
//            }
//
//            System.out.println("invalid input!");
//
//        }
//    }
//
//    private void placeCard(int cardNumber, int slotNumber) {
//        game.placeCard(getPlayerByColor(selectedCardColor).cards.get(cardNumber-1) , slotNumber-1,selectedCardColor);
//    }
//
//    private void showCardInfo(int cardNumber , Color playerColor) {
//        getPlayerByColor(playerColor).cards.get(cardNumber).getName();
//    }
//
//    private Player getPlayerByColor(Color color){
//        if(color == Color.BLUE){
//            return bluePlayer;
//        }else if (color== Color.RED){
//            return redPlayer;
//        }
//        return null;
//    }
//    public Result back(){
//        App.setCurrentMenu(Menu.MainMenu);
//        return new Result(true, "Main menu: ");
//    }
//    public void exit(){
//        App.setCurrentMenu(Menu.Exit);
//    }
//
//}
