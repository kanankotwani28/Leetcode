class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        int inc = 1;
        int dec = 1;
        int ans = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                inc++;
                dec = 1;
            }
            else if (nums[i] < nums[i - 1]) {
                dec++;
                inc = 1;
            }
            else {
                inc = 1;
                dec = 1;
            }
            ans = Math.max(ans, Math.max(inc, dec));
        }
        return ans;
    }
}

// start with ans = 1
// take  pointers one must be at the start of the array and other must be increasing till we doesnt get any element that violated 
// if violates then put the left pointer on the current and keep on moving til the ennd