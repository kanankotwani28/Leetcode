class Solution {
    public boolean checkOnesSegment(String s) {
         int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                if (i == 0 || s.charAt(i - 1) == '0') {
                    count++;
                }
                if (count >= 2) {
                    return false;
                }
            }
        }
        return true;
    }
}