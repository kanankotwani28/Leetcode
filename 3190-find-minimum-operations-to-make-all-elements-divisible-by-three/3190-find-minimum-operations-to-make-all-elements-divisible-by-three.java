class Solution {
    public int minimumOperations(int[] nums) {
        int l = nums.length;
        int op = 0;
        for(int i = 0 ; i<l ; i++){
            if(nums[i]%3 == 1){
                nums[i]-=1;
                op++;
            }
            else if(nums[i]%3 == 2){
                nums[i]+=1;
                op++;
            }
        }
    return op;
    }
}