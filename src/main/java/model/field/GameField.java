package model.field;

import model.Color;
import model.Game;

import java.util.ArrayList;
import java.util.Random;

public class GameField {
    public static ArrayList<Slot> blueField = new ArrayList<>(21);
    public static ArrayList<Slot> redField = new ArrayList<>(21);

    public GameField(Game game){
        blueField = new ArrayList<>();
        redField = new ArrayList<>();
        for(int i = 0 ; i<21 ; i++){
            blueField.add(new Slot(i , Color.BLUE));
            redField.add(new Slot(i , Color.RED));
        }

        Slot.setGame(game);

        blueField.get(new Random().nextInt(blueField.size())).inactive();
        redField.get(new Random().nextInt(redField.size())).inactive();
    }

    public ArrayList<Slot> getFieldByColor(Color color){
        if(color == Color.BLUE){
            return blueField;
        }else if(color == Color.RED){
            return redField;
        }
        return null;
    }
}
