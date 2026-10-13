
import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squares = new long[n];

        // Square each element. Negative values become positive too.
        for (int i = 0; i < n; i++) {
            squares[i] = (long) nums[i] * nums[i];
        }

        // Sort the squared values in ascending order.
        Arrays.sort(squares);

        long score = 0;

        // Subtract the smallest floor(n/2) squares.
        for (int i = 0; i < n / 2; i++) {
            score -= squares[i];
        }

        // Add the largest ceil(n/2) squares.
        for (int i = n / 2; i < n; i++) {
            score += squares[i];
        }

        return score;
    }
}
