class Solution {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int dp[][] = new int[n][amount+1];
        for(int i = 0 ; i<=amount ; i++){
            if (i % coins[0] == 0) dp[0][i] = 1;
        }

        for(int i = 1 ; i<n ; i++){
            for(int j = 0 ; j<=amount ; j++){
                int notTaken = dp[i-1][j];
                int taken = 0;
                if(coins[i] <= j) taken = dp[i][j-coins[i]];

                dp[i][j] = taken + notTaken;
            }
        }
        return dp[n-1][amount];
    }
}