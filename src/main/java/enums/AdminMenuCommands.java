package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum AdminMenuCommands {//changed by mohammad
    cardMenu("card\\s+menu"),
    playerMenu("player\\s+menu"),
    Logout("logout"),
    Exit("exit");

    private final String pattern;

    AdminMenuCommands(String pattern) {
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
