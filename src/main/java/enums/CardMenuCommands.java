package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CardMenuCommands {
    BuyCard("buy\\s+card"),
    UpgradeCard("upgrade\\s+card"),
    Back("back"),
    Exit("exit");
    private final String pattern;

    CardMenuCommands(String pattern) {
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
