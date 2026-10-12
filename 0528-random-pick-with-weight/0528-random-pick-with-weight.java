import java.util.Random;
class Solution {
    int[] prefix;
    int total;
    Random random = new Random();

    public Solution(int[] w) {
        int n = w.length;
        prefix = new int[n];
        total = 0;

        for (int i = 0; i < n; i++) {
            total += w[i];
            prefix[i] = total;
        }
    }

    public int pickIndex() {
        int target = random.nextInt(total) + 1;
        int low = 0;
        int high = prefix.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;

            if (prefix[mid] >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */