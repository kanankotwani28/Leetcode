class Solution {
    private static final int MOD = (int)1e9 + 7;
    private int func(int nums[] , int target){
        int n = nums.length;
        int dp[][] = new int[n][target+1];
        if(nums[0] == 0) dp[0][0] = 2;
        else dp[0][0] = 1;
        if(nums[0] != 0 && nums[0] <= target) dp[0][nums[0]] = 1;
        for(int i = 1 ; i< n ; i++){
            for(int j = 0 ; j<=target ; j++){
                int notTaken = dp[i-1][j];
                int taken = 0;
                if(nums[i] <= j)
                    taken = dp[i-1][j-nums[i]];
                dp[i][j] = (notTaken+taken) % MOD;
            }
        }
        return dp[n-1][target];
    }
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        int totSum = 0;
        for(int num : nums){
            totSum +=num;
        }

        if(totSum - target < 0 || (totSum - target)%2 != 0) return 0;
        return func(nums,(totSum - target) / 2);
    }
}