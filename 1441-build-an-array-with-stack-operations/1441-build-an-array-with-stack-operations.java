
import java.util.*;

class Solution {
    public List<String> buildArray(int[] target, int n) {
        List<String> operations = new ArrayList<>();

        int targetIndex = 0;

        // Only process numbers until the target is completely built.
        for (int num = 1; num <= n && targetIndex < target.length; num++) {

            // Every number read from the stream must be pushed first.
            operations.add("Push");

            if (num == target[targetIndex]) {
                // This number belongs in the target, so keep it.
                targetIndex++;
            } else {
                // This number is not needed, so remove it.
                operations.add("Pop");
            }
        }

        return operations;
    }
}
