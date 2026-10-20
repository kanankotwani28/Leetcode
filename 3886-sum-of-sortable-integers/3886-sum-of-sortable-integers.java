class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;

        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int ans = 0;

        // Only divisors of n are valid candidates.
        for (int k = 1; k <= n; k++) {
            if (n % k != 0) {
                continue;
            }

            boolean valid = true;

            // Check every block of length k.
            for (int start = 0; start < n; start += k) {
                if (!isRotation(nums, sorted, start, k)) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                ans += k;
            }
        }

        return ans;
    }

    private boolean isRotation(int[] nums, int[] sorted,
                               int start, int k) {
        // Build the KMP prefix table for the sorted block.
        int[] lps = new int[k];
        int len = 0;

        for (int i = 1; i < k; i++) {
            while (len > 0 &&
                   sorted[start + i] != sorted[start + len]) {
                len = lps[len - 1];
            }

            if (sorted[start + i] == sorted[start + len]) {
                len++;
            }

            lps[i] = len;
        }

        // Search for the sorted block in the original block
        // repeated twice, using only indices (no extra array).
        int j = 0;

        for (int i = 0; i < 2 * k - 1; i++) {
            int current = nums[start + (i % k)];

            while (j > 0 && current != sorted[start + j]) {
                j = lps[j - 1];
            }

            if (current == sorted[start + j]) {
                j++;
            }

            if (j == k) {
                return true;
            }
        }

        return false;
    }
}