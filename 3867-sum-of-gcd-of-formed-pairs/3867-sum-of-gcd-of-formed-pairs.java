class Solution {
    public long gcdSum(int[] nums) {
        int n = nums.length;
        int []prefixGcd = new int[n];
        int maxNum = 0;
        for(int i = 0 ; i<n ; i++){
            maxNum = Math.max(maxNum, nums[i]);
            prefixGcd[i] = gcd(maxNum,nums[i]);
        }

        Arrays.sort(prefixGcd);
        int left = 0 ; 
        int right = n-1;
        long sum = 0;

        while(left< right){
            int num = gcd(prefixGcd[left],prefixGcd[right]);
            left ++;
            right --;
            sum +=num;
        }
        return sum;
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}