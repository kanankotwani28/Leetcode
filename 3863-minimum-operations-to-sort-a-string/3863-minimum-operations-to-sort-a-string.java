class Solution {
    public int minOperations(String s) {
        // Convert the input string into a char array for easy indexed access
        char[] chars = s.toCharArray();
        int length = chars.length;

        // Track whether the array is already in non-decreasing order
        boolean isSorted = true;

        // Track the minimum and maximum characters in the array,
        // initialized with the first element
        char minChar = chars[0];
        char maxChar = chars[0];

        // Single pass to update min/max and check the sorted condition
        for (int i = 1; i < length; ++i) {
            minChar = (char) Math.min(minChar, chars[i]);
            maxChar = (char) Math.max(maxChar, chars[i]);
            // If the current element is smaller than its predecessor,
            // the array is not sorted
            if (chars[i] < chars[i - 1]) {
                isSorted = false;
            }
        }

        // Case 1: already sorted, no operation needed
        if (isSorted) {
            return 0;
        }

        // Case 2: only two elements and not sorted, impossible to fix
        if (length == 2) {
            return -1;
        }

        // Case 3: if the first element is the global minimum,
        // or the last element is the global maximum, one operation suffices
        if (chars[0] == minChar || chars[length - 1] == maxChar) {
            return 1;
        }

        // Case 4: if any interior element equals the global min or max,
        // two operations are required
        for (int i = 1; i < length - 1; ++i) {
            if (chars[i] == minChar || chars[i] == maxChar) {
                return 2;
            }
        }

        // Case 5: otherwise, three operations are needed
        return 3;
    }
}
