class Solution {
    public int repeatedNTimes(int[] nums) {
        int max = Integer.MIN_VALUE;

        for(int i = 0 ; i< nums.length ; i++){
            if(nums[i]> max)
                max = nums[i];
        }

        int [] hash = new int[max+1];
        for(int i = 0; i<nums.length ; i++){
            hash[nums[i]]++;
        }

        for(int i = 0 ; i<nums.length ; i++){
            if(hash[nums[i]] >= 2)
                return nums[i];
        }


        return -1;
    }
}