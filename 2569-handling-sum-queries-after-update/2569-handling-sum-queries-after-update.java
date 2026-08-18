class Solution {
    int []tree;
    int []lazy;

    void build(int node , int start , int end , int arr[]){
        if(start == end){
            tree[node] = arr[start];
            return;
        }

        int mid = (start + end )/ 2;
        build(node*2 , start , mid,arr );
        build(node *2 +1, mid+1 , end , arr);

        tree[node] = tree[node*2] + tree[node *2 + 1];
    }

    void push(int node , int start , int end){
        if(lazy[node] == 0 || start == end ) return;
        int mid = (start + end) / 2;

        tree[node*2] = (mid - start + 1) - tree[node*2];
        tree[node*2+1] = (end - mid) - tree[node*2+1];

        lazy[node*2] = 1 - lazy[node*2];
        lazy[node*2+1] = 1 - lazy[node*2+1];

        lazy[node] = 0;

    }

    void update(int node , int start  , int end , int l , int r){
        if(r < start || l > end) return;
        if (l <= start && end <= r){
            tree[node] = (end - start + 1) - tree[node];
            lazy[node] = 1 - lazy[node];
            return;
        }

        push(node, start , end);
        int mid = (start + end) / 2;
        update(node*2,start,mid,l,r);
        update(node*2+1,mid+1,end,l,r);
        tree[node] = tree[node*2] + tree[node*2+1];
    }
    public long[] handleQuery(int[] nums1, int[] nums2, int[][] queries) {
        int n = nums1.length;
        tree = new int[4*n];
        lazy = new int[4*n];

        build(1,0,n-1,nums1);
        long sum2 = 0;
        for(int i = 0 ; i<nums2.length ; i++){
            sum2+=(long)nums2[i];
        }

        int cnt = 0;
         for (int[] q : queries) {
            if (q[0] == 3) {
                cnt++;
            }
        }

        long []ans = new long[cnt];
        int ind = 0;
        for(int []q : queries){
            if(q[0] == 1){
                update(1,0,n-1,q[1],q[2]);
            }
            else if(q[0] == 2 ){
                sum2 +=(long)tree[1]*q[1];
            }
            else{
                ans[ind++] = sum2;
            }
        }
    return ans;
    }
}