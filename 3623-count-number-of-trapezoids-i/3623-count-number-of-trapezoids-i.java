class Solution {
    static final long MOD = 1_000_000_007;
    public int countTrapezoids(int[][] points) {
         Map<Integer, Integer> countByY = new HashMap<>();
        for (int[] p : points) {
            countByY.put(p[1], countByY.getOrDefault(p[1], 0) + 1);
        }
        List<Long> pairs = new ArrayList<>();
        for (int k : countByY.values()) {
            if (k >= 2) {
                long val = (long) k * (k - 1) / 2;
                pairs.add(val % MOD);
            }
        }
        if (pairs.size() < 2) return 0;
        long sum = 0, sumSq = 0;

        for (long p : pairs) {
            sum = (sum + p) % MOD;
            sumSq = (sumSq + (p * p) % MOD) % MOD;
        }

        long result = ((sum * sum) % MOD - sumSq + MOD) % MOD; 
        result = (result * inv2()) % MOD;

        return (int) result;
    }

    private long inv2() {
        return (MOD + 1) / 2; 
    }
}
    