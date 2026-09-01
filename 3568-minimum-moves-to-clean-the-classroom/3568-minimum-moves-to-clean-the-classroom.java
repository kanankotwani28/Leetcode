import java.util.*;

class Solution {

    static class State {
        int r, c;
        int mask;
        int energy;
        int moves;

        State(int r, int c, int mask, int energy, int moves) {
            this.r = r;
            this.c = c;
            this.mask = mask;
            this.energy = energy;
            this.moves = moves;
        }
    }

    public int minMoves(String[] classroom, int energy) {

        int m = classroom.length;
        int n = classroom[0].length();

        int startR = -1;
        int startC = -1;

        // Give every litter an ID
        int[][] litterId = new int[m][n];

        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }

        int litterCount = 0;

        // Find S and number the L cells
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    startR = i;
                    startC = j;
                }

                if (ch == 'L') {
                    litterId[i][j] = litterCount++;
                }
            }
        }

        // No litter
        if (litterCount == 0) {
            return 0;
        }

        // Example: 3 litter -> 111
        int fullMask = (1 << litterCount) - 1;

        /*
         * best[r][c][mask]
         *
         * Maximum energy with which we have reached
         * cell (r,c) after collecting litter represented
         * by mask.
         */
        int[][][] best = new int[m][n][1 << litterCount];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(best[i][j], -1);
            }
        }

        Queue<State> queue = new LinkedList<>();

        // Starting state
        best[startR][startC][0] = energy;

        queue.offer(
            new State(startR, startC, 0, energy, 0)
        );

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {

            State current = queue.poll();

            int r = current.r;
            int c = current.c;
            int mask = current.mask;
            int currentEnergy = current.energy;
            int moves = current.moves;

            // All litter collected
            if (mask == fullMask) {
                return moves;
            }

            /*
             * If energy is 0 and we are NOT on R,
             * we cannot make another move.
             */
            if (currentEnergy == 0 &&
                classroom[r].charAt(c) != 'R') {
                continue;
            }

            // Try all 4 directions
            for (int d = 0; d < 4; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];

                // Outside grid
                if (nr < 0 || nr >= m ||
                    nc < 0 || nc >= n) {
                    continue;
                }

                char nextCell = classroom[nr].charAt(nc);

                // Obstacle
                if (nextCell == 'X') {
                    continue;
                }

                // Need 1 energy to move
                if (currentEnergy == 0) {
                    continue;
                }

                // Moving costs 1 energy
                int newEnergy = currentEnergy - 1;

                // Copy current mask
                int newMask = mask;

                // If we find litter, collect it
                if (nextCell == 'L') {

                    int id = litterId[nr][nc];

                    newMask = mask | (1 << id);
                }

                // Reset energy at R
                if (nextCell == 'R') {
                    newEnergy = energy;
                }

                /*
                 * If we have already reached this
                 * cell + mask with MORE energy,
                 * this state is useless.
                 */
                if (newEnergy <= best[nr][nc][newMask]) {
                    continue;
                }

                best[nr][nc][newMask] = newEnergy;

                queue.offer(
                    new State(
                        nr,
                        nc,
                        newMask,
                        newEnergy,
                        moves + 1
                    )
                );
            }
        }

        // Impossible to collect all litter
        return -1;
    }
}