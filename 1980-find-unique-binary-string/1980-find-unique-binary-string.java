class Solution {
    public String findDifferentBinaryString(String[] nums) {
        int n = nums.length;
        StringBuilder res = new StringBuilder();

        for(int i = 0; i < n; i++) {

            char c = nums[i].charAt(i);

            if(c == '0')
                res.append('1');
            else
                res.append('0');
        }

        return res.toString();
    }
}