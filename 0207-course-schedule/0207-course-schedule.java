import java.util.*;
// We are not doing the classical parent solution here because the graph is directed hence we need to keep the path visiited
class Solution {

    private boolean dfs(int node, List<List<Integer>> adj, int[] vis, int[] pathVis) {
        vis[node] = 1;
        pathVis[node] = 1;

        for (int adjNode : adj.get(node)) {
            // If not visited → DFS
            if (vis[adjNode] == 0) {
                if (dfs(adjNode, adj, vis, pathVis)) return true;
            }
            // If already in current path → cycle
            else if (pathVis[adjNode] == 1) {
                return true;
            }
        }

        pathVis[node] = 0; // backtrack
        return false;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        // Build graph
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] p : prerequisites) {
            int a = p[0], b = p[1];
            adj.get(b).add(a);  // b → a
        }

        int[] vis = new int[numCourses];
        int[] pathVis = new int[numCourses];

        // Check all components
        for (int i = 0; i < numCourses; i++) {
            if (vis[i] == 0) {
                if (dfs(i, adj, vis, pathVis)) {
                    return false; // cycle found → cannot finish
                }
            }
        }

        return true; // no cycle
    }
}