class Solution {
    public int minSubarray(int[] nums, int p) {
        long total = 0;
        for (int x : nums) total += x;

        int r = (int)(total % p);
        if (r == 0) return 0; // Already divisible

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // prefix before starting

        long prefix = 0;
        int ans = nums.length;

        for (int i = 0; i < nums.length; i++) {
            prefix = (prefix + nums[i]) % p;

            // We want prefix[j] = (prefix - r + p) % p
            int need = (int)((prefix - r + p) % p);

            if (map.containsKey(need)) {
                ans = Math.min(ans, i - map.get(need));
            }

            map.put((int) prefix, i);
        }

        return ans == nums.length ? -1 : ans;
    }
}
