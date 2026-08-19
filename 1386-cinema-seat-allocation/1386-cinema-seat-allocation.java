import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        // Store reserved seats for only the rows that have reservations
        Map<Integer, Integer> map = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            // Set the bit corresponding to the seat
            map.put(row, map.getOrDefault(row, 0) | (1 << col));
        }

        // Every completely empty row can accommodate 2 groups
        int ans = 2 * n;

        // Masks for the three possible blocks
        int left = (1 << 2) | (1 << 3) | (1 << 4) | (1 << 5);
        int middle = (1 << 4) | (1 << 5) | (1 << 6) | (1 << 7);
        int right = (1 << 6) | (1 << 7) | (1 << 8) | (1 << 9);

        // Only rows with reserved seats need to be checked
        for (int mask : map.values()) {

            // This row was initially counted as 2 groups.
            // We will replace that count with the actual value.

            if ((mask & left) == 0 && (mask & right) == 0) {
                // Both left and right blocks are available
                // => still 2 groups
            }
            else if ((mask & left) == 0 ||
                     (mask & middle) == 0 ||
                     (mask & right) == 0) {

                // At least one block is available
                // => 1 group
                ans--;
            }
            else {
                // No block is available
                // => 0 groups
                ans -= 2;
            }
        }

        return ans;
    }
}