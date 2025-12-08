class Solution {
    public int countTriples(int n) {
        int count = 0;
        int[] sq = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            sq[i] = i * i;
        }

        for (int a = 1; a <= n; a++) {
            for (int b = 1; b <= n; b++) {
                int sum = sq[a] + sq[b];
                int c = (int) Math.sqrt(sum);

                if (c <= n && sq[c] == sum) {
                    count++;
                }
            }
        }
        return count;
    }
}
