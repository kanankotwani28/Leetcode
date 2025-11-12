class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int totgcd = nums[0];

        for(int i = 0 ; i<n ; i++){
            totgcd = gcd(totgcd,nums[i]);
        }

        if(totgcd > 1) return -1;

        int ones = 0;
        for(int i = 0 ; i<n ; i++){
            if(nums[i] == 1)
                ones++;
        }

        if(ones > 0)
            return n-ones;

        int mini = Integer.MAX_VALUE;
        for(int i = 0 ; i<n ; i++){
            int cur = nums[i];
            for(int j = i ; j<n ; j++){
                cur = gcd(cur,nums[j]);
                if(cur == 1 ){
                    mini = Math.min(mini,j-i+1);
                    break;
                }
                
            }
        }
        return (mini-1)+(n-1);
    }

    private int gcd(int a, int b){
         while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}