import java.util.*;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {

        // Available servers: {weight, index}
        PriorityQueue<int[]> available = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0])
                    return a[0] - b[0];
                return a[1] - b[1];
            }
        );

        // Busy servers: {freeTime, weight, index}
        PriorityQueue<int[]> busy = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0])
                    return a[0] - b[0];
                return a[1] - b[1];
            }
        );

        int n = servers.length;
        int m = tasks.length;

        // Initially, all servers are available
        for (int i = 0; i < n; i++) {
            available.offer(new int[]{servers[i], i});
        }

        int[] ans = new int[m];
        long time = 0;

        for (int i = 0; i < m; i++) {

            // Current time must be at least task arrival time
            time = Math.max(time, i);

            // Release all servers that have completed their tasks
            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                int[] server = busy.poll();

                available.offer(new int[]{server[1], server[2]});
            }

            // If no server is available, move time forward
            if (available.isEmpty()) {

                time = busy.peek()[0];

                // Release all servers free at this time
                while (!busy.isEmpty() && busy.peek()[0] <= time) {
                    int[] server = busy.poll();

                    available.offer(new int[]{server[1], server[2]});
                }
            }

            // Select the best available server
            int[] server = available.poll();

            int weight = server[0];
            int index = server[1];

            ans[i] = index;

            // Assign task and calculate server's free time
            busy.offer(new int[]{
                (int)(time + tasks[i]),
                weight,
                index
            });
        }

        return ans;
    }
}