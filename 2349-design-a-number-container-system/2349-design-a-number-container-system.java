
import java.util.*;

class NumberContainers {

    // Stores the current number assigned to each index.
    private Map<Integer, Integer> indexToNumber;

    // Stores a min-heap of candidate indices for each number.
    private Map<Integer, PriorityQueue<Integer>> numberToIndices;

    public NumberContainers() {
        indexToNumber = new HashMap<>();
        numberToIndices = new HashMap<>();
    }

    public void change(int index, int number) {

        // Update the current value at this index.
        indexToNumber.put(index, number);

        // Get or create the min-heap for this number.
        numberToIndices
            .computeIfAbsent(number, k -> new PriorityQueue<>())
            .offer(index);
    }

    public int find(int number) {

        // No heap means this number has never been inserted.
        PriorityQueue<Integer> minHeap = numberToIndices.get(number);

        if (minHeap == null) {
            return -1;
        }

        // Remove stale indices until the smallest one is valid.
        while (!minHeap.isEmpty()) {

            int index = minHeap.peek();

            // Check whether this index still contains this number.
            if (indexToNumber.get(index) == number) {
                return index;
            }

            // The index now contains another number, so discard it.
            minHeap.poll();
        }

        // No valid index remains for this number.
        return -1;
    }
}
