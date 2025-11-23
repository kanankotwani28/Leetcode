class Solution {
    public int maxSumDivThree(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int x : nums) sum += x;

        if (sum % 3 == 0) return sum;

        int r = sum % 3;

        int min1 = Integer.MAX_VALUE, min11 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE, min22 = Integer.MAX_VALUE;

        for (int x : nums) {
            if (x % 3 == 1) {
                if (x < min1) {
                    min11 = min1;
                    min1 = x;
                } else if (x < min11) {
                    min11 = x;
                }
            } else if (x % 3 == 2) {
                if (x < min2) {
                    min22 = min2;
                    min2 = x;
                } else if (x < min22) {
                    min22 = x;
                }
            }
        }

        if (r == 1) {
            int remove1 = min1;
            int remove2 = (min2 == Integer.MAX_VALUE || min22 == Integer.MAX_VALUE)
                          ? Integer.MAX_VALUE : min2 + min22;
            int remove = Math.min(remove1, remove2);
            return remove == Integer.MAX_VALUE ? 0 : sum - remove;
        } else { // r == 2
            int remove1 = min2;
            int remove2 = (min1 == Integer.MAX_VALUE || min11 == Integer.MAX_VALUE)
                          ? Integer.MAX_VALUE : min1 + min11;
            int remove = Math.min(remove1, remove2);
            return remove == Integer.MAX_VALUE ? 0 : sum - remove;
        }
    }
}
