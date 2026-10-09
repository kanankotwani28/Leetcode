class Solution {
    public int longestConsecutive(int[] nums) {
        int longest = 1;
        int n = nums.length;
        if(n == 0) return 0;
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0 ; i< n ; i++){
            set.add(nums[i]);
        }
        for(int it : set){
            if(!set.contains(it-1)){
                int count = 1;
                int x = it;
                while(set.contains(x+1)){
                    count++;
                    x++;
                }
            longest = Math.max(count,longest);
            }
        }
        return longest;
    }
}

// have to return the len of unique sequence without sorting
// 100 --> 4 
// 100 marked
// 4 marked 
// 200 --> do i anything earlier no.. cnt = 1
// 1 --> marked 
// 3 -- marked checked left got 1 marked update the count got 4 marked -- update 
// got 2 again check and store the updated cnt


// hashSet to mark
// check if left right is something then update accordingly
