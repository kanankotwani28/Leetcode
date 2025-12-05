class Solution {
    public int countPartitions(int[] nums) {
        long total = 0;
        for (int x : nums) {
            total += x;
        }
        int n = nums.length;
        return (total % 2 == 0) ? (n - 1) : 0;
    }
}
