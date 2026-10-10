class Solution {
    public int firstMissingPositive(int[] nums) {
        int ans = 0;
        int n = nums.length;
        for(int i = 0 ; i<n ; i++){
            if(nums[i] == 1)
                ans = 1;
        }

        if(ans == 0) return 1;
        for(int i = 0 ; i< n ;i++){
            if (nums[i] <= 0 || nums[i] > n)
                nums[i] = 1;
        }

        for(int i = 0  ;  i<n ; i++){
            int num = Math.abs(nums[i]);
            int index = num-1;

            if(nums[index] > 0) 
                nums[index]*=-1;
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] > 0)
                return i + 1;
        }

        return n+1;
    }
}