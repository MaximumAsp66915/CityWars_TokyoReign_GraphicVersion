package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileMenuCommands {
    ShowInformation("show\\s+information"),
    ChangeUsername("change\\s+username\\s+(?<newUsername>[\\S]+)"),
    ChangeNickname("change\\s+nickname\\s+(?<newNickname>[\\S]+)"),
    ChangePassword("change\\s+password\\s+(?<oldPassword>[\\S]+)\\s+(?<newPassword>[\\S]+)"),
    ChangeEmail("change\\s+email\\s+(?<newEmail>[\\S]+)"),
    Back("back"),
    Exit("exit");
    private final String pattern;

    ProfileMenuCommands(String pattern) {
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
