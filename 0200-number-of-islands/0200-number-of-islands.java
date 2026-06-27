class Solution {
    class Pair{
        int row ; int col;
        Pair(int row , int col){
            this.row = row;
            this.col = col;
        }
    }
    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        Queue<Pair> q = new LinkedList<>();
        int vis[][] = new int[n][m];
        int islandCnt = 0 ;

        for(int i = 0 ; i< n ; i++){
            for(int j = 0 ; j< m ; j++){

                if(grid[i][j] == '1' && vis[i][j] == 0){
                    islandCnt ++;
                    q.add(new Pair(i,j));
                    vis[i][j] = 1;
                    int[]drow = {-1,0,1,0};
                    int[]dcol = {0,1,0,-1};

                    while(!q.isEmpty()){
                        Pair p = q.poll();
                        int row = p.row;
                        int col = p.col;

                        for(int k = 0 ; k<4 ; k++){
                            int nrow = row + drow[k];
                            int ncol = col + dcol[k];

                            if(nrow>=0 && nrow<n && ncol>=0 && ncol<m && grid[nrow][ncol] == '1' && vis[nrow][ncol] == 0){
                                vis[nrow][ncol] = 1;
                                q.add(new Pair(nrow,ncol));
                            }
                        }
                    }
                }
            }
        }
        return islandCnt;
    }
}