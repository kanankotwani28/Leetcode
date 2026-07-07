class Solution {
    private int coinCount(int[]coins ,int[][]dp, int n , int amount){
       if(n == 0){
        if( amount % coins[0] == 0) return amount / coins[0];
        return (int)1e9;
       }
       if(dp[n][amount] != -1) return dp[n][amount];
       int notTake = 0 + coinCount(coins,dp,n-1,amount);
       int take = (int)1e9;
       if(coins[n] <= amount){
            take = 1 + coinCount(coins,dp,n,amount - coins[n]);
       }
       return dp[n][amount] = Math.min(notTake,take);
    }
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int dp[][] = new int[n][amount+1];
        for(int []row : dp){
            Arrays.fill(row,-1);
        }
        int ans = coinCount(coins ,dp,n-1,amount);
        if(ans == (int)1e9) return -1;
        return ans;
    }
}