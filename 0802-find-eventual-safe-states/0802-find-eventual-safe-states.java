class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<List<Integer>> rev = new ArrayList<>();
        int V = graph.length;
        for(int i = 0 ; i<V ; i++){
            rev.add(new ArrayList<>());
        }

        int outDegree[] = new int[V];
        for(int i = 0 ; i< V ; i++){
            outDegree[i] = graph[i].length;
            for(int nei : graph[i]){
                rev.get(nei).add(i);
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for(int i = 0 ; i < V ; i++){
            if(outDegree[i] == 0) q.offer(i);
        }

        int safe[] = new int[V];
        while(!q.isEmpty()){
            int p = q.poll();
            safe[p] = 1;
            for(int i : rev.get(p)){
                outDegree[i]--;
                if(outDegree[i] == 0) q.offer(i);
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (safe[i] == 1) {
                ans.add(i);
            }
        }
        return ans;
    }
}