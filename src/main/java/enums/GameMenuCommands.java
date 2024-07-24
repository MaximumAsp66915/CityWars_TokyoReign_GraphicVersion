package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands {
    StartGame("start\\s+game\\s+u\\s+(?<username>[\\S]+)\\s+p\\s+(?<password>[\\S]+)"),
    StartBettingGame("start\\s+betting\\s+game\\s+u\\s+(?<username>[\\S]+)\\s+p\\s+(?<password>[\\S]+)"),
    SelectCard("select\\s+card\\s+number\\s+(?<cardNumber>\\d+)\\s+player\\s+(?<playerColor>[\\S]+)"),
    PlaceCard("place\\s+card\\s+number\\s+(?<cardNumber>\\d+)\\s+in\\s+slot\\s+(?<slotNumber>\\d+)"),
    Back("back");

    private final String pattern;

    GameMenuCommands(String pattern) {
        this.pattern = pattern;
    }

    public Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile(this.pattern).matcher(input);

        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }
}