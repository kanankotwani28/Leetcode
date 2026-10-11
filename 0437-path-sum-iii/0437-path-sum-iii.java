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
    public int pathSum(TreeNode root, int targetSum) {
        HashMap<Long,Integer> map = new HashMap<>();
        map.put(0L,1);
        return countPath(root,map,0L,targetSum);
    }
    private int countPath(TreeNode root , HashMap<Long,Integer>map , long sum , int target){
        if(root == null) return 0;
        sum += root.val;
        
        long need = sum - target;
        int count = map.getOrDefault(need,0);
        map.put(sum, map.getOrDefault(sum, 0) + 1);

        count += countPath(root.left , map , sum , target);
        count += countPath(root.right , map , sum , target);

        map.put(sum,map.get(sum)-1);

        return count;
    }
}