class Solution {
    public String lexPalindromicPermutation(String s, String target) {

        int[] freq = new int[26];

        // Count characters
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Check if palindrome is possible
        int odd = 0;
        int mid = -1;

        for (int i = 0; i < 26; i++) {
            if ((freq[i] & 1) == 1) {
                odd++;
                mid = i;
            }
        }

        if (odd > 1) {
            return "";
        }

        // Keep only characters needed for the left half
        for (int i = 0; i < 26; i++) {
            freq[i] /= 2;
        }

        int n = s.length();
        int half = n / 2;

        char[] ans = new char[n];
        char[] t = target.toCharArray();

        // Try to match target's first half
        int pos = 0;

        while (pos < half) {
            int ch = t[pos] - 'a';

            if (freq[ch] == 0) {
                break;
            }

            ans[pos] = t[pos];
            freq[ch]--;
            pos++;
        }

        // First half is exactly equal to target's first half
        if (pos == half) {

            makePalindrome(ans, half, mid);

            String candidate = new String(ans);

            if (candidate.compareTo(target) > 0) {
                return candidate;
            }
        }

        // Backtrack and make one position greater
        while (true) {

            if (pos < half) {

                int start = t[pos] - 'a' + 1;
                int ch = -1;

                // Find smallest available character > target[pos]
                for (int c = start; c < 26; c++) {
                    if (freq[c] > 0) {
                        ch = c;
                        break;
                    }
                }

                if (ch != -1) {

                    ans[pos] = (char) ('a' + ch);
                    freq[ch]--;

                    // Fill remaining left half in sorted order
                    int index = pos + 1;

                    for (int c = 0; c < 26; c++) {
                        while (freq[c] > 0) {
                            ans[index++] = (char) ('a' + c);
                            freq[c]--;
                        }
                    }

                    // Complete palindrome
                    makePalindrome(ans, half, mid);

                    return new String(ans);
                }
            }

            // No solution
            if (pos == 0) {
                return "";
            }

            // Backtrack
            pos--;

            // Restore the character that was matched with target
            int ch = t[pos] - 'a';
            freq[ch]++;
        }
    }

    private void makePalindrome(char[] ans, int half, int mid) {

        int n = ans.length;

        // Put middle character
        if (mid != -1) {
            ans[half] = (char) ('a' + mid);
        }

        // Mirror left half
        for (int i = 0; i < half; i++) {
            ans[n - 1 - i] = ans[i];
        }
    }
}