import java.util.*;

class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));

        // pos[node] = position of node in sorted order
        int[] pos = new int[n];
        // comp[node] = connected component id
        int[] comp = new int[n];

        int compId = 0;
        for (int i = 0; i < n; i++) {
            if (i > 0 && arr[i][0] - arr[i - 1][0] > maxDiff) {
                compId++;
            }
            pos[arr[i][1]] = i;
            comp[arr[i][1]] = compId;
        }

        // next[i] = farthest sorted position reachable in one jump
        int[] next = new int[n];
        int r = 0;
        for (int i = 0; i < n; i++) {
            while (r + 1 < n && arr[r + 1][0] - arr[i][0] <= maxDiff) {
                r++;
            }
            next[i] = r;
            if (r == i) r++;
        }

        int LOG = 18;
        int[][] up = new int[LOG][n];

        for (int i = 0; i < n; i++) {
            up[0][i] = next[i];
        }

        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                up[k][i] = up[k - 1][up[k - 1][i]];
            }
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];

            if (u == v) {
                ans[i] = 0;
                continue;
            }

            if (comp[u] != comp[v]) {
                ans[i] = -1;
                continue;
            }

            int a = pos[u];
            int b = pos[v];

            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }

            int cur = a;
            int steps = 0;

            for (int k = LOG - 1; k >= 0; k--) {
                if (up[k][cur] < b) {
                    steps += 1 << k;
                    cur = up[k][cur];
                }
            }

            ans[i] = steps + 1;
        }

        return ans;
    }
}