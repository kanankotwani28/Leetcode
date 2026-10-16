class Solution {
    private static final long MOD = 1_000_000_007L;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        int size = 4 * n;

        long[] sum = new long[size];
        long[] sq = new long[size];
        long[] lazy = new long[size];

        int[] last = new int[100001];
        java.util.Arrays.fill(last, -1);

        long ans = 0;

        for (int i = 0; i < n; i++) {
            int x = nums[i];

            // Every subarray ending at i gains x as a distinct
            // element unless x already appeared in that subarray.
            int left = last[x] + 1;

            update(1, 0, n - 1, left, i, 1, sum, sq, lazy);

            // Sum of squared distinct counts for subarrays
            // ending at index i.
            ans = (ans + sq[1]) % MOD;

            last[x] = i;
        }

        return (int) ans;
    }

    private void update(
            int node, int start, int end,
            int l, int r, long val,
            long[] sum, long[] sq, long[] lazy) {

        if (l > end || r < start) {
            return;
        }

        if (l <= start && end <= r) {
            apply(node, start, end, val, sum, sq, lazy);
            return;
        }

        push(node, start, end, sum, sq, lazy);

        int mid = start + (end - start) / 2;

        update(node * 2, start, mid, l, r, val,
               sum, sq, lazy);

        update(node * 2 + 1, mid + 1, end, l, r, val,
               sum, sq, lazy);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sq[node] = (sq[node * 2] + sq[node * 2 + 1]) % MOD;
    }

    private void apply(
            int node, int start, int end, long val,
            long[] sum, long[] sq, long[] lazy) {

        long len = end - start + 1L;

        // (a + val)^2 = a^2 + 2*a*val + val^2
        long addSquare = val * val % MOD * len % MOD;
        long addLinear = (2L * val % MOD) * sum[node] % MOD;

        sq[node] = (sq[node] + addLinear + addSquare) % MOD;
        sum[node] = (sum[node] + val * len) % MOD;
        lazy[node] = (lazy[node] + val) % MOD;
    }

    private void push(
            int node, int start, int end,
            long[] sum, long[] sq, long[] lazy) {

        if (lazy[node] == 0 || start == end) {
            return;
        }

        int mid = start + (end - start) / 2;
        long val = lazy[node];

        apply(node * 2, start, mid, val, sum, sq, lazy);
        apply(node * 2 + 1, mid + 1, end, val, sum, sq, lazy);

        lazy[node] = 0;
    }
}