
import java.util.*;

class Solution {
    public String simplifyPath(String path) {
        // Split on one or more slashes.
        String[] parts = path.split("/+");

        // Stack stores the directories in the canonical path.
        Deque<String> stack = new ArrayDeque<>();

        for (String part : parts) {

            // Empty tokens and "." do not change the path.
            if (part.isEmpty() || part.equals(".")) {
                continue;
            }

            if (part.equals("..")) {
                // Move to the parent directory if possible.
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                // A normal directory name, including "...".
                stack.push(part);
            }
        }

        // Build the canonical absolute path.
        StringBuilder result = new StringBuilder();

        while (!stack.isEmpty()) {
            // Stack is LIFO, so reverse the collected directories.
            result.insert(0, "/" + stack.pop());
        }

        // An empty result represents the root directory.
        return result.length() == 0 ? "/" : result.toString();
    }
}
