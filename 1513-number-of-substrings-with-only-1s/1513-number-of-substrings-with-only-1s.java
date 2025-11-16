class Solution {
    public int numSub(String s) {
        
        int n = s.length();
        long ans = 0;
        long count = 0;
        long mod = 1_000_000_007;

        for(int i = 0 ;i<n; i ++)
        {
            if(s.charAt(i) == '1'){
                count ++;
                ans = (ans + count) % mod;
            }
            else{
                count = 0 ;
            }
        }

        return (int)ans;
    }
}