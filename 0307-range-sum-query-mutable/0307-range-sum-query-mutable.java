class NumArray {
    int []tree;
    int n ;
    int arr[];
    public NumArray(int[] nums) {
        n = nums.length;
        tree = new int[4 * n];
        arr = nums;
        build(0,0 , n-1);
    }
    private void build(int node , int left , int right){
        if(left == right){
            tree[node] = arr[left];
            return;
        }

        int mid = (left + right) / 2;
        build(2*node+1,left , mid);
        build(2*node+2,mid+1,right);

        tree[node] = tree[2*node+1] + tree[2*node+2];
    }

    private void update(int node , int left , int right , int index , int val){
        if(left == right){
            tree[node] = val;
            arr[left] = val;
            return;
        }

        int mid = (left + right) / 2;
        if(index<= mid) update(2*node+1,left , mid , index , val);
        else update(2*node+2,mid + 1 ,right, index , val);

        tree[node] = tree[2*node+1] + tree[2*node+2];
    }
    
    public void update(int index, int val) {
        update(0 , 0 , n-1 , index , val);
    }

    private int sumRange(int node , int left , int right , int qleft , int qright){
        if(right < qleft || left > qright){
            return 0;
        }

        if(left >= qleft && right <= qright){
            return tree[node];
        }

        int mid = (left + right)/2;

        int leftSum = sumRange(2*node+1,left , mid , qleft , qright);
        int rightSum = sumRange(2*node + 2 , mid + 1 , right , qleft , qright);

        return leftSum + rightSum;

    }
    
    public int sumRange(int left, int right) {
        return sumRange(0 , 0 , n-1 , left , right);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */