
class Solution {
    public String maxSumOfSquares(int num, int sum) {
        // A number with num digits can have at most 9 * num as its digit sum.
        if (sum > 9 * num) {
            return "";
        }

        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < num; i++) {
            if (sum >= 9) {
                // Use the largest possible digit to maximize the score.
                ans.append('9');
                sum -= 9;
            } else {
                // Put the remaining sum into this digit.
                ans.append((char) ('0' + sum));
                sum = 0;
            }
        }

        return ans.toString();
    }
}
