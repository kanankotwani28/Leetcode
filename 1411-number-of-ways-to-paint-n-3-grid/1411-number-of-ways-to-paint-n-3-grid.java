class Solution {
    public int numOfWays(int n) {
        final int MOD = 1_000_000_007;

        // dpA -> patterns of type ABC (all different)
        // dpB -> patterns of type ABA (first and third same)
        long dpA = 6;
        long dpB = 6;

        for (int i = 2; i <= n; i++) {
            long newA = (2 * dpA + 2 * dpB) % MOD;
            long newB = (2 * dpA + 3 * dpB) % MOD;

            dpA = newA;
            dpB = newB;
        }

        return (int)((dpA + dpB) % MOD);
    }
}