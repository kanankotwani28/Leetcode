class Solution {
    public int numOfMinutes(
            int n, int headID, int[] manager, int[] informTime) {
        List<List<Integer>> children = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            children.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            if (manager[i] != -1) {
                children.get(manager[i]).add(i);
            }
        }
        return dfs(headID, children, informTime);
    }

    private int dfs(
            int employee,
            List<List<Integer>> children,
            int[] informTime) {

        int maxTime = 0;

        for (int child : children.get(employee)) {
            maxTime = Math.max(
                maxTime,
                dfs(child, children, informTime)
            );
        }

        return informTime[employee] + maxTime;
    }
}
