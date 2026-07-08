class Solution {
    static final int MOD = 1_000_000_007;
    public int[] sumAndMultiply(String s, int[][] queries) {
        ArrayList<Integer> pos = new ArrayList<>();
        ArrayList<Integer> digits = new ArrayList<>();

        for(int i = 0 ; i< s.length() ; i++){
            int d = s.charAt(i) - '0';
            if(d!=0){
                pos.add(i);
                digits.add(d);
            }
        }

        int m = digits.size();

        long[] preNum = new long[m+1];
        int[] preSum = new int[m+1];
        long[] pow = new long[m+1];

        pow[0] = 1;
        for (int i = 1; i <= m; i++) {
            pow[i] = (pow[i - 1] * 10) % MOD;
        }

        for (int i = 0; i < m; i++) {
            preNum[i + 1] = (preNum[i] * 10 + digits.get(i)) % MOD;
            preSum[i + 1] = preSum[i] + digits.get(i);
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];
            int L = lowerBound(pos, l);
            int R = upperBound(pos, r) - 1;
            if (L > R) {
                ans[i] = 0;
                continue;
            }
            int len = R - L + 1;
            int sum = preSum[R + 1] - preSum[L];

            long x = (preNum[R + 1]
                    - (preNum[L] * pow[len]) % MOD
                    + MOD) % MOD;

            ans[i] = (int) ((x * sum) % MOD);
        }
        return ans;
    }

    private int lowerBound(ArrayList<Integer> arr, int target) {
        int l = 0, r = arr.size();
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr.get(mid) < target)
                l = mid + 1;
            else
                r = mid;
        }
        return l;
    }

    private int upperBound(ArrayList<Integer> arr, int target) {
        int l = 0, r = arr.size();
        while (l < r) {
            int mid = l + (r - l) / 2;

            if (arr.get(mid) <= target)
                l = mid + 1;
            else
                r = mid;
        }
        return l;
    }
}