class Solution {
    class DisjointSet{
        int[] parent;
        int[] size;

        DisjointSet(int n){
            parent = new int[n];
            size = new int[n];

            for(int i = 0 ; i<n ; i++){
                parent[i] = i;
                size[i] = 1;
            }
        }
            int findParent(int node){
                if(parent[node] == node) return node;
                return parent[node] = findParent(parent[node]);
            }

            void unionbysize(int u , int v){
                int pu = findParent(u);
                int pv = findParent(v);

                if(pu == pv) return;

                if(size[pu] < size[pv]){
                    parent[pu] = pv;
                    size[pv] += size[pu];
                }

                else{
                    parent[pv] = pu;
                    size[pu] += size[pv];
                }
                
            }
        }
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {

        DisjointSet ds = new DisjointSet(n);
        for(int i = 1 ; i< n ; i++){
            if(nums[i] - nums[i-1] <= maxDiff){
                ds.unionbysize(i,i-1);
            }
        }

        boolean []ans = new boolean[queries.length];
        for(int i = 0 ; i< queries.length ; i++){
            int u = queries[i][0];
            int v = queries[i][1];

            ans[i] = (ds.findParent(u) == ds.findParent(v));
        }
        
        return ans;
    }
}