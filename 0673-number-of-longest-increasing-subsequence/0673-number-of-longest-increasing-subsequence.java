class Solution {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int ans = 0;
        int maxLen = 0;

        int[] dp = new int[n];
        int[] count = new int[n];

        Arrays.fill(dp, 1);
        Arrays.fill(count, 1);

        for(int ind = 0; ind < n; ind++) {
            for(int prevInd = 0; prevInd < ind; prevInd++) {
                if(nums[prevInd] < nums[ind]) {
                    if(dp[prevInd] + 1 > dp[ind]) {
                        dp[ind] = dp[prevInd] + 1;
                        count[ind] = count[prevInd];
                    }
   
                    else if(dp[prevInd] + 1 == dp[ind]) {
                        count[ind] += count[prevInd];
                    }

                }
            }
            maxLen = Math.max(maxLen, dp[ind]);
        }
        for(int i = 0; i < n; i++) {
            if(dp[i] == maxLen) ans += count[i];
        }
        return ans;
    }
}