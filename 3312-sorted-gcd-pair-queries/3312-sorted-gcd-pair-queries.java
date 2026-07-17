import java.util.*;

class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int max = 0;
        for (int x : nums) max = Math.max(max, x);

        // Frequency of each value
        int[] freq = new int[max + 1];
        for (int x : nums) freq[x]++;

        // exact[g] = number of pairs with GCD exactly g
        long[] exact = new long[max + 1];

        // Process from largest GCD to smallest
        for (int g = max; g >= 1; g--) {
            long count = 0;

            // Count numbers divisible by g
            for (int multiple = g; multiple <= max; multiple += g) {
                count += freq[multiple];
            }

            // Total pairs divisible by g
            exact[g] = count * (count - 1) / 2;

            // Remove pairs whose GCD is a multiple of g
            for (int multiple = 2 * g; multiple <= max; multiple += g) {
                exact[g] -= exact[multiple];
            }
        }

        // Prefix sums
        long[] prefix = new long[max + 1];
        for (int g = 1; g <= max; g++) {
            prefix[g] = prefix[g - 1] + exact[g];
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            long target = queries[i] + 1; // Convert to 1-based count

            int lo = 1, hi = max;

            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;

                if (prefix[mid] >= target)
                    hi = mid;
                else
                    lo = mid + 1;
            }

            ans[i] = lo;
        }

        return ans;
    }
}