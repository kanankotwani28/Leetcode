class Solution {
    public int countPalindromicSubsequence(String s) {
       int n = s.length();
        int result = 0;
        
        for (char ch = 'a'; ch <= 'z'; ch++) {

            int first = -1, last = -1;
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == ch) {
                    if (first == -1) first = i;
                    last = i;
                }
            }

            if (first != -1 && last != -1 && first < last) {
                boolean[] used = new boolean[26];

                for (int i = first + 1; i < last; i++) {
                    used[s.charAt(i) - 'a'] = true;
                }
                for (boolean b : used) {
                    if (b) result++;
                }
            }
        }

        return result;
    }
}