class Pair {
    int row;
    int col;
    Pair(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int n = image.length;
        int m = image[0].length;

        int originalColor = image[sr][sc];
        if (originalColor == color) return image;

        Queue<Pair> q = new LinkedList<>();
        int[][] vis = new int[n][m];

        q.add(new Pair(sr, sc));
        vis[sr][sc] = 1;
        image[sr][sc] = color;

        int[] drow = {-1, 0, 1, 0};
        int[] dcol = {0, -1, 0, 1};

        while (!q.isEmpty()) {
            Pair p = q.poll();
            int r = p.row;
            int c = p.col;

            for (int i = 0; i < 4; i++) {
                int nrow = r + drow[i];
                int ncol = c + dcol[i];

                if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m &&
                    vis[nrow][ncol] == 0 &&
                    image[nrow][ncol] == originalColor) {

                    q.add(new Pair(nrow, ncol));
                    vis[nrow][ncol] = 1;
                    image[nrow][ncol] = color;
                }
            }
        }
        return image;
    }
}