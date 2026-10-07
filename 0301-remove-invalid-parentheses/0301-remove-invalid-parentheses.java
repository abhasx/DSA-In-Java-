class Solution {
    Set<String> ans = new HashSet<>();
    int n;

    public List<String> removeInvalidParentheses(String s) {
        n = s.length();

        int leftRemove = 0;
        int rightRemove = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                leftRemove++;
            } else if (c == ')') {
                if (leftRemove > 0) {
                    leftRemove--;
                } else {
                    rightRemove++;
                }
            }
        }

        dfs(s, 0, leftRemove, rightRemove, 0, 0, "");

        return new ArrayList<>(ans);
    }

    private void dfs(String s, int index, int leftRemove, int rightRemove,
                     int leftCount, int rightCount, String current) {

        if (index == n) {
            if (leftRemove == 0 && rightRemove == 0) {
                ans.add(current);
            }
            return;
        }

        if (leftCount < rightCount) {
            return;
        }

        char c = s.charAt(index);

        if (c == '(' && leftRemove > 0) {
            dfs(s, index + 1, leftRemove - 1, rightRemove,
                    leftCount, rightCount, current);
        }

        if (c == ')' && rightRemove > 0) {
            dfs(s, index + 1, leftRemove, rightRemove - 1,
                    leftCount, rightCount, current);
        }

        if (c == '(') {
            dfs(s, index + 1, leftRemove, rightRemove,
                    leftCount + 1, rightCount, current + c);
        } else if (c == ')') {
            if (leftCount > rightCount) {
                dfs(s, index + 1, leftRemove, rightRemove,
                        leftCount, rightCount + 1, current + c);
            }
        } else {
            dfs(s, index + 1, leftRemove, rightRemove,
                    leftCount, rightCount, current + c);
        }
    }
}