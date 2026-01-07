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
    long totalSum = 0;
    long maxProduct = 0 ;
    static final int MOD = 1_000_000_007;

    public int maxProduct(TreeNode root) {
        totalSum = findTotalSum(root);

        getDfs(root);
    
    return (int) (maxProduct % MOD);
    }

    public long findTotalSum(TreeNode root){
        if(root == null) return 0;
        return root.val + findTotalSum(root.left)+ findTotalSum(root.right);
    }

    public long getDfs(TreeNode root){
        if(root == null) return 0;

        long left = getDfs(root.left);
        long right = getDfs(root.right);

        long curr = left + right + root.val;
        long pro = curr * (totalSum - curr);

        maxProduct = Math.max(maxProduct , pro);

        return curr;
    }
}