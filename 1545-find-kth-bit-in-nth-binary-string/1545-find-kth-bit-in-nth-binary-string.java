class Solution {
    public char findKthBit(int n, int k) {
        if (n == 1) {
            return '0';
        }

        int mid = 1 << (n - 1);  // 2^(n-1)

        if (k == mid) {
            return '1';
        }

        if (k < mid) {
            return findKthBit(n - 1, k);
        }

        // Right half
        char bit = findKthBit(n - 1, (1 << n) - k);
        return bit == '0' ? '1' : '0'; 
    }
}