class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;

        int []hash = new int[10];
        for(int i = 0 ; i<n ; i++)
            hash[digits[i]]++;
        
        if(hash[9] == n){
            int []a = new int[n+1];
            for(int i = n; i>=1 ; i--)
                a[i] = 0 ;
            a[0] = 1;
            return a;
        }
        else{
            int i = n-1;
            while(digits[i] == 9){
                digits[i] = 0;
                i --;
            }
            digits[i]++;
        }
        return digits;
        
    }
}