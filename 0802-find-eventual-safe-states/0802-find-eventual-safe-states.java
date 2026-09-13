class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<List<Integer>> rev = new ArrayList<>();
        int V = graph.length;
        int outdegree[] = new int[V];
        for(int i = 0  ; i < graph.length ; i++){
            rev.add(new ArrayList<>());
        }

        for (int i = 0; i < V; i++) {
            outdegree[i] = graph[i].length;
            for (int neighbor : graph[i]) {
                rev.get(neighbor).add(i);
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (outdegree[i] == 0) {
                q.offer(i);
            }
        }

        boolean[] safe = new boolean[V];
        while (!q.isEmpty()) {
            int node = q.poll();
            safe[node] = true;
            for (int prev : rev.get(node)) {
                outdegree[prev]--;
                if (outdegree[prev] == 0) {
                    q.offer(prev);
                }
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (safe[i]) {
                ans.add(i);
            }
        }
        return ans;
    }
}