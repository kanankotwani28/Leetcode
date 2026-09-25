import java.util.*;

class Solution {

    private String s;
    private int i;

    public List<String> braceExpansionII(String expression) {
        s = expression;
        i = 0;

        TreeSet<String> ans = parseExpression();
        return new ArrayList<>(ans);
    }

    // expression := term (, term)*
    private TreeSet<String> parseExpression() {
        TreeSet<String> result = parseTerm();

        while (i < s.length() && s.charAt(i) == ',') {
            i++; // skip comma
            result.addAll(parseTerm());
        }

        return result;
    }

    // term := factor factor ...
    private TreeSet<String> parseTerm() {
        TreeSet<String> result = new TreeSet<>();
        result.add("");

        while (i < s.length()
                && s.charAt(i) != '}'
                && s.charAt(i) != ',') {

            TreeSet<String> next = parseFactor();

            TreeSet<String> temp = new TreeSet<>();

            for (String a : result) {
                for (String b : next) {
                    temp.add(a + b);
                }
            }

            result = temp;
        }

        return result;
    }

    // factor := letter | { expression }
    private TreeSet<String> parseFactor() {

        if (Character.isLowerCase(s.charAt(i))) {
            TreeSet<String> set = new TreeSet<>();
            set.add(String.valueOf(s.charAt(i++)));
            return set;
        }

        // '{'
        i++; // skip {

        TreeSet<String> result = parseExpression();

        i++; // skip }

        return result;
    }
}