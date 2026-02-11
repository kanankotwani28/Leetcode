class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int n = nums.length;
        int i = 0 , j = 0;
        int maxSum = Integer.MIN_VALUE;
        int sum = 0 ;
        while(j < n)
        {
          sum += nums[j];
          int s = j-i+1;

          if(s > k)
          {
            sum -= nums[i];
            i++;
            s--;
          }
          if(s == k)
            maxSum = Math.max(maxSum,sum);

          j++;   
        }

        return (double)maxSum/k;
    }
}