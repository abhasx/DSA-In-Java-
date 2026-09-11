class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // suf[i] = first index of word2 that cannot be matched
        // using word1[i...n-1] as a subsequence.
        int[] suf = new int[n + 1];
        suf[n] = m;

        int j = m - 1;

        for (int i = n - 1; i >= 0; i--) {
            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                j--;
            }
            suf[i] = j + 1;
        }

        int[] ans = new int[m];
        int size = 0;
        j = 0;
        boolean changed = false;

        for (int i = 0; i < n && size < m; i++) {

            // Normal match
            if (word1.charAt(i) == word2.charAt(j)) {
                ans[size++] = i;
                j++;
            }

            // Use the one allowed modification
            else if (!changed && suf[i + 1] <= j + 1) {
                ans[size++] = i;
                j++;
                changed = true;
            }
        }

        if (size != m) {
            return new int[0];
        }

        return ans;
    }
}