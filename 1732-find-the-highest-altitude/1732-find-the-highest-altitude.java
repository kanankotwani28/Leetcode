class Solution {
    public int largestAltitude(int[] gain) {
        int maxi = 0;
        int n = gain.length;

        int []alt = new int[n+1];
        alt[0] = 0;
        for(int i = 0 ; i< n ; i++){
            alt[i+1] = alt[i]+gain[i];
            maxi = Math.max(alt[i+1],maxi);
        }
        return maxi;
    }
}