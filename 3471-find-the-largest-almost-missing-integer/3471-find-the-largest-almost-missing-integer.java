import java.util.*;

class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;

        // Case 1: k = 1
        if (k == 1) {
            HashMap<Integer, Integer> freq = new HashMap<>();

            for (int num : nums) {
                freq.put(num, freq.getOrDefault(num, 0) + 1);
            }

            int ans = -1;

            for (int num : freq.keySet()) {
                if (freq.get(num) == 1) {
                    ans = Math.max(ans, num);
                }
            }

            return ans;
        }

        // Case 2: k = n
        if (k == n) {
            int ans = nums[0];

            for (int num : nums) {
                ans = Math.max(ans, num);
            }

            return ans;
        }

        // Case 3: 1 < k < n
        int ans = -1;

        // Check first element
        boolean firstUnique = true;

        for (int i = 1; i < n; i++) {
            if (nums[i] == nums[0]) {
                firstUnique = false;
                break;
            }
        }

        if (firstUnique) {
            ans = Math.max(ans, nums[0]);
        }

        // Check last element
        boolean lastUnique = true;

        for (int i = 0; i < n - 1; i++) {
            if (nums[i] == nums[n - 1]) {
                lastUnique = false;
                break;
            }
        }

        if (lastUnique) {
            ans = Math.max(ans, nums[n - 1]);
        }

        return ans;
    }
}