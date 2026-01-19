class Solution {
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;

        // 1️⃣ Create prefix sum matrix (extra row & column)
        int[][] prefix = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                prefix[i][j] =
                        mat[i - 1][j - 1]
                      + prefix[i - 1][j]
                      + prefix[i][j - 1]
                      - prefix[i - 1][j - 1];
            }
        }

        // 2️⃣ Binary search on square size
        int low = 0, high = Math.min(m, n);

        while (low < high) {
            int mid = (low + high + 1) / 2;

            if (isValid(prefix, m, n, mid, threshold)) {
                low = mid;        // try bigger square
            } else {
                high = mid - 1;   // try smaller square
            }
        }

        return low;
    }

    // 3️⃣ Check if a k x k square exists with sum <= threshold
    private boolean isValid(int[][] prefix, int m, int n, int k, int threshold) {
        for (int i = k; i <= m; i++) {
            for (int j = k; j <= n; j++) {
                int sum =
                        prefix[i][j]
                      - prefix[i - k][j]
                      - prefix[i][j - k]
                      + prefix[i - k][j - k];

                if (sum <= threshold) {
                    return true;
                }
            }
        }
        return false;
    }
}
