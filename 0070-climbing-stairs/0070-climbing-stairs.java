class Solution {
    public int memo(int dp[] , int n){
        if(n<=1) return 1;
        if(dp[n]!=-1){
            return dp[n];
        }
        return dp[n] = memo(dp,n-1)+ memo(dp,n-2);
    }
    public int climbStairs(int n) {
        int dp[]= new int[n+1];
        Arrays.fill(dp,-1);
        return dp[n] = memo(dp,n);
    }
}