class Solution {
    class Pair {
        char ch;
        int freq;

        Pair(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }
    }

    public String longestDiverseString(int a, int b, int c) {

        PriorityQueue<Pair> pq = new PriorityQueue<>(
            (x, y) -> y.freq - x.freq
        );

        if (a > 0) pq.offer(new Pair('a', a));
        if (b > 0) pq.offer(new Pair('b', b));
        if (c > 0) pq.offer(new Pair('c', c));

        StringBuilder ans = new StringBuilder();

        while (!pq.isEmpty()) {

            Pair first = pq.poll();

            // Would create xxx
            if (ans.length() >= 2 &&
                ans.charAt(ans.length() - 1) == first.ch &&
                ans.charAt(ans.length() - 2) == first.ch) {

                // No second character available
                if (pq.isEmpty()) {
                    break;
                }

                Pair second = pq.poll();

                ans.append(second.ch);
                second.freq--;

                if (second.freq > 0) {
                    pq.offer(second);
                }

                // Put first character back
                pq.offer(first);

            } else {

                ans.append(first.ch);
                first.freq--;

                if (first.freq > 0) {
                    pq.offer(first);
                }
            }
        }

        return ans.toString();
    }
}