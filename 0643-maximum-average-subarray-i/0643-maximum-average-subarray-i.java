class Solution {
    public double findMaxAverage(int[] nums, int k) {
           int left=0;
        double sum=0;
        double maxAvg=Double.NEGATIVE_INFINITY;
    for (int i = 0; i < nums.length; i++) {
        sum+=nums[i];
        if(i>k-1){
            sum-=nums[left];
            left++;
        }
        if(i>=k-1) {
            maxAvg = Math.max(sum, maxAvg);
        }
    }
return maxAvg/k;
    }
}