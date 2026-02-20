class Solution {
    public int maxVowels(String s, int k) {
        int i = 0 ;
        int j = 0 ;
        int count = 0 ;
        int maxVowels = 0;

        while(j < s.length())
        {
            if (isVowel(s.charAt(j))) {
                count++;
            }

            if (j - i + 1 > k) {
                if (isVowel(s.charAt(i))) {
                    count--;
                }
                i++;
            }

            if (j - i + 1 == k) {
                maxVowels = Math.max(maxVowels, count);
            }

            j++;
        }

        return maxVowels;
    }

    private boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' ||
               ch == 'o' || ch == 'u';
    }
}