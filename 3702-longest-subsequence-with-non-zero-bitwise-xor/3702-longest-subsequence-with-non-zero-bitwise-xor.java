class Solution {
    public int longestSubsequence(int[] nums) {
       int xor = 0;
       boolean zero = false;
       for(int num : nums) {
            xor ^= num;
            if(num != 0) zero = true;
       }
       if (xor != 0) 
            return nums.length;
        
        if(zero) return nums.length - 1;
        return 0;
    }
}


// get the longest seq of non repeating numbers