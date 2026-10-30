
import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            graph[e[0]].add(new int[]{e[1], e[2]});
        }

        // dist[node][count] = minimum distance to this state
        int[][] dist = new int[n][k + 1];

        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // State: {distance, node, consecutive count}
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );

        dist[0][1] = 0;
        pq.offer(new int[]{0, 0, 1});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();

            int d = curr[0];
            int node = curr[1];
            int count = curr[2];

            if (d != dist[node][count]) {
                continue;
            }

            if (node == n - 1) {
                return d;
            }

            for (int[] edge : graph[node]) {
                int next = edge[0];
                int weight = edge[1];

                int newCount;

                if (labels.charAt(next) == labels.charAt(node)) {
                    newCount = count + 1;
                } else {
                    newCount = 1;
                }

                if (newCount > k) {
                    continue;
                }

                int newDist = d + weight;

                if (newDist < dist[next][newCount]) {
                    dist[next][newCount] = newDist;
                    pq.offer(new int[]{
                        newDist, next, newCount
                    });
                }
            }
        }

        return -1;
    }
}