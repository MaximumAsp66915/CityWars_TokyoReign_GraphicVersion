package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public enum AdminCardMenuCommands {
    AddCardTroop("add\\s+card\\s+(?<name>[\\S]+)\\s+(?<attack>[\\S]+)\\s+(?<duration>[\\S]+)\\s+(?<damage>[\\S]+)\\s+(?<upgradeLevel>[\\S]+)\\s+(?<upgradeCost>[\\S]+)\\s+(?<cost>[\\S]+)\\s+(?<image>[\\S]+)\\s+(?<imageBackground>[\\S]+)"),
    AddCardSpell("add\\s+card\\s+(?<spellType>[\\S]+)\\s+(?<cost>[\\S]+)\\s+(?<image>[\\S]+)\\s+(?<imageBackground>[\\S]+)"),
    EditCardTroop("edit\\s+card\\s+(?<name>[\\S]+)\\s+(?<attack>[\\S]+)\\s+(?<duration>[\\S]+)\\s+(?<damage>[\\S]+)\\s+(?<upgradeLevel>[\\S]+)\\s+(?<upgradeCost>[\\S]+)\\s+(?<cost>[\\S]+)\\s+(?<image>[\\S]+)\\s+(?<imageBackground>[\\S]+)"),
    EditCardSpell("edit\\s+card\\s+(?<spellType>[\\S]+)\\s+(?<cost>[\\S]+)\\s+(?<image>[\\S]+)\\s+(?<imageBackground>[\\S]+)"),
    RemoveCard("remove\\s+card\\s+(?<input>[\\S]+)"),
    ShowCards("show cards"),
    Back("back"),
    Exit("exit");
    private final String pattern;

    AdminCardMenuCommands(String pattern) {
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
