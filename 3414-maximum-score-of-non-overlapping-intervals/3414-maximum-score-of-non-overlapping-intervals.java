
import java.util.*;

class Solution {
    private int[][] intervals;
    private int[] next;
    private long[][] memo;
    private int[][][] chosen;
    private int n;

    public int[] maximumWeight(List<List<Integer>> input) {
        n = input.size();

        intervals = new int[n][4];

        for (int i = 0; i < n; i++) {
            List<Integer> cur = input.get(i);

            intervals[i][0] = cur.get(0); // start
            intervals[i][1] = cur.get(1); // end
            intervals[i][2] = cur.get(2); // weight
            intervals[i][3] = i;          // original index
        }

        // Sort by start, then end, then weight
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0])
                return Integer.compare(a[0], b[0]);
            if (a[1] != b[1])
                return Integer.compare(a[1], b[1]);
            return Integer.compare(a[2], b[2]);
        });

        int[] starts = new int[n];

        for (int i = 0; i < n; i++) {
            starts[i] = intervals[i][0];
        }

        // Precompute next compatible interval
        next = new int[n];

        for (int i = 0; i < n; i++) {
            next[i] = upperBound(starts, intervals[i][1]);
        }

        memo = new long[n + 1][5];
        chosen = new int[n + 1][5][];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(memo[i], -1);
        }

        return solve(0, 4).indices;
    }

    // First index whose start > end
    private int upperBound(int[] starts, int end) {
        int left = 0, right = starts.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (starts[mid] <= end) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private Result solve(int i, int k) {
        if (i == n || k == 0) {
            return new Result(0, new int[0]);
        }

        if (memo[i][k] != -1) {
            return new Result(memo[i][k], chosen[i][k]);
        }

        // Option 1: Skip current interval
        Result skip = solve(i + 1, k);

        // Option 2: Take current interval
        Result remaining = solve(next[i], k - 1);

        long takeScore = intervals[i][2] + remaining.score;

        int[] takeIndices = Arrays.copyOf(
            remaining.indices,
            remaining.indices.length + 1
        );

        takeIndices[takeIndices.length - 1] = intervals[i][3];

        // Sort original indices for lexicographical comparison
        Arrays.sort(takeIndices);

        Result take = new Result(takeScore, takeIndices);

        // Choose the better result
        Result best;

        if (take.score > skip.score) {
            best = take;
        } else if (take.score < skip.score) {
            best = skip;
        } else {
            best = compare(take.indices, skip.indices) < 0
                    ? take : skip;
        }

        memo[i][k] = best.score;
        chosen[i][k] = best.indices;

        return best;
    }

    private int compare(int[] a, int[] b) {
        int len = Math.min(a.length, b.length);

        for (int i = 0; i < len; i++) {
            if (a[i] != b[i]) {
                return Integer.compare(a[i], b[i]);
            }
        }

        return Integer.compare(a.length, b.length);
    }

    private static class Result {
        long score;
        int[] indices;

        Result(long score, int[] indices) {
            this.score = score;
            this.indices = indices;
        }
    }
}