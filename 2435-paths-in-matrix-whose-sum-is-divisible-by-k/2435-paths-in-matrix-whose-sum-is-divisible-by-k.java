class Solution {
    public int numberOfPaths(int[][] grid, int k) {
        int MOD = 1_000_000_007;
        int m = grid.length, n = grid[0].length;

        int[][] dp = new int[n][k];

        dp[0][grid[0][0] % k] = 1;

        for (int i = 0; i < m; i++) {
            int[][] newDp = new int[n][k];

            for (int j = 0; j < n; j++) {

                if (i > 0) {
                    for (int r = 0; r < k; r++) {
                        if (dp[j][r] > 0) {
                            int newR = (r + grid[i][j]) % k;
                            newDp[j][newR] = (newDp[j][newR] + dp[j][r]) % MOD;
                        }
                    }
                }

                if (j > 0) {
                    for (int r = 0; r < k; r++) {
                        if (newDp[j-1][r] > 0) {
                            int newR = (r + grid[i][j]) % k;
                            newDp[j][newR] = (newDp[j][newR] + newDp[j-1][r]) % MOD;
                        }
                    }
                }

                if (i == 0 && j == 0) {
                    newDp[0][grid[0][0] % k] = 1;
                }
            }

            dp = newDp;
        }

        return dp[n - 1][0];
    }
}
