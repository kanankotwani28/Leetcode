class Solution {
    private int lcs(String s1 , String s2){
        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n+1][m+1];
        for(int i = 0 ; i<=n ; i++) dp[i][0] = 0;
        for(int j = 0 ; j<=m ; j++) dp[0][j] = 0;

        for(int ind1 = 1 ; ind1 <=n ; ind1 ++){
            for(int ind2 = 1 ; ind2 <=m ; ind2 ++){
                if(s1.charAt(ind1 - 1) == s2.charAt(ind2 - 1)){
                    dp[ind1][ind2] = 1 + dp[ind1 - 1][ind2 - 1];
                }
                else{
                    dp[ind1][ind2] = Math.max(dp[ind1-1][ind2], dp[ind1][ind2-1]);
                }
            }
        }

        return dp[n][m];
    }
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int k = lcs(word1,word2);

        return (n + m - (2*k));
    }
}