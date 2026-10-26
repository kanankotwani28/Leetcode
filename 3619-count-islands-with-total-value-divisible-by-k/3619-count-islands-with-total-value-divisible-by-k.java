class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        boolean[][] vis = new boolean[m][n];
        int ans = 0;

        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0 && !vis[i][j]) {
                    long sum = 0;
                    ArrayDeque<int[]> stack = new ArrayDeque<>();

                    stack.push(new int[] {i, j});
                    vis[i][j] = true;

                    while (!stack.isEmpty()) {
                        int[] cur = stack.pop();
                        int r = cur[0], c = cur[1];

                        sum += grid[r][c];

                        for (int d = 0; d < 4; d++) {
                            int nr = r + dr[d];
                            int nc = c + dc[d];

                            if (nr >= 0 && nr < m &&
                                nc >= 0 && nc < n &&
                                grid[nr][nc] > 0 &&
                                !vis[nr][nc]) {

                                vis[nr][nc] = true;
                                stack.push(new int[] {nr, nc});
                            }
                        }
                    }

                    if (sum % k == 0) {
                        ans++;
                    }
                }
            }
        }

        return ans;
    }
}