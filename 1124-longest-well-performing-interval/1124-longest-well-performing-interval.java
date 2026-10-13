
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int longestWPI(int[] hours) {
        Map<Integer, Integer> firstIndex = new HashMap<>();

        int prefix = 0;
        int maxLength = 0;

        for (int i = 0; i < hours.length; i++) {

            // Tiring day contributes +1; otherwise, -1.
            prefix += (hours[i] > 8) ? 1 : -1;

            // If the prefix sum is positive, [0..i] is valid.
            if (prefix > 0) {
                maxLength = i + 1;
            } else {
                // Need an earlier prefix strictly smaller than prefix.
                // Since prefix is non-positive, prefix - 1 is smaller.
                if (firstIndex.containsKey(prefix - 1)) {
                    int start = firstIndex.get(prefix - 1);
                    maxLength = Math.max(maxLength, i - start);
                }
            }

            // Store only the first occurrence of each prefix sum.
            // An earlier occurrence gives a longer interval.
            firstIndex.putIfAbsent(prefix, i);
        }

        return maxLength;
    }
}
