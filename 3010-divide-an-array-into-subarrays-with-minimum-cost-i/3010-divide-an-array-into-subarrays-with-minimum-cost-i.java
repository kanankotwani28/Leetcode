class Solution {
    public int minimumCost(int[] nums) {
        int first = nums[0] ;
        int minS = nums[1];
        int ans = Integer.MAX_VALUE;

        for(int i = 2 ; i < nums.length ; i++){
            ans = Math.min(ans, first + minS + nums[i]);
            minS = Math.min(minS , nums[i]);
        }
    return ans;
    }
}