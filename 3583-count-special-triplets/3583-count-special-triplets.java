class Solution {
    public int specialTriplets(int[] nums) {
                final int MOD = 1_000_000_007;
        final int MAX = 200000;   

        int[] right = new int[MAX + 1];
        int[] left = new int[MAX + 1];

        for (int x : nums) {
            right[x]++;
        }

        long ans = 0;

        for (int j = 0; j < nums.length; j++) {
            int mid = nums[j];
            right[mid]--;

            int target = mid * 2;

            if (target <= MAX) {
                ans = (ans + (long) left[target] * right[target]) % MOD;
            }
            left[mid]++;
        }

        return (int) ans;

    }
}