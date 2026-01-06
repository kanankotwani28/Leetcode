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
    public int maxLevelSum(TreeNode root) {
        if (root == null) return 0;

        int max = Integer.MIN_VALUE;
        int ansLevel = 1;
        int level = 1;
        Queue <TreeNode> q = new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()){
            int sum = 0 ;
            int s = q.size();

            for(int i =0 ; i<s ; i++){
                TreeNode node = q.poll();
                sum += node.val;

                if(node.left!= null) q.offer(node.left);
                if(node.right!= null) q.offer(node.right);            
            }

            if(sum > max){
                max = sum;
                ansLevel = level;
            }
            level++ ;
        }
        return ansLevel;
    }
}