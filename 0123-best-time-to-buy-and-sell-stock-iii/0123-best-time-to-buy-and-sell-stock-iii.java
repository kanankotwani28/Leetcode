class Solution {
    private int func(int[] arr , int n ){
        int[][][] dp = new int[n + 1][2][3];
        for (int ind = n - 1; ind >= 0; ind--) {
             for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= 2; cap++) {
                    if (buy == 0) { 
                        dp[ind][buy][cap] = Math.max(0 + dp[ind + 1][0][cap],
                                                     (-1)*arr[ind] + dp[ind + 1][1][cap]);
                    }
                     if (buy == 1) { 
                        dp[ind][buy][cap] = Math.max(0 + dp[ind + 1][1][cap],
                                                     arr[ind] + dp[ind + 1][0][cap - 1]);
                    }
                }
            }
        }
        return dp[0][0][2];
    }
    public int maxProfit(int[] arr) {
        int n = arr.length;
        return func(arr, n);
    }
}