class Solution {

    char[] s;
    int[] prefix, suffix, best;
    char[] leftChar, rightChar;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {

        int n = s.length();
        this.s = s.toCharArray();

        prefix = new int[4 * n];
        suffix = new int[4 * n];
        best = new int[4 * n];

        leftChar = new char[4 * n];
        rightChar = new char[4 * n];

        build(1, 0, n - 1);

        int[] ans = new int[queryIndices.length];

        for (int i = 0; i < queryIndices.length; i++) {

            update(
                1,
                0,
                n - 1,
                queryIndices[i],
                queryCharacters.charAt(i)
            );

            ans[i] = best[1];
        }

        return ans;
    }

    private void build(int node, int l, int r) {

        if (l == r) {
            leftChar[node] = rightChar[node] = s[l];
            prefix[node] = suffix[node] = best[node] = 1;
            return;
        }

        int mid = (l + r) / 2;

        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);

        merge(node, node * 2, node * 2 + 1, l, mid, r);
    }

    private void update(int node, int l, int r, int index, char ch) {

        if (l == r) {
            leftChar[node] = rightChar[node] = ch;
            prefix[node] = suffix[node] = best[node] = 1;
            return;
        }

        int mid = (l + r) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index, ch);
        } else {
            update(node * 2 + 1, mid + 1, r, index, ch);
        }

        merge(node, node * 2, node * 2 + 1, l, mid, r);
    }

    private void merge(
            int node,
            int left,
            int right,
            int l,
            int mid,
            int r
    ) {

        leftChar[node] = leftChar[left];
        rightChar[node] = rightChar[right];

       
        best[node] = Math.max(best[left], best[right]);

       
        prefix[node] = prefix[left];

        if (leftChar[left] == leftChar[right]
                && prefix[left] == mid - l + 1) {

            prefix[node] += prefix[right];
        }

    
        suffix[node] = suffix[right];

        if (leftChar[right] == rightChar[left]
                && suffix[right] == r - mid) {

            suffix[node] += suffix[left];
        }

      
        if (rightChar[left] == leftChar[right]) {

            best[node] = Math.max(
                    best[node],
                    suffix[left] + prefix[right]
            );
        }
    }
}