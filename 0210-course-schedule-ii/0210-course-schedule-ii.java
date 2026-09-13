class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0 ; i<numCourses ; i++){
            adj.add(new ArrayList<>());
        }

        for(int i = 0 ; i< prerequisites.length ; i++){
            int u = prerequisites[i][0];
            int v = prerequisites[i][1];
            adj.get(v).add(u);
        }

        int inDegree[] = new int[numCourses];
        for(int  i = 0 ; i < numCourses ; i++){
            for(int it: adj.get(i)){
                inDegree[it]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for(int i = 0 ; i< numCourses ; i++){
            if(inDegree[i] == 0) q.add(i);
        }
        int ans[] = new int[numCourses];
        int idx = 0;
        while(!q.isEmpty()){
            int p = q.poll();
            ans[idx++] = p;
            for(int it : adj.get(p)){
                inDegree[it]--;
                if(inDegree[it] == 0) q.add(it);
            } 
        }

        if(idx != numCourses) return new int[]{};
       return ans; 
    }
}