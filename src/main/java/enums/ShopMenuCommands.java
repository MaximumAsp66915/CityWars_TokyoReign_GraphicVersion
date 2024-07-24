package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ShopMenuCommands {
    ShowCards("show\\s+cards"),
    SelectCard("select\\s+card\\s+(?<cardName>[\\S]+)"),
    Back("back"),
    Exit("exit");
    private final String pattern;

    ShopMenuCommands(String pattern) {
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
