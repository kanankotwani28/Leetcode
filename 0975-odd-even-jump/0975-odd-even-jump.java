
import java.util.*;

class Solution {
    public int oddEvenJumps(int[] arr) {
        int n = arr.length;

        // nextHigher[i]: destination of an odd jump from i.
        // nextLower[i]: destination of an even jump from i.
        int[] nextHigher = new int[n];
        int[] nextLower = new int[n];

        Arrays.fill(nextHigher, -1);
        Arrays.fill(nextLower, -1);

        // Sort indices by value ascending, then by index ascending.
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        Arrays.sort(indices, (i, j) -> {
            if (arr[i] != arr[j]) {
                return Integer.compare(arr[i], arr[j]);
            }
            return Integer.compare(i, j);
        });

        // Build nextHigher using a monotonic stack of indices.
        int[] stack = new int[n];
        int top = -1;

        for (int idx : indices) {
            // The current index is to the right of these indices.
            // Because of sorting, it is their correct next higher choice.
            while (top >= 0 && stack[top] < idx) {
                nextHigher[stack[top--]] = idx;
            }

            stack[++top] = idx;
        }

        // Sort indices by value descending, then by index ascending.
        Arrays.sort(indices, (i, j) -> {
            if (arr[i] != arr[j]) {
                return Integer.compare(arr[j], arr[i]);
            }
            return Integer.compare(i, j);
        });

        // Build nextLower using the same monotonic-stack technique.
        top = -1;

        for (int idx : indices) {
            while (top >= 0 && stack[top] < idx) {
                nextLower[stack[top--]] = idx;
            }

            stack[++top] = idx;
        }

        // odd[i]: can reach the end when the next jump is odd.
        // even[i]: can reach the end when the next jump is even.
        boolean[] odd = new boolean[n];
        boolean[] even = new boolean[n];

        // The destination can always reach itself (zero more jumps).
        odd[n - 1] = true;
        even[n - 1] = true;

        int answer = 1; // Starting at the last index is always good.

        // Work backwards because every jump moves to a larger index.
        for (int i = n - 2; i >= 0; i--) {
            if (nextHigher[i] != -1) {
                odd[i] = even[nextHigher[i]];
            }

            if (nextLower[i] != -1) {
                even[i] = odd[nextLower[i]];
            }

            if (odd[i]) {
                answer++;
            }
        }

        return answer;
    }
}
