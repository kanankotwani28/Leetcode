class Solution {
    class Tuple{
        int differ;
        int row;
        int col;
        Tuple(int differ , int row , int col){
            this.differ = differ;
            this.row = row;
            this.col = col;
        }
    }
    public int minimumEffortPath(int[][] heights) {
        PriorityQueue<Tuple> pq = new PriorityQueue<Tuple>((x,y) -> x.differ - y.differ);
        int n = heights.length;
        int m = heights[0].length;

        int dis[][] = new int[n][m];
        pq.add(new Tuple(0,0,0));
        dis[0][0] = 0;

        for(int i = 0 ; i<n ; i++){
            for(int j = 0 ; j<m ; j++){
                dis[i][j] = (int)(1e9);
            }
        }

        int []drow = {-1,0,1,0};
        int []dcol = {0,-1,0,1};

        while(pq.size()!=0){
            Tuple p = pq.poll();
            int diff = p.differ;
            int row = p.row;
            int col = p.col;

            if(row == n-1 && col == m-1) return diff;

            for(int i = 0 ; i<4 ; i++){
                int nrow = row + drow[i];
                int ncol = col + dcol[i];

                if(nrow >= 0 && nrow< n && ncol>=0 && ncol <m){
                    int maxEffort = Math.max(diff , Math.abs(heights[nrow][ncol] - heights[row][col]));
                    if(maxEffort < dis[nrow][ncol]){
                        dis[nrow][ncol] = maxEffort;
                        pq.add(new Tuple(maxEffort,nrow,ncol));
                    }
                }
            }
        }
        return 0;
    }
}