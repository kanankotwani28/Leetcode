class NodeValue {
    int minNode, maxNode, sum;

    NodeValue(int minNode, int maxNode, int sum) {
        this.minNode = minNode;
        this.maxNode = maxNode;
        this.sum = sum;
    }
}
class Solution {
    int maxSum = 0; 
    private NodeValue helper(TreeNode root) {
        if (root == null) {
            return new NodeValue(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        }
        NodeValue left = helper(root.left);
        NodeValue right = helper(root.right);
        if (left.maxNode < root.val && root.val < right.minNode) {
            int currSum = left.sum + right.sum + root.val;
            maxSum = Math.max(maxSum, currSum);
            return new NodeValue(
                Math.min(root.val, left.minNode),
                Math.max(root.val, right.maxNode),
                currSum
            );
        }
        return new NodeValue(
            Integer.MIN_VALUE,
            Integer.MAX_VALUE,
            0
        );
    }

    public int maxSumBST(TreeNode root) {
        helper(root);
        return maxSum;
    }
}