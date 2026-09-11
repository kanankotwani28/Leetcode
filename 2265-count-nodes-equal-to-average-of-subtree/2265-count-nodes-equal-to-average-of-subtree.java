/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int ans = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return ans;
    }

    // Returns [sum, count]
    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }

        // Get information from left and right subtrees
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);

        // Calculate current subtree's sum and count
        int sum = node.val + left[0] + right[0];
        int count = 1 + left[1] + right[1];

        // Check if node value equals floor(subtree average)
        if (node.val == sum / count) {
            ans++;
        }

        // Return sum and count to parent
        return new int[]{sum, count};
    }
}