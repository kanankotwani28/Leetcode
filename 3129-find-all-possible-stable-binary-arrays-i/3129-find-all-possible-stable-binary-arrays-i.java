class Solution {

    int MOD = 1_000_000_007;
    int limit;
    Integer[][][][] memo;

    public int numberOfStableArrays(int zero, int one, int limit) {

        this.limit = limit;
        memo = new Integer[zero + 1][one + 1][2][limit + 1];

        long ans = 0;

        if (zero > 0)
            ans += dfs(zero - 1, one, 0, 1);

        if (one > 0)
            ans += dfs(zero, one - 1, 1, 1);

        return (int)(ans % MOD);
    }

    private int dfs(int z, int o, int last, int cnt) {

        if (z == 0 && o == 0)
            return 1;

        if (memo[z][o][last][cnt] != null)
            return memo[z][o][last][cnt];

        long res = 0;

        if (last == 0) {

            if (z > 0 && cnt < limit)
                res += dfs(z - 1, o, 0, cnt + 1);

            if (o > 0)
                res += dfs(z, o - 1, 1, 1);
        } 
        else {

            if (o > 0 && cnt < limit)
                res += dfs(z, o - 1, 1, cnt + 1);

            if (z > 0)
                res += dfs(z - 1, o, 0, 1);
        }

        res %= MOD;

        return memo[z][o][last][cnt] = (int) res;
    }
}