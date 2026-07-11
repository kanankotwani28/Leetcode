class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        boolean[] visited = new boolean[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int[] info = new int[2]; 
                dfs(i, adj, visited, info);
                int nodes = info[0];
                int edgeCount = info[1] / 2;
                if (edgeCount == nodes * (nodes - 1) / 2) {
                    ans++;
                }
            }
        }

        return ans;
    }

    private void dfs(int node, List<Integer>[] adj, boolean[] visited, int[] info) {
        visited[node] = true;
        info[0]++;                      
        info[1] += adj[node].size();   

        for (int nei : adj[node]) {
            if (!visited[nei]) {
                dfs(nei, adj, visited, info);
            }
        }
    }
}