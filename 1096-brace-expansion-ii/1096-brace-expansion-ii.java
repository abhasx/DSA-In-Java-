class Solution {
    private String s;
    private int i;

    public List<String> braceExpansionII(String expression) {
        s = expression;
        i = 0;

        Set<String> result = parseExpression();

        return new ArrayList<>(result.stream().sorted().toList());
    }

    private Set<String> parseExpression() {
        Set<String> result = parseTerm();

        while (i < s.length() && s.charAt(i) == ',') {
            i++;
            result.addAll(parseTerm());
        }

        return result;
    }

    private Set<String> parseTerm() {
        Set<String> result = new HashSet<>();
        result.add("");

        while (i < s.length() && s.charAt(i) != '}' && s.charAt(i) != ',') {
            Set<String> next = parseFactor();

            Set<String> combined = new HashSet<>();

            for (String a : result) {
                for (String b : next) {
                    combined.add(a + b);
                }
            }

            result = combined;
        }

        return result;
    }

    private Set<String> parseFactor() {
        if (s.charAt(i) == '{') {
            i++;
            Set<String> result = parseExpression();
            i++;
            return result;
        }

        Set<String> result = new HashSet<>();
        result.add(String.valueOf(s.charAt(i)));
        i++;

        return result;
    }
}