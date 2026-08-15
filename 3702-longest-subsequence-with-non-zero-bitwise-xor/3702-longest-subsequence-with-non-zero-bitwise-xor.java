class Solution {
    public int longestSubsequence(int[] nums) {
        int xor = 0;
        int zeroCount = 0;
        int n = nums.length;

        for (int num : nums) {
            xor ^= num;

            if (num == 0) {
                zeroCount++;
            }
        }

        if (xor != 0) {
            return n;
        }

        if (zeroCount == n) {
            return 0;
        }

        return n - 1;
    }
}