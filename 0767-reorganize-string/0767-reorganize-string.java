class Solution {
    public String reorganizeString(String s) {

        // Count frequency
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Max heap: character with highest frequency first
        PriorityQueue<Character> pq =
            new PriorityQueue<>((a, b) -> freq[b - 'a'] - freq[a - 'a']);

        for (char c = 'a'; c <= 'z'; c++) {
            if (freq[c - 'a'] > 0) {
                pq.add(c);
            }
        }

        // If maximum frequency is too large
        if (freq[pq.peek() - 'a'] > (s.length() + 1) / 2) {
            return "";
        }

        char[] ans = new char[s.length()];
        int index = 0;

        while (!pq.isEmpty()) {

            char c = pq.poll();

            // Put character at 0, 2, 4, ...
            while (freq[c - 'a'] > 0) {

                ans[index] = c;
                freq[c - 'a']--;

                index += 2;

                // Switch to odd positions
                if (index >= s.length()) {
                    index = 1;
                }
            }
        }

        return new String(ans);
    }
}