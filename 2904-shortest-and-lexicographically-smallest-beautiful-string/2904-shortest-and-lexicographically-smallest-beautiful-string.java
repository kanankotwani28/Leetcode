class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int left = 0;
        int right = 0;
        int ones = 0;
        int len = Integer.MAX_VALUE;
        String ans = "";
        int n = s.length();

        while (right < n) {
            if (s.charAt(right) == '1')
                ones++;
            if (ones == k) {
                while (s.charAt(left) == '0') {
                    left++;
                }
                int currLen = right - left + 1;
                String curr = s.substring(left, right + 1);
                if (currLen < len) {
                    len = currLen;
                    ans = curr;
                } 
                else if (currLen == len && curr.compareTo(ans) < 0) {
                    ans = curr;
                }
                if (s.charAt(left) == '1') {
                    ones--;
                    left++;
                }
            }
            right++;
        }
        return ans;
    }
}