class Solution {

    class State {
        int row, col, health;

        State(int row, int col, int health) {
            this.row = row;
            this.col = col;
            this.health = health;
        }
    }

    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int n = grid.size();
        int m = grid.get(0).size();
        int startHealth = health - grid.get(0).get(0);

        if (startHealth <= 0) return false;
        int[][] best = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(best[i], -1);
        }

        Queue<State> q = new LinkedList<>();
        q.offer(new State(0, 0, startHealth));
        best[0][0] = startHealth;

        int[] drow = {-1, 0, 1, 0};
        int[] dcol = {0, 1, 0, -1};

        while (!q.isEmpty()) {
            State curr = q.poll();

            int row = curr.row;
            int col = curr.col;
            int currHealth = curr.health;

            if (row == n - 1 && col == m - 1) {
                return true;
            }

            for (int k = 0; k < 4; k++) {
                int nrow = row + drow[k];
                int ncol = col + dcol[k];

                if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m) {

                    int newHealth = currHealth - grid.get(nrow).get(ncol);

                    if (newHealth > 0 && newHealth > best[nrow][ncol]) {
                        best[nrow][ncol] = newHealth;
                        q.offer(new State(nrow, ncol, newHealth));
                    }
                }
            }
        }
        return false;
    }
}