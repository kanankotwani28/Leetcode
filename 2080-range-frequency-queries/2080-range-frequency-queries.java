class RangeFreqQuery {
    HashMap<Integer, Integer>[] tree;
    int n ;
    int[] arr;
    public RangeFreqQuery(int[] arr) {
        this.n = arr.length;
        this.arr = arr;
        tree = new HashMap[4*n];
        build(0,0,n-1);
    }

    private void build(int node , int left , int right){
        if(left == right){
            tree[node] = new HashMap<>();
            tree[node].put(arr[left],1);
            return;
        }

        int mid = (left + right) / 2;
        build(2 * node + 1, left , mid);
        build(2* node + 2, mid+1, right);

        tree[node] = new HashMap<>();
        merge(tree[node], tree[2*node + 1]);
        merge(tree[node], tree[2*node + 2]);
    }

    private void merge(HashMap<Integer, Integer> parent , HashMap<Integer, Integer> child ){
        for(Map.Entry<Integer, Integer> e : child.entrySet()){
            parent.put(e.getKey(), parent.getOrDefault(e.getKey(), 0) + e.getValue()
);
        }
    }
    
    public int query(int left, int right, int value) {
        return query(0, 0, n - 1, left, right, value);
    }

    private int query(int node , int start , int end , int left , int right , int value){
        if(right < start || end < left) return 0;
        if (left <= start && end <= right) {
            return tree[node].getOrDefault(value, 0);
        }

        int mid = (start + end) / 2;
        int l = query(2 * node + 1, start, mid,left, right, value);
        int r = query(2 * node + 2, mid + 1, end, left, right, value);

        return l + r;
    }
}


/**
 * Your RangeFreqQuery object will be instantiated and called as such:
 * RangeFreqQuery obj = new RangeFreqQuery(arr);
 * int param_1 = obj.query(left,right,value);
 */