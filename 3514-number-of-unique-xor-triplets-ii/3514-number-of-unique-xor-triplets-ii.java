import java.util.*;

class Solution {
    public int uniqueXorTriplets(int[] nums) {
        final int MAX = 2048;

        boolean[] present = new boolean[MAX];
        List<Integer> values = new ArrayList<>();

        for (int x : nums) {
            if (!present[x]) {
                present[x] = true;
                values.add(x);
            }
        }

        boolean[] pair = new boolean[MAX];

        // All XORs of two values (repetition allowed)
        for (int a : values) {
            for (int b : values) {
                pair[a ^ b] = true;
            }
        }

        boolean[] ans = new boolean[MAX];

        // XOR pair result with third value
        for (int x = 0; x < MAX; x++) {
            if (!pair[x]) continue;
            for (int v : values) {
                ans[x ^ v] = true;
            }
        }

        int count = 0;
        for (boolean b : ans) {
            if (b) count++;
        }

        return count;
    }
}