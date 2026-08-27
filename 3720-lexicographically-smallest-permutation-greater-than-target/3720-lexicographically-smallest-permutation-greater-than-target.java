class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int[] cnt = new int[26];

        for (char c : s.toCharArray())
            cnt[c - 'a']++;

        char[] a = target.toCharArray();

        for (int i = 0; i < a.length; i++) {
            int x = a[i] - 'a';

            if (cnt[x] > 0) {
                cnt[x]--;
                continue;
            }

            // Backtrack and increase a position
            for (int j = i; j >= 0; j--) {
                if (j < i)
                    cnt[a[j] - 'a']++;

                for (int c = a[j] - 'a' + 1; c < 26; c++) {
                    if (cnt[c] > 0) {
                        a[j] = (char) ('a' + c);
                        cnt[c]--;

                        int k = j + 1;
                        for (c = 0; c < 26; c++)
                            while (cnt[c]-- > 0)
                                a[k++] = (char) ('a' + c);

                        return new String(a);
                    }
                }
            }

            return "";
        }

        // target itself is possible, so find its next permutation
        for (int j = a.length - 1; j >= 0; j--) {
            cnt[a[j] - 'a']++;

            for (int c = a[j] - 'a' + 1; c < 26; c++) {
                if (cnt[c] > 0) {
                    a[j] = (char) ('a' + c);
                    cnt[c]--;

                    int k = j + 1;
                    for (c = 0; c < 26; c++)
                        while (cnt[c]-- > 0)
                            a[k++] = (char) ('a' + c);

                    return new String(a);
                }
            }
        }

        return "";
    }
}