package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum PasswordBuilderMenuCommands {
    confirmPassword("confirm\\s+p\\s+(?<password>[\\S]+)\\s+(?<passwordConfirmation>[\\S]+)"),
    newPassword("new\\s+password"),
    Exit("exit");
    private final String pattern;

    PasswordBuilderMenuCommands(String pattern) {
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
