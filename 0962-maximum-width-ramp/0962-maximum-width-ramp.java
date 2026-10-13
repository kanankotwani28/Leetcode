
class Solution {
    public int maxWidthRamp(int[] nums) {
        int n = nums.length;
        int[] stack = new int[n];
        int top = -1;

        // Step 1: Build a decreasing stack of candidate left indices.
        for (int i = 0; i < n; i++) {
            // Keep only indices with strictly decreasing values.
            if (top == -1 || nums[i] < nums[stack[top]]) {
                stack[++top] = i;
            }
        }

        int maxWidth = 0;

        // Step 2: Search for the farthest valid right endpoint.
        for (int j = n - 1; j >= 0; j--) {
            // Check the candidate left indices from the stack's top.
            while (top >= 0 && nums[stack[top]] <= nums[j]) {
                int i = stack[top--];

                // Update the maximum width of a valid ramp.
                maxWidth = Math.max(maxWidth, j - i);
            }
        }

        return maxWidth;
    }
}
