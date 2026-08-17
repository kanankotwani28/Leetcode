import java.util.*;

class Solution {

    int[] tree;
    int[] lazy;

    void push(int node) {
        if (lazy[node] == 0)
            return;

        tree[node * 2] = lazy[node];
        tree[node * 2 + 1] = lazy[node];

        lazy[node * 2] = lazy[node];
        lazy[node * 2 + 1] = lazy[node];

        lazy[node] = 0;
    }

    // Update range [l, r)
    void update(int node, int start, int end,
                int l, int r, int height) {

        // No overlap
        if (r <= start || end <= l)
            return;

        // Complete overlap
        if (l <= start && end <= r) {
            tree[node] = height;
            lazy[node] = height;
            return;
        }

        push(node);

        int mid = (start + end) / 2;

        update(node * 2, start, mid, l, r, height);
        update(node * 2 + 1, mid, end, l, r, height);

        tree[node] = Math.max(
            tree[node * 2],
            tree[node * 2 + 1]
        );
    }

    // Query maximum in range [l, r)
    int query(int node, int start, int end,
              int l, int r) {

        // No overlap
        if (r <= start || end <= l)
            return 0;

        // Complete overlap
        if (l <= start && end <= r)
            return tree[node];

        push(node);

        int mid = (start + end) / 2;

        int left = query(
            node * 2,
            start,
            mid,
            l,
            r
        );

        int right = query(
            node * 2 + 1,
            mid,
            end,
            l,
            r
        );

        return Math.max(left, right);
    }

    public List<Integer> fallingSquares(int[][] positions) {

        // -------------------------------
        // Coordinate Compression
        // -------------------------------

        TreeSet<Integer> set = new TreeSet<>();

        for (int[] p : positions) {

            int left = p[0];
            int right = p[0] + p[1];

            set.add(left);
            set.add(right);
        }

        List<Integer> coords = new ArrayList<>(set);

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < coords.size(); i++) {
            map.put(coords.get(i), i);
        }

        // Number of actual intervals
        int intervals = coords.size() - 1;

        tree = new int[4 * intervals + 5];
        lazy = new int[4 * intervals + 5];

        List<Integer> ans = new ArrayList<>();

        int maxHeight = 0;

        // -------------------------------
        // Process every square
        // -------------------------------

        for (int[] p : positions) {

            int left = p[0];
            int side = p[1];
            int right = left + side;

            int l = map.get(left);
            int r = map.get(right);

            // Find maximum height underneath
            int baseHeight = query(
                1,
                0,
                intervals,
                l,
                r
            );

            // Add current square
            int newHeight = baseHeight + side;

            // Set entire square range
            update(
                1,
                0,
                intervals,
                l,
                r,
                newHeight
            );

            maxHeight = Math.max(maxHeight, newHeight);

            ans.add(maxHeight);
        }

        return ans;
    }
}