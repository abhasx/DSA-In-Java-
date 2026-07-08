class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>>result=new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length-2; i++) {//in last iteration two places remain for j,k
            if(i>0&&nums[i]==nums[i-1]){
                continue;
            }
            int j=i+1;//left
            int k= nums.length-1;//right
            while(j<k){//pointers don't cross each other
                int sum=nums[i]+nums[j]+nums[k];
                if(sum==0){
                    result.add(List.of(nums[i],nums[j],nums[k]));
                    j++;
                    k--;
                    while(j<k&&nums[j]==nums[j-1]){//skip duplicate of j in left to right
                        j++;
                    }
                    while(j<k&&nums[k]==nums[k+1]){//skip duplicate of k in right to left
                        k--;
                    }
                } else if (sum>0) {
                    k--;
                }else{
                    j++;
                }
            }
        }
       return result;}
}
