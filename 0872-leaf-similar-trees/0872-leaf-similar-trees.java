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
    private boolean isLeaf(TreeNode root){
        if(root.left == null && root.right == null) return true;
        return false;
    }
    private void addLeaves(TreeNode root, List<Integer> res){
        if(root == null) return;
        addLeaves(root.left,res);
        if(isLeaf(root)) res.add(root.val);
        addLeaves(root.right,res);
    }
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        addLeaves(root1,list1);
        addLeaves(root2,list2);

        return list1.equals(list2);
    }
}