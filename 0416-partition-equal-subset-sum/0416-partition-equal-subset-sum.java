class Solution {
    public boolean canPartition(int[] arr) {
        int n = arr.length;
        int sum = 0;
        for(int i = 0 ; i<n ; i++) sum+=arr[i];
        if(sum % 2 == 1) return false;
        int dp[][] = new int [n][sum/2+1];
        for(int row[] : dp)
            Arrays.fill(row,-1);
        // return canSum(n-1 , sum/2 , dp , arr);
        int t = sum / 2;
        for(int i = 0 ; i< n ; i++) dp[i][0] = 1;
        if(arr[0] <= t) dp[0][arr[0]] = 1;
        for( int i = 1 ; i< n ; i++){
            for(int j = 1 ; j <= t ; j++ ){
                boolean notTake = dp[i-1][j] == 1;
                boolean take = false; 
                if(j >= arr[i]) take = dp[i-1][j-arr[i]] == 1;
                dp[i][j] = notTake || take ? 1 : 0 ;
            }
        }

        return dp[n-1][t] == 1;
    }
}