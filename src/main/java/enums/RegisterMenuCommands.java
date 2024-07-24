package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RegisterMenuCommands {
    Register("register[\\s]+u[\\s]+(?<username>[\\S]+)[\\s]+p[\\s]+(?<password>[\\S]+)[\\s]+(?<passwordConfirmation>[\\S]+)[\\s]+e[\\s]+(?<email>[\\S]+)[\\s]+n[\\s]+(?<nickname>[\\S]+)"),
    LoginMenu("login\\s+menu"),
    Exit("exit");

    private final String pattern;

    RegisterMenuCommands(String pattern) {
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
