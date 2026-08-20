class KthLargest {
    int k;
    PriorityQueue<Integer> pq;
    public KthLargest(int k, int[] nums) {
        pq = new PriorityQueue<>(); // min heap at default
        this.k = k;
        int n = nums.length;
        for(int i = 0; i < n ; i++){
            if(pq.size() < k){
                pq.add(nums[i]);
            }
            else{
                if(pq.peek() < nums[i]){
                    pq.poll();
                    pq.add(nums[i]);
                }
            }
        }
    }
    
    public int add(int val) {
        if(pq.size() < k){
            pq.add(val);
        }

        else{
            if(pq.peek() < val){
                pq.poll();
                pq.add(val);
            }
        }

        return pq.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */