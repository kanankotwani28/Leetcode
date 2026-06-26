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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();

        if(root == null )
            return result;
        
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        boolean lr = true ;

        while(!queue.isEmpty())
        {
            int levelNum = queue.size();
            List<Integer> subList = new LinkedList<>();
            for(int i = 0 ; i<levelNum ; i++)
            {
                TreeNode node = queue.poll();
                 if (lr)
                    subList.add(node.val);         
                else
                    subList.add(0, node.val);
                    
                if(node.left != null ){
                    queue.offer(node.left);
                }
                if(node.right!=null)
                {
                    queue.offer(node.right);
                }
            }

            lr =!lr;
            result.add(subList);
        }
        return result;
    }
}