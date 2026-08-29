import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;

        Integer[] indices = new Integer[n];

        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        Arrays.sort(indices, (a, b) -> Integer.compare(nums[a], nums[b]));

        int[] result = new int[n];

        int i = 0;

        while (i < n) {
            int j = i + 1;

            while (j < n &&
                   nums[indices[j]] - nums[indices[j - 1]] <= limit) {
                j++;
            }

            Integer[] groupIndices = Arrays.copyOfRange(indices, i, j);

            Arrays.sort(groupIndices);

            for (int k = 0; k < groupIndices.length; k++) {
                result[groupIndices[k]] = nums[indices[i + k]];
            }

            i = j;
        }

        return result;
    }
}