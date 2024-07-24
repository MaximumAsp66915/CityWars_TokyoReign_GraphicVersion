package model.cards;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SpellType {
    COPY("copy"),
    DEBILITATE("debilitate"),
    HOLE_CHANGE("hole_change"),
    POWER_BOOST("power_boost"),
    REMOVE("remove"),
    REPAIR("repair"),
    SHIELD("shield"),
    HEAL("heal"),
    ROUND_REDUCER("round_reducer") ;

    private final String pattern;

    SpellType(String pattern) {
        this.pattern = pattern;
    }

    public Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile(this.pattern).matcher(input);

        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }
    public static SpellType spellType(String pattern){
        pattern = pattern.toLowerCase();
        if(COPY.pattern.matches(pattern)){
            return COPY ;
        } else if(DEBILITATE.pattern.matches(pattern)){
            return DEBILITATE ;
        } else if(HOLE_CHANGE.pattern.matches(pattern)){
            return HOLE_CHANGE ;
        } else if(POWER_BOOST.pattern.matches(pattern)){
            return POWER_BOOST ;
        } else if(REMOVE.pattern.matches(pattern)){
            return REMOVE ;
        } else if(REPAIR.pattern.matches(pattern)){
            return REPAIR ;
        } else if(SHIELD.pattern.matches(pattern)){
            return SHIELD ;
        } else if(HEAL.pattern.matches(pattern)){
            return HEAL ;
        } else if(ROUND_REDUCER.pattern.matches(pattern)){
            return ROUND_REDUCER ;
        }
        return null;
    }
}
