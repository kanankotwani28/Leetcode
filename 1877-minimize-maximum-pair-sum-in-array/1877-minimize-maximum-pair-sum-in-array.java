class Solution {
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int l = 0 , r = nums.length-1;
        int maxi = Integer.MIN_VALUE;

        while(l<r){
            int sum = nums[l]+nums[r];
            maxi = Math.max(maxi,sum);
            l++;
            r--;
        }
    return maxi;
        
    }
}