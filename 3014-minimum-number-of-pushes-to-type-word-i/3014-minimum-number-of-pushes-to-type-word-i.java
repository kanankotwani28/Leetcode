class Solution {
    public int minimumPushes(String word) {
        int n = word.length();
        int ans = 0;
        if(n < 8) return n;
        for (int i = 0; i < word.length(); i++) {
            ans += (i / 8) + 1;
        }

        return ans;
    }
}

// for first 8 numbers always give the normal numbers and fro the rest do 2 * i