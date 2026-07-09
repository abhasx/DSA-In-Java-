class Solution {
    public int maxArea(int[] height) {
        int maxarea=0;
        int left=0;
        int right= height.length-1;
        while(left<right){
            int sLine=Math.min(height[left],height[right]);
            int curArea=sLine*(right-left);
            maxarea=Math.max(curArea,maxarea);
         if (height[left]<height[right]){
             left++;
         }else{right--;
             }
        }
   return maxarea; }
}