/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>(Comparator.comparingInt(a -> a.val));
        for(ListNode it : lists){
            if(it!= null) pq.add(it);
        }

        ListNode ans = new ListNode(0);
        ListNode dummy = ans;
        while(pq.size()!=0)
        {
            ListNode node = pq.poll();
            dummy.next = node;
            dummy = dummy.next;
            if(node.next != null) pq.add(node.next);
        }
        ans = ans.next;
        return ans;
    }
}