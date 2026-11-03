class Solution {
    public int minMoves(int[] nums) {
        int sum = 0;
        int n = nums.length;
        int max = 0;
        for(int i = 0 ; i < n ; i++){
            max = Math.max(max , nums[i]);
        }

        for(int i = 0 ; i< n ; i++){
            sum += (max - nums[i]);
        }

        return sum;
    }
}
// get the max element 
// get the max - nums[i] --> add it to the sum
// return the sum