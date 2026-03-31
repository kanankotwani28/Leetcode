class Solution {

    public int reverse(int n) {
        long a = 0;
        while (n != 0) {
            long d = n % 10;
            a = (a * 10) + d;
            n /= 10;
        }
        if (a > Integer.MAX_VALUE || a < Integer.MIN_VALUE) {
            return 0;
        }
        return (int)a;
    }
}