class Solution {
    public boolean kLengthApart(int[] nums, int k) {
        int fone = -1;
        for(int i = 0 ;i<nums.length ; i++){
            if(nums[i] == 1)
            {
                fone = i;
                break;
            }
        }

        if(fone == -1)
            return true;
        
        for(int j = fone+1; j < nums.length ; j++){
            if(nums[j] == 1){
                if(j - fone - 1 < k )
                    return false;
                
                else
                    fone = j ;
            }
        }

        return true;
    }
}