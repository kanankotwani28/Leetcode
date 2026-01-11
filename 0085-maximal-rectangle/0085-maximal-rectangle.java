import java.util.Deque;
import java.util.ArrayDeque;

public class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) return 0;
        int maxArea = 0;
        int[] heights = new int[matrix[0].length];

        for (char[] row : matrix) {
            // Update the histogram heights
            for (int i = 0; i < row.length; i++) {
                heights[i] = (row[i] == '1') ? heights[i] + 1 : 0;
            }
            // Calculate the maximal rectangle area for the current histogram
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }

        return maxArea;
    }

    private int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        int[] extendedHeights = new int[heights.length + 1];
        System.arraycopy(heights, 0, extendedHeights, 0, heights.length);

        for (int i = 0; i < extendedHeights.length; i++) {
            while (!stack.isEmpty() && extendedHeights[i] < extendedHeights[stack.peek()]) {
                int height = extendedHeights[stack.pop()];
                int width = (stack.isEmpty()) ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }

        return maxArea;
    }
}
