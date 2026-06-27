class Solution {
    class Pair{
        int row;
        int col;
        Pair(int row , int col){
            this.row = row;
            this.col = col;
        }
    }
    public void solve(char[][] board) {
        int n = board.length;
        int m = board[0].length;

        int vis[][] = new int[n][m];
        Queue<Pair> q = new LinkedList<>();

        for(int i = 0 ; i< n ; i++){
            if(board[i][0] == 'O'){
                vis[i][0] = 1;
                q.add(new Pair(i,0));
            }
        }

        for(int i = 0 ; i< n ; i++){
            if(board[i][m-1] == 'O'){
                vis[i][m-1] = 1;
                q.add(new Pair(i,m-1));
            }
        }

        for(int j = 0 ; j< m ; j++){
            if(board[0][j] == 'O'){
                vis[0][j] = 1;
                q.add(new Pair(0,j));
            }
        }

        for(int j = 0 ; j< m ; j++){
            if(board[n-1][j] == 'O'){
                vis[n-1][j] = 1;
                q.add(new Pair(n-1,j));
            }
        }


        int []drow = {-1,0,1,0};
        int []dcol = {0,1,0,-1};

        while(!q.isEmpty()){
            Pair p = q.poll();
            int row = p.row;
            int col = p.col;

            for(int i = 0 ; i<4 ; i++){
                int nrow = row + drow[i];
                int ncol = col + dcol[i];
                if(nrow>=0 && nrow<n && ncol>=0 && ncol<m && board[nrow][ncol] == 'O' && vis[nrow][ncol] == 0){
                    vis[nrow][ncol] = 1;
                    q.add(new Pair(nrow,ncol));
                    board[nrow][ncol] = 'O';
                }
            }
        }

        for(int i = 0 ; i< n ; i++){
            for(int j = 0 ; j<m ; j++){
                if(vis[i][j] == 0){
                    board[i][j] = 'X';
                }
            }
        }
    }
}