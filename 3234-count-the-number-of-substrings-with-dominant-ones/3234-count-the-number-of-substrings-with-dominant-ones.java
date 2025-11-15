class Solution {
    public long countDominantOnes(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();

        // Step 1: collect zero positions
        ArrayList<Integer> zeros = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (arr[i] == '0') zeros.add(i);
        }

        long ans = 0;

        // Step 2: count all-ones substrings (z = 0)
        int i = 0;
        while (i < n) {
            if (arr[i] == '1') {
                int j = i;
                while (j < n && arr[j] == '1') j++;
                long len = j - i;
                ans += len * (len + 1) / 2;
                i = j;
            } else i++;
        }

        int Z = zeros.size();
        if (Z == 0) return ans;

        // We only need to consider z up to sqrt(n)
        int limit = (int) Math.sqrt(n) + 2;

        // Step 3: sliding window on zeros
        for (int l = 0; l < Z; l++) {
            for (int r = l; r < Z; r++) {
                int z = r - l + 1;
                if (z > limit) break;   // minLen too long

                int minLen = z * z + z;
                if (minLen > n) break;

                // Allowed start range
                int startMin = (l == 0) ? 0 : zeros.get(l - 1) + 1;
                int startMax = zeros.get(l);

                // Allowed end range
                int endMin = zeros.get(r);
                int endMax = (r == Z - 1) ? n - 1 : zeros.get(r + 1) - 1;

                // For each start in [startMin .. startMax]
                for (int start = startMin; start <= startMax; start++) {
                    int requiredEnd = start + minLen - 1;

                    if (requiredEnd > endMax) continue;

                    int validStart = Math.max(requiredEnd, endMin);
                    ans += (endMax - validStart + 1);
                }
            }
        }

        return ans;
    }
}