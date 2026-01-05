class Solution {
    public long maxMatrixSum(int[][] matrix) {
        long sum = 0;
        int negCnt = 0 ;
        int minAbs = Integer.MAX_VALUE;

        for(int i = 0 ; i < matrix.length ; i++)
        {
            for(int j = 0 ; j < matrix[0].length ; j++){
                int v = matrix[i][j];

                if(v < 0) negCnt ++;

                sum += Math.abs(v);
                minAbs = Math.min(minAbs, Math.abs(v));
            }
        }

        if(negCnt%2 == 1){
            sum -= 2L * minAbs;
        }

        return sum ;
    }
}