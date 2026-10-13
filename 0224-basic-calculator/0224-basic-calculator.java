
import java.util.Stack;

class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();

        int result = 0;
        int number = 0;
        int sign = 1;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {
                // Build multi-digit numbers, such as 123.
                number = number * 10 + (ch - '0');

            } else if (ch == '+') {
                // Commit the previous number to the result.
                result += sign * number;
                number = 0;
                sign = 1;

            } else if (ch == '-') {
                // Commit the previous number to the result.
                result += sign * number;
                number = 0;
                sign = -1;

            } else if (ch == '(') {
                // Save the outer sign and result.
                stack.push(result);
                stack.push(sign);

                // Start evaluating the inner expression.
                result = 0;
                sign = 1;

            } else if (ch == ')') {
                // Finish the current inner expression.
                result += sign * number;
                number = 0;

                // Apply the sign that was before '('.
                result *= stack.pop();

                // Add the previous outer result.
                result += stack.pop();
            }

            // Spaces require no action.
        }

        // Include the last number, since there may be no operator after it.
        result += sign * number;

        return result;
    }
}
