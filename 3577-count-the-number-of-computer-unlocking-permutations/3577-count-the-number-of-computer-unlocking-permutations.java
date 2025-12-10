class Solution {
    private static final int MOD = 1_000_000_007;

    public int countPermutations(int[] complexity) {
        int n = complexity.length;

        // 1) Check if all other computers can be unlocked by 0 at least
        for (int i = 1; i < n; i++) {
            if (complexity[i] <= complexity[0]) {
                // If any complexity[i] <= complexity[0],
                // computer i can NEVER be unlocked
                return 0;
            }
        }

        // 2) All complexity[i] > complexity[0] for i>0
        // Any permutation of 1..n-1 is valid -> (n-1)! % MOD
        long fact = 1;
        for (int i = 1; i <= n - 1; i++) {
            fact = (fact * i) % MOD;
        }

        return (int) fact;
    }
}
