import java.util.*;

class Solution {

    private static class SparseTable {
        private final int[][] st;

        SparseTable(int[] nums, int n) {
            int k = 32 - Integer.numberOfLeadingZeros(Math.max(n, 1));
            st = new int[k + 1][];
            st[0] = Arrays.copyOf(nums, n);

            for (int j = 1; j <= k; j++) {
                int half = 1 << (j - 1);
                int len = n - (1 << j) + 1;
                if (len <= 0) break;

                st[j] = new int[len];
                for (int i = 0; i < len; i++) {
                    st[j][i] = Math.max(st[j - 1][i], st[j - 1][i + half]);
                }
            }
        }

        int query(int l, int r) {
            int len = r - l + 1;
            int i = 31 - Integer.numberOfLeadingZeros(len);
            return Math.max(st[i][l], st[i][r - (1 << i) + 1]);
        }
    } // <-- Missing brace was here

    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        int ones = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') ones++;
        }

        List<int[]> groups = new ArrayList<>();
        int[] groupIndex = new int[n];

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                if (i > 0 && s.charAt(i - 1) == '0') {
                    groups.get(groups.size() - 1)[1]++;
                } else {
                    groups.add(new int[]{i, 1});
                }
            }
            groupIndex[i] = groups.size() - 1;
        }

        List<Integer> ans = new ArrayList<>();

        if (groups.isEmpty()) {
            for (int i = 0; i < queries.length; i++) {
                ans.add(ones);
            }
            return ans;
        }

        int g = groups.size();
        int[] adj = new int[Math.max(g - 1, 1)];

        for (int k = 0; k + 1 < g; k++) {
            adj[k] = groups.get(k)[1] + groups.get(k + 1)[1];
        }

        SparseTable st = (g - 1 > 0) ? new SparseTable(adj, g - 1) : null;

        for (int[] query : queries) {
            int l = query[0];
            int r = query[1];

            int left = -1;
            if (groupIndex[l] != -1) {
                int[] grp = groups.get(groupIndex[l]);
                left = grp[1] - (l - grp[0]);
            }

            int right = -1;
            if (groupIndex[r] != -1) {
                int[] grp = groups.get(groupIndex[r]);
                right = r - grp[0] + 1;
            }

            int rGroupBound = (s.charAt(r) == '1') ? groupIndex[r] : groupIndex[r] - 1;
            int startAdjIdx = groupIndex[l] + 1;
            int endAdjIdx = rGroupBound - 1;

            int active = ones;

            if (s.charAt(l) == '0' && s.charAt(r) == '0' && groupIndex[l] + 1 == groupIndex[r]) {
                active = Math.max(active, ones + left + right);
            } else if (startAdjIdx <= endAdjIdx) {
                active = Math.max(active, ones + st.query(startAdjIdx, endAdjIdx));
            }

            if (s.charAt(l) == '0' && groupIndex[l] + 1 <= rGroupBound) {
                active = Math.max(active, ones + left + groups.get(groupIndex[l] + 1)[1]);
            }

            if (s.charAt(r) == '0' && groupIndex[l] < groupIndex[r] - 1) {
                active = Math.max(active, ones + right + groups.get(groupIndex[r] - 1)[1]);
            }

            ans.add(active);
        }

        return ans;
    }
}