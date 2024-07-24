package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ForgotPasswordMenuCommands {
    Back("back"),
    ForgotPassword("a\\s+(?<answer>[\\S]+)\\s+p\\s+(?<newPassword>[\\S]+)"),
    Exit("exit");

    private final String pattern;

    ForgotPasswordMenuCommands(String pattern) {
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
