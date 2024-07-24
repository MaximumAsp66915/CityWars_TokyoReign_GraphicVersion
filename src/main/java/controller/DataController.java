package controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import model.App;
import model.Player;
import model.cards.*;
import javafx.scene.media.Media;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataController {
    public static Media button1 ;
    public static Media button2 ;
    public static Media button3 ;
    public static Media button4 ;
    public static Media gameLost ;
    public static Media gameOver ;
    public static Media inGameSong ;
    public static Media MainMenu;
    public static Media ShopMenu;
    public static Media GameField;

    public static void updateData(){
        DataController.writeCards();
        DataController.writePlayers();
        System.out.println("Data has been updated");
//        DataController.readCards();
//        DataController.readPlayers();
    }
    public static void writePlayers() {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode playersArray = objectMapper.createArrayNode();
        for(Player player : App.players.values()){
            List<List<String>>Cards = new ArrayList<>();
            for (Card card : player.cards.values()) {
                List<String> CardToPut = new ArrayList<>() ;
                CardToPut.add(card.getName()) ;
                CardToPut.add(card.cardType.toString());
                CardToPut.add(String.valueOf(card.getCost())) ;
                CardToPut.add(String.valueOf(card.getDuration()));
                if(card.cardType.equals(CardType.TROOP)){
                    Troop troop = (Troop) card ;
                    CardToPut.add(String.valueOf(troop.getAttack()));
                    CardToPut.add(String.valueOf(troop.getDamage()));
                    CardToPut.add(String.valueOf(troop.getEffectiveDamage()));
                    CardToPut.add(String.valueOf(troop.getUpgradeLevel()));
                    CardToPut.add(String.valueOf(troop.getUpgradeCost()));
                } else {
                    Spell spell = (Spell) card ;
                    CardToPut.add(String.valueOf(spell.getSpellType()));
                }
                Cards.add(CardToPut);
            }
            ObjectNode playerNode = objectMapper.createObjectNode();
            playerNode.put("username" , player.getUsername());
            playerNode.put("password" , player.getPassword());
            playerNode.put("nickname", player.getNickname());
            playerNode.put("email", player.getEmail());
            playerNode.put("recoveryQuestion", player.getRecoveryQuestion());
            playerNode.put("recoveryAnswer", player.getRecoveryAnswer());
            playerNode.putPOJO("cards", Cards);
            playerNode.put("level", player.getLevel());
            playerNode.put("xp", player.getXp());
            playerNode.put("hp", player.getHp());
            playerNode.put("gold", player.getGold());
            playerNode.put("soundVolume", player.getSoundVolume());
            playerNode.put("effectVolume", player.getEffectVolume());
            playerNode.put("dateOfBirth", player.getDateOfBirth());
            playerNode.put("monthOfBirth", player.getMonthOfBirth());
            playerNode.put("yearOfBirth", player.getYearOfBirth());
            playerNode.put("gender",player.getGender());
            playerNode.put("image", player.getImage());
            playersArray.add(playerNode);
        }
        try {
            objectMapper.writeValue(new File("players.json"), playersArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void readPlayers(){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode playersArray = objectMapper.readTree(new File("players.json"));
            for (JsonNode playerNode : playersArray) {
                List<Card>cards = new ArrayList<>();
                for(int i=0 ; i<playerNode.get("cards").size() ; i++){
                    Card card ;
                    if(playerNode.get("cards").get(i).get(1).asText().equals(CardType.TROOP.toString())){
                        card = new Troop(playerNode.get("cards").get(i).get(0).asText() , playerNode.get("cards").get(i).get(4).asDouble() , playerNode.get("cards").get(i).get(3).asInt() , playerNode.get("cards").get(i).get(5).asDouble() , playerNode.get("cards").get(i).get(7).asInt() , playerNode.get("cards").get(i).get(8).asLong() , playerNode.get("cards").get(i).get(2).asLong());
                    } else {
                        card = new Spell(SpellType.spellType(playerNode.get("cards").get(i).get(4).asText()) , playerNode.get("cards").get(i).get(2).asLong());
                    }
                    card.image = Card.cardHashMap.get(card.getName()).image;
                    card.imageBackground = Card.cardHashMap.get(card.getName()).imageBackground;
                    cards.add(card);
                }
                Player.addPlayerCompletely(playerNode.get("username").asText() , playerNode.get("password").asText() , playerNode.get("nickname").asText() , playerNode.get("email").asText() , playerNode.get("recoveryQuestion").asInt() , playerNode.get("recoveryAnswer").asText() , playerNode.get("level").asInt() , playerNode.get("xp").asInt() , playerNode.get("hp").asInt() , playerNode.get("gold").asLong() , cards , playerNode.get("soundVolume").asDouble() , playerNode.get("effectVolume").asDouble() , playerNode.get("dateOfBirth").asText() , playerNode.get("monthOfBirth").asText() , playerNode.get("yearOfBirth").asText() , playerNode.get("gender").asText() , playerNode.get("image").asText());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeCards() {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode cardsArray = objectMapper.createArrayNode();
        for(Card card : Card.cardHashMap.values()){
            ObjectNode cardNode = objectMapper.createObjectNode();
            cardNode.put("name" , card.getName());
            cardNode.put("cardType" , String.valueOf(card.cardType));
            cardNode.put("cost" ,card.getCost());
            cardNode.put("duration" , card.getDuration());
            if(card.cardType.equals(CardType.TROOP)){
                Troop troop = (Troop) card ;
                cardNode.put("attack" , troop.getAttack());
                cardNode.put("damage" , troop.getDamage());
                cardNode.put("effectiveDamage" , troop.getEffectiveDamage());
                cardNode.put("upgradeLevel" , troop.getUpgradeLevel());
                cardNode.put("upgradeCost" , troop.getUpgradeCost());
            } else {
                Spell spell = (Spell) card ;
                cardNode.put("spellType" , spell.getSpellType().toString());
            }
            cardNode.put("image" , card.image);
            cardNode.put("imageBackground" , card.imageBackground);
            cardsArray.add(cardNode);
        }
        try {
            objectMapper.writeValue(new File("cards.json"), cardsArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void readCards(){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode cardsArray = objectMapper.readTree(new File("cards.json"));
            for (JsonNode cardNode : cardsArray) {
                if(cardNode.get("cardType").asText().equals(CardType.TROOP.toString())){
                    Troop.addTroopDataReading(cardNode.get("name").asText() , cardNode.get("attack").asDouble() , cardNode.get("duration").asInt() , cardNode.get("damage").asDouble() , cardNode.get("upgradeLevel").asInt() , cardNode.get("upgradeCost").asLong() , cardNode.get("cost").asLong() , cardNode.get("image").asText() , cardNode.get("imageBackground").asText());
                } else {
                    Spell.addSpellDataReading(SpellType.spellType(cardNode.get("spellType").asText()),cardNode.get("cost").asLong() , cardNode.get("image").asText() , cardNode.get("imageBackground").asText());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
