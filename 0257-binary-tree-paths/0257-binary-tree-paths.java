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
    List<String> ans = new ArrayList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        preorder(root, "");
        return ans;
    }

    private void preorder(TreeNode root, String path) {
        if (root == null) return;
        path += root.val;
        if (root.left == null && root.right == null) {
            ans.add(path);
            return;
        }
        path += "->";
        preorder(root.left, path);
        preorder(root.right, path);
    }
}