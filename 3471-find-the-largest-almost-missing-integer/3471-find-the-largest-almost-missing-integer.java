class Solution {
    public int largestInteger(int[] nums, int k) {
        int[] freq = new int[51];
        int[] windowCount = new int[51];

        int n = nums.length;

        // First window
        for (int i = 0; i < k; i++) {
            freq[nums[i]]++;
        }

        for (int x = 0; x <= 50; x++) {
            if (freq[x] > 0) {
                windowCount[x]++;
            }
        }

        // Slide the window
        for (int i = k; i < n; i++) {

            // Remove outgoing element
            freq[nums[i - k]]--;

            // Add incoming element
            freq[nums[i]]++;

            // Count every distinct element in new window
            for (int x = 0; x <= 50; x++) {
                if (freq[x] > 0) {
                    windowCount[x]++;
                }
            }
        }

        // Largest almost missing integer
        for (int x = 50; x >= 0; x--) {
            if (windowCount[x] == 1) {
                return x;
            }
        }

        return -1;
    }
}