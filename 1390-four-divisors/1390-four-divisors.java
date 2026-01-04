class Solution {
    public int sumFourDivisors(int[] nums) {
        int totalSum = 0;

        for (int num : nums) {
            int sum = 0;
            int count = 0;

            for (int d = 1; d * d <= num; d++) {
                if (num % d == 0) {
                    int d1 = d;
                    int d2 = num / d;

                    count++;
                    sum += d1;

                    if (d1 != d2) {
                        count++;
                        sum += d2;
                    }

                    if (count > 4) break;
                }
            }

            if (count == 4) {
                totalSum += sum;
            }
        }
        return totalSum;
    }
}
