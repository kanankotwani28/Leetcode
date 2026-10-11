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
import java.util.*;

class Solution {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {

        // A HashSet lets us check quickly whether a node must be deleted.
        Set<Integer> deleteSet = new HashSet<>();

        for (int val : to_delete) {
            deleteSet.add(val);
        }

        // This list stores the roots of all remaining trees.
        List<TreeNode> forest = new ArrayList<>();

        // The original root starts a tree if it is not deleted.
        dfs(root, true, deleteSet, forest);

        return forest;
    }

    private TreeNode dfs(TreeNode node, boolean isRoot,
                         Set<Integer> deleteSet,
                         List<TreeNode> forest) {

        // An empty child has nothing to delete.
        if (node == null) return null;

        // Check whether the current node must be deleted.
        boolean deleted = deleteSet.contains(node.val);

        // If this node starts a tree and survives,
        // add it to the list of forest roots.
        if (isRoot && !deleted) {
            forest.add(node);
        }

        // Process children first.
        // A child becomes a potential new root if its parent is deleted.
        node.left = dfs(node.left, deleted, deleteSet, forest);
        node.right = dfs(node.right, deleted, deleteSet, forest);

        // If this node is deleted, return null to disconnect it
        // from its parent; otherwise, keep the node connected.
        return deleted ? null : node;
    }
}
