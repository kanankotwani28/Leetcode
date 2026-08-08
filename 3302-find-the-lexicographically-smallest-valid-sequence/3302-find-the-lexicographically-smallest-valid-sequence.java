class Solution {
    public int[] validSequence(String word1, String word2) {
        char[] s = word1.toCharArray();
        char[] t = word2.toCharArray();

        int n = s.length;
        int m = t.length;

        // dp[i] = number of characters from the suffix of word2
        // that can still be matched starting from word1[i]
        int[] dp = new int[n + 1];

        int j = m - 1;
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = dp[i + 1];
            if (j >= 0 && s[i] == t[j]) {
                dp[i]++;
                j--;
            }
        }

        int[] ans = new int[m];

        int i = 0;
        j = 0;

        while (i < n && j < m) {
            if (s[i] == t[j]) {
                ans[j++] = i;
            } else {
                // Use the single mismatch here if the remaining suffix
                // can still be matched exactly.
                if (dp[i + 1] >= m - j - 1) {
                    ans[j++] = i;
                    i++;
                    break;
                }
            }
            i++;
        }

        if (j < m && i == n)
            return new int[0];

        while (i < n && j < m) {
            if (s[i] == t[j]) {
                ans[j++] = i;
            }
            i++;
        }

        return j == m ? ans : new int[0];
    }
}