import java.util.ArrayList;
import java.util.List;

class SnapshotArray {

    // Each index has its own history of (snapId, value) pairs.
    private List<int[]>[] history;

    // ID of the snapshot currently being created.
    private int snapId = 0;

    @SuppressWarnings("unchecked")
    public SnapshotArray(int length) {
        history = new ArrayList[length];

        for (int i = 0; i < length; i++) {
            history[i] = new ArrayList<>();

            // Initially, every index contains 0.
            history[i].add(new int[]{0, 0});
        }
    }

    public void set(int index, int val) {
        List<int[]> changes = history[index];

        int last = changes.size() - 1;

        // If this index was already updated in the current
        // snapshot, overwrite its value instead of adding a pair.
        if (changes.get(last)[0] == snapId) {
            changes.get(last)[1] = val;
        } else {
            // Otherwise, record a new (snapshot ID, value) pair.
            changes.add(new int[]{snapId, val});
        }
    }

    public int snap() {
        // Save the current snapshot and move to the next ID.
        return snapId++;
    }

    public int get(int index, int snap_id) {
        List<int[]> changes = history[index];

        int low = 0;
        int high = changes.size() - 1;

        // Find the rightmost entry whose snapshot ID <= snap_id.
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (changes.get(mid)[0] <= snap_id) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        // high points to the latest applicable value.
        return changes.get(high)[1];
    }
}