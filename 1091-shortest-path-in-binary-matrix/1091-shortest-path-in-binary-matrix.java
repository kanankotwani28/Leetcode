class Solution {
    class Pair{
        int first;
        int second;
        int third;
        Pair(int first , int second , int third){
            this.first = first;
            this.second = second;
            this.third = third;
        }
    }
    public int shortestPathBinaryMatrix(int[][] grid) {
        Queue<Pair> q = new LinkedList<>();
        int n = grid.length;
        int m = grid[0].length;

        if(grid[0][0] == 1 || grid[n-1][n-1] == 1)
            return -1;

        if(n == 1)
            return 1;

        int dis[][] = new int[n][m];
        for(int i = 0 ; i<n; i++){
            for(int j = 0 ; j<m ; j++){
                dis[i][j] = (int)1e9;
            }
        }

        q.add(new Pair(1,0,0));
        dis[0][0] = 1;

        int[] drow = {-1,-1,-1,0,0,1,1,1};
        int[] dcol = {-1,0,1,-1,1,-1,0,1};

        while(!q.isEmpty())
        {
            Pair p = q.poll();
            int dist = p.first;
            int x = p.second;
            int y = p.third;

            for(int i = 0 ; i<8 ; i++){
                int nrow = x + drow[i];
                int ncol = y + dcol[i];

                if(nrow>=0 && nrow<n && ncol>=0 && ncol<m && grid[nrow][ncol] == 0 && dist + 1 < dis[nrow][ncol]){
                    if(nrow == n-1 && ncol == m-1) return dist + 1;
                    q.add(new Pair(dist+1,nrow,ncol));
                    dis[nrow][ncol] = dist + 1;
                }
            }

        }
        return -1;
    }
}