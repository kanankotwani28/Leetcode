class Solution {
    public int findGCD(int[] nums) {
        int n = nums.length;
        int mini = nums[0];
        int maxi = nums[0];
        for(int i = 0 ; i<n ; i++){
            mini = Math.min(mini,nums[i]);
            maxi = Math.max(maxi,nums[i]);
        }

        return gcd(mini,maxi);
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}