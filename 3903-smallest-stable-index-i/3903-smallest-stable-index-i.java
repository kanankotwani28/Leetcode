class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int suf[]= new int[n];

        suf[n-1] = nums[n-1];
        for(int i = n-2 ; i >= 0 ; i--){
            suf[i] = Math.min(nums[i],suf[i+1]);
        }
        int maxi = nums[0];
        for(int i = 0 ; i < n ; i++){
            maxi = Math.max(maxi,nums[i]);
            if(maxi - suf[i] <= k){
                return i;
            }
        }
        return -1;
    }
}

// to get the max till now we can just update from the last max
// you can get the min at first and save its index if we reach that index then we can again calculate the min  