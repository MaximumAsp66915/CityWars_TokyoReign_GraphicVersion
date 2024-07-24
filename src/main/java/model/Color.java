package model;

import java.util.Random;

public enum Color {
    BLUE, RED; //don't put any other color !!!

    public static Color getRandomColor() {
        return values()[new Random().nextInt(values().length)];
    }
    public static Color turnStringToColor(String str){
        if(str.trim().toLowerCase().equals("blue")){
            return BLUE;
        } else if (str.trim().toLowerCase().equals("red")) {
            return RED;
        }
        return null;
    }
}
