package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {
    private String email;
    private String domain;
    Email(String input) {
        Matcher matcher = Pattern.compile("^(?<email>[\\S]+)@(?<domain>[\\S]+)\\.com$").matcher(input);
        if(matcher.matches()){
            this.email = matcher.group("email");
            this.domain = matcher.group("domain");
        }
    }

    public String getEmail() {
        return email + "@" + domain + ".com";
    }

    public void setEmail(String input) {
        Matcher matcher = Pattern.compile("^(?<email>[\\S]+)@(?<domain>[\\S]+)\\.com$").matcher(input);
        if(matcher.matches()){
            this.email = matcher.group("email");
            this.domain = matcher.group("domain");
        }
    }
    public static boolean checkFormat(String input){
        return Pattern.compile("^(?<email>[\\S]+)@(?<domain>[\\S]+)\\.com$").matcher(input).matches();
    }
}
