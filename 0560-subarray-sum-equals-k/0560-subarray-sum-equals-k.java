class Solution {
    public int subarraySum(int[] nums, int k) {
        HashMap<Long,Integer> mpp = new HashMap<>();
        mpp.put(0L,0);

        long sum = 0;
        int cnt = 0;

        for(int num : nums)
        {
            sum+=num;
            
            cnt+=mpp.getOrDefault(sum-k,0);
            mpp.put(sum,mpp.getOrDefault(sum,0)+1);
        }
        return cnt;
    }
}