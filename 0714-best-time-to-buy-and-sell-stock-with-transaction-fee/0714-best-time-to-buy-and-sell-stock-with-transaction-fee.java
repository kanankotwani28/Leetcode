class Solution {
    public int maxProfit(int[] arr, int fee) {
        int n = arr.length;
        int[][] dp = new int[n+1][2];

        for(int ind = n-1 ; ind>= 0 ; ind --){
            for(int buy = 0 ; buy<=1 ; buy++){
                int profit = 0;
                if(buy == 0){
                    profit = Math.max(0+dp[ind+1][0],(-1)*arr[ind] + dp[ind+1][1]);
                }
                if(buy == 1){
                    profit = Math.max(0 + dp[ind + 1][1], arr[ind]-fee + dp[ind + 1][0]);
                }

                dp[ind][buy] = profit;
            }
        }
        return dp[0][0];
    }
}