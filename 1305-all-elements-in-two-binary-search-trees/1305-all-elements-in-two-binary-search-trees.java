class Solution {
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        List<Integer> res = new ArrayList<>();
        
        TreeNode curr1 = root1;
        TreeNode curr2 = root2;
        
        while (curr1 != null || curr2 != null || !s1.isEmpty() || !s2.isEmpty()) {
            
            // go left in both trees (your inorder idea)
            while (curr1 != null) {
                s1.push(curr1);
                curr1 = curr1.left;
            }
            
            while (curr2 != null) {
                s2.push(curr2);
                curr2 = curr2.left;
            }
            
            // now compare (your main logic)
            if (s2.isEmpty() || (!s1.isEmpty() && s1.peek().val <= s2.peek().val)) {
                TreeNode node = s1.pop();
                res.add(node.val);
                curr1 = node.right;   // move in tree1
            } else {
                TreeNode node = s2.pop();
                res.add(node.val);
                curr2 = node.right;   // move in tree2
            }
        }
        
        return res;
    }
}