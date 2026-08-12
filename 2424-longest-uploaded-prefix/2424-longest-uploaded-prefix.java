class LUPrefix {
    int []tree;
    int n;
    public LUPrefix(int n) {
        this.n = n ;
        tree = new int[4*n];
    }
    
    public void upload(int video) {
        update(1,1,n,video);
    }
    
    public int longest() {
       return tree[1];
    }

    private void update(int node , int start , int end , int video){
        if(start == end){
            tree[node] = 1;
            return;
        }

        int mid = (start + end) / 2;
        if(video<=mid ) update(2*node , start , mid ,video);
        else update(2*node + 1 , mid+1 , end , video);

        int leftChild = node * 2 ;
        int rightChild = node * 2 + 1;

        int length = mid - start + 1;
        if(tree[leftChild] == length){
            tree[node] = length + tree[rightChild];
        }
        else {
            tree[node] = tree[leftChild];
        }


    }
}

/**
 * Your LUPrefix object will be instantiated and called as such:
 * LUPrefix obj = new LUPrefix(n);
 * obj.upload(video);
 * int param_2 = obj.longest();
 */