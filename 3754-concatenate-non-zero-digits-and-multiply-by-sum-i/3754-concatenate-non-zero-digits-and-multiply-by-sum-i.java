class Solution {
    public long sumAndMultiply(int n) {
        long rev = 0;
        int temp = n;
        while (temp > 0) {
            rev = rev * 10 + (temp % 10);
            temp /= 10;
        }
        long x = 0;
        int sum = 0;
        while (rev > 0) {
            int d = (int)(rev % 10);

            if (d != 0) {
                x = x * 10 + d;
                sum += d;
            }

            rev /= 10;
        }

        return x * sum;
    }
}