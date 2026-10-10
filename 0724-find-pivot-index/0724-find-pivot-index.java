class Solution {
    public int pivotIndex(int[] nums) {
        int n = nums.length;
        int prefix[] = new int[n];
        prefix[0] = nums[0];
        int sum = nums[0];
        for(int i = 1; i<n; i++){
            sum +=nums[i];
            prefix[i] = prefix[i-1] + nums[i];
        }

        for(int i = 0 ; i<n ; i++){
            int left = (i == 0) ? 0 : prefix[i-1];
            int right = sum - left - nums[i];

            if(left == right)
                return i;
        }
        return -1;
    }
}