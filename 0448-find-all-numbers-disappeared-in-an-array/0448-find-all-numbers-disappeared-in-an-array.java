class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        for(int i = 0 ; i<n ; i++){
            int num = Math.abs(nums[i]);
            int index = num - 1;

            if(nums[index] >0)
                nums[index] *= -1;
        }
        List<Integer> ans = new ArrayList<>();
        for(int i = 0 ; i<n ; i++){
            if(nums[i] > 0)
                ans.add(i+1);
        }

        return ans;
    }
}