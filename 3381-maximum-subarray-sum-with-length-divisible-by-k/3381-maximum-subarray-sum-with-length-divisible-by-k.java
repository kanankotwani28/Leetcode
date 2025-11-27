class Solution {
    public long maxSubarraySum(int[] nums, int k) {

        long[] minPrefix = new long[k];
        for (int i = 0; i < k; i++) 
            minPrefix[i] = Long.MAX_VALUE; 

        long prefix = 0;                     
        long ans = Long.MIN_VALUE;           

        minPrefix[0] = 0;                   

        for (int j = 0; j < nums.length; j++) {

            prefix += nums[j];               

            int bucket = (j + 1) % k;        

            if (minPrefix[bucket] != Long.MAX_VALUE) {
                long candidate = prefix - minPrefix[bucket];
                ans = Math.max(ans, candidate); 
            }
            minPrefix[bucket] = Math.min(minPrefix[bucket], prefix);
        }

        return ans;
    }
}
