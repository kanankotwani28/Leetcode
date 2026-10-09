class Solution {
    public int minInsertions(String s) {

        int open = 0;
        int ans = 0;

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            if (ch == '(') {
                open++;
            } 
            else {

                // We have a ')'
                // Need another ')' after it
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    i++;  // consume the second ')'
                } 
                else {
                    // Missing one ')'
                    ans++;
                }

                // This pair of ')' needs an opening '('
                if (open > 0) {
                    open--;
                } 
                else {
                    // No '(' available, so insert one
                    ans++;
                }
            }
        }

        // Every remaining '(' needs two ')'
        ans += 2 * open;

        return ans;
    }
}