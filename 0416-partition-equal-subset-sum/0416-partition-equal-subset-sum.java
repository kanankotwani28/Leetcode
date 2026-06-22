class Solution {
    private boolean canSum(int ind , int target , int[][]dp , int arr[]){
        if(target == 0) return true;
        if(ind == 0 ) return arr[0] == target;
        if(dp[ind][target]  != -1) return dp[ind][target] == 1;

        boolean notTake = canSum(ind-1 , target , dp , arr);
        boolean take = false; 
        if(target >= arr[ind]) take = canSum(ind-1 , target - arr[ind] , dp , arr);

        dp[ind][target] = notTake || take ? 1 : 0 ;
        return notTake || take;
    }
    public boolean canPartition(int[] arr) {
        int n = arr.length;
        int sum = 0;
        for(int i = 0 ; i<n ; i++) sum+=arr[i];
        if(sum % 2 == 1) return false;
        int dp[][] = new int [n][sum/2+1];
        for(int row[] : dp)
            Arrays.fill(row,-1);
        return canSum(n-1 , sum/2 , dp , arr);
    }
}