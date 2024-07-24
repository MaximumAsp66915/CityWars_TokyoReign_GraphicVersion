package controller;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StaticMethods {
    public static String find(String query1 , List<String> dataBase ) {
        if(query1.length()<2){
            return query1;
        }
        Matcher matcher = getMatcher(query1);
        assert matcher != null;
        String query = matcher.group("query");
        Optional<String> closestMatch = dataBase.stream()
                .min(Comparator.comparingInt(s -> levenshteinDistance(s.toLowerCase(), query.toLowerCase())));

        String result = closestMatch.orElse(null);
        if(result == null){
            return "invalid command";
        }
        if(matcher.group("rest").length()>1)
            result = result+matcher.group("rest");
        if(!query1.equals(result))
            System.out.println("COMMAND_CORRECTION : " + result);
        return result;
    }

    private static int levenshteinDistance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = min(dp[i - 1][j - 1] + costOfSubstitution(a.charAt(i - 1), b.charAt(j - 1)),
                            dp[i - 1][j] + 1,
                            dp[i][j - 1] + 1);
                }
            }
        }

        return dp[a.length()][b.length()];
    }

    private static int costOfSubstitution(char a, char b) {return a == b ? 0 : 1;}

    private static int min(int... numbers) {return Arrays.stream(numbers).min().orElse(Integer.MAX_VALUE);}
    public static Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile("(?<query>[\\S]+)(?<rest>.+)").matcher(input);
        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }
}
