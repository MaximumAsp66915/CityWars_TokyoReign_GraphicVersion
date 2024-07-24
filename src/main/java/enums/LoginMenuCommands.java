package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginMenuCommands {
    RegisterMenu("register menu"),
    Login("login\\s+u\\s+(?<username>[\\S]+)\\s+p\\s+(?<password>[\\S]+)"),
    ForgotPassword("forgot\\s+password\\s+u\\s+(?<username>[\\S]+)"),
    Exit("exit");
    private final String pattern;

    LoginMenuCommands(String pattern) {
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
