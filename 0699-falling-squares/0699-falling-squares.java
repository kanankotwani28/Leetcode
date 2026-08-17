class Solution {
    int tree[];
    int lazy[];

    void push(int node){
        if(lazy[node] == 0) return;

        tree[2*node] = lazy[node];
        tree[2*node + 1] = lazy[node];

        lazy[node * 2] = lazy[node];
        lazy[2*node + 1] = lazy[node];

        lazy[node]  = 0;
    }

    void update(int node , int start , int end , int l , int r , int height){
        if(r <= start || l > end) return;
        if(l<=start && end < r ){
            tree[node] = height;
            lazy[node] = height;
            return;
        }

        push(node);
        int mid = (start + end)/ 2;
        update(2* node , start , mid , l , r , height);
        update(2* node + 1 , mid+1 , end , l , r , height);

        tree[node] = Math.max(tree[node * 2] , tree[node * 2 +1]);
    }

    int query(int node , int start , int end , int l , int r){
        if(r <= start || end < l) return 0;
        if(l <= start && end < r) return tree[node];
        push(node);
        int mid = (start + end) / 2;
        int left = query (node * 2 , start , mid , l , r);
        int right = query(node * 2 + 1 , mid+1 , end , l , r);

        return Math.max(left , right);
    }
    public List<Integer> fallingSquares(int[][] positions) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int[] p : positions) {
            int left = p[0];
            int right = p[0] + p[1];

            set.add(left);
            set.add(right);
        }

        List<Integer> coords = new ArrayList<>(set);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < coords.size(); i++) {
            map.put(coords.get(i), i);
        }

        int n = coords.size()- 1;
        tree = new int[4 * n + 5];
        lazy = new int[4 * n + 5];

        List<Integer> ans = new ArrayList<>();
        int maxHeight = 0;
        for(int []p : positions){
            int left = p[0];
            int side = p[1] ;
            int right = left + side;

            int l = map.get(left);
            int r = map.get(right);

            int baseHeight = query(1 , 0 , n-1 , l , r);
            int newHeight = baseHeight + side;

            update(1, 0 , n-1 , l , r , newHeight);
            maxHeight = Math.max(maxHeight,newHeight);

            ans.add(maxHeight);

        }
    return ans;
    }
}