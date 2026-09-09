class Solution {
    public long countCommas(long n) {
        long ans = 0;
        long power = 1000; // 10^3

        while (power <= n) {
            ans += n - power + 1;

            // Move to 10^6, 10^9, 10^12, 10^15
            if (power > n / 1000) {
                break;
            }

            power *= 1000;
        }

        return ans;
    }
}