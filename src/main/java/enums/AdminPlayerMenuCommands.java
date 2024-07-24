package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public enum AdminPlayerMenuCommands {
    AddPlayer("add\\s+player\\s+(?<username>[\\S]+)\\s+(?<password>[\\S]+)\\s+(?<nickname>[\\S]+)\\s+(?<email>[\\S]+)\\s+(?<level>[\\S]+)\\s+(?<hp>[\\S]+)\\s+(?<gold>[\\S]+)\\s+(?<date>[\\S]+)\\s+(?<month>[\\S]+)\\s+(?<year>[\\S]+)\\s+(?<gender>[\\S]+)\\s+(?<image>[\\S]+)\\s+(?<recoveryQ>[\\S]+)\\s+(?<recoveryA>[\\S]+)"),
    EditPlayer("edit\\s+player\\s+(?<username>[\\S]+)\\s+(?<password>[\\S]+)\\s+(?<nickname>[\\S]+)\\s+(?<email>[\\S]+)\\s+(?<level>[\\S]+)\\s+(?<hp>[\\S]+)\\s+(?<gold>[\\S]+)\\s+(?<date>[\\S]+)\\s+(?<month>[\\S]+)\\s+(?<year>[\\S]+)\\s+(?<gender>[\\S]+)\\s+(?<image>[\\S]+)\\s+(?<recoveryQ>[\\S]+)\\s+(?<recoveryA>[\\S]+)"),
    RemovePlayer("remove\\s+player\\s+(?<username>[\\S]+)"),
    ShowPlayers("show\\s+players"),
    Back("back"),
    Exit("exit");
    private final String pattern;

    AdminPlayerMenuCommands(String pattern) {
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
