class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            int p = nums.get(i);

            // even -> impossible
            if ((p & 1) == 0) {
                ans[i] = -1;
                continue;
            }

            int k = 0;
            int temp = p;

            // count trailing 1s
            while ((temp & 1) == 1) {
                k++;
                temp >>= 1;
            }

            // subtract highest trailing-1 contribution
            ans[i] = p - (1 << (k - 1));
        }

        return ans;
    }
}
