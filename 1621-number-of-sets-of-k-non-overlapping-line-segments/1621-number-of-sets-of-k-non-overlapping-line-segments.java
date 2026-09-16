
class Solution {
    public int numberOfSets(int n, int k) {
        int MOD = 1_000_000_007;

        int[][] dp = new int[n][k + 1];
        int[][] prefix = new int[n][k + 1];

        // Base case: zero segments
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
            prefix[i][0] = (i + 1) % MOD;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= k; j++) {

                // Do not use point i
                long ways = dp[i - 1][j];

                // Make the last segment end at i
                ways += prefix[i - 1][j - 1];

                dp[i][j] = (int) (ways % MOD);

                // Update prefix sum
                prefix[i][j] =
                    (int) ((prefix[i - 1][j] + (long) dp[i][j]) % MOD);
            }
        }

        return dp[n - 1][k];
    }
}