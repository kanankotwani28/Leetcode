class Solution {

    static final long LIMIT = 1_000_000L;

    public String smallestPalindrome(String s, int k) {

        int[] freq = new int[26];

        for (char c : s.toCharArray())
            freq[c - 'a']++;

        int[] half = new int[26];
        String mid = "";

        int len = 0;

        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
            len += half[i];

            if ((freq[i] & 1) == 1)
                mid = String.valueOf((char) ('a' + i));
        }

        if (countWays(half, len) < k)
            return "";

        StringBuilder first = new StringBuilder();

        for (int pos = 0; pos < len; pos++) {

            for (int c = 0; c < 26; c++) {

                if (half[c] == 0)
                    continue;

                half[c]--;

                long ways = countWays(half, len - pos - 1);

                if (ways >= k) {
                    first.append((char) ('a' + c));
                    break;
                } else {
                    k -= ways;
                    half[c]++;
                }
            }
        }

        String second = new StringBuilder(first).reverse().toString();

        return first.toString() + mid + second;
    }

    private long countWays(int[] cnt, int total) {

        long ans = 1;
        int rem = total;

        for (int x : cnt) {

            if (x == 0)
                continue;

            ans = multiplyCap(ans, comb(rem, x));

            if (ans >= LIMIT)
                return LIMIT;

            rem -= x;
        }

        return ans;
    }

    private long comb(int n, int r) {

        if (r > n)
            return 0;

        r = Math.min(r, n - r);

        long res = 1;

        for (int i = 1; i <= r; i++) {

            res = res * (n - r + i) / i;

            if (res >= LIMIT)
                return LIMIT;
        }

        return res;
    }

    private long multiplyCap(long a, long b) {

        if (a == 0 || b == 0)
            return 0;

        if (a >= LIMIT || b >= LIMIT)
            return LIMIT;

        if (a > LIMIT / b)
            return LIMIT;

        long val = a * b;

        return Math.min(val, LIMIT);
    }
}