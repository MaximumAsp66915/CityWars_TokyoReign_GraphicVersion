package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SecurityMenuCommands {
    Back("back"),
    Security("pick\\s+q\\s+(?<questionNumber>[\\d]+)\\s+a\\s+(?<answer>.+)\\s+(?<answerConfirmation>.+)\\s+c\\s+(?<captcha>[\\d]+)"),
    Exit("exit");
    private final String pattern;

    SecurityMenuCommands(String pattern) {
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
