class Solution {
    public int countCommas(int n) {
         int ans = 0;
        long start = 1000;
        int commas = 1;

        while (start <= n) {
            long end = start * 1000 - 1;

            if (end > n) {
                end = n;
            }

            ans += (end - start + 1) * commas;

            start *= 1000;
            commas++;
        }

        return ans;
        
    }
}

// int must be of length 4 or >
// if of 4 digit then extract the last digit and return + 1 
// if (n-1)% 3 then times % 3 *  add
//