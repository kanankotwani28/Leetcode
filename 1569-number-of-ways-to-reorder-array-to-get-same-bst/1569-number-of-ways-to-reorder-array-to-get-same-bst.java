
import java.util.*;

class Solution {
    private static final long MOD = 1_000_000_007L;
    private long[][] comb;

    public int numOfWays(int[] nums) {
        int n = nums.length;

        // Precompute combinations using Pascal's Triangle.
        comb = new long[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            comb[i][0] = comb[i][i] = 1;

            for (int j = 1; j < i; j++) {
                comb[i][j] =
                    (comb[i - 1][j - 1] + comb[i - 1][j]) % MOD;
            }
        }

        List<Integer> arr = new ArrayList<>();
        for (int num : nums) {
            arr.add(num);
        }

        // Subtract the original ordering.
        return (int) ((solve(arr) - 1 + MOD) % MOD);
    }

    private long solve(List<Integer> nums) {
        int n = nums.size();

        // A subtree with fewer than 3 nodes has one valid ordering.
        if (n <= 2) {
            return 1;
        }

        int root = nums.get(0);

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        // Divide elements according to BST properties.
        for (int i = 1; i < n; i++) {
            if (nums.get(i) < root) {
                left.add(nums.get(i));
            } else {
                right.add(nums.get(i));
            }
        }

        long leftWays = solve(left);
        long rightWays = solve(right);

        // Interleave left and right subtree elements.
        long interleavings = comb[n - 1][left.size()];

        return (((interleavings * leftWays) % MOD)
                    * rightWays) % MOD;
    }
}
