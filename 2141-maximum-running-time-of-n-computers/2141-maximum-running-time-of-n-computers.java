class Solution {
    public long maxRunTime(int n, int[] batteries) {
        long sum = 0;
        for (int b : batteries) sum += b;
        
        long left = 0, right = sum / n; // max possible time
        long ans = 0;
        
        while (left <= right) {
            long mid = left + (right - left) / 2; 
            
            long total = 0;
            for (int b : batteries) {
                total += Math.min(b, mid);
            }
            
            if (total >= mid * n) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return ans;
    }
}
