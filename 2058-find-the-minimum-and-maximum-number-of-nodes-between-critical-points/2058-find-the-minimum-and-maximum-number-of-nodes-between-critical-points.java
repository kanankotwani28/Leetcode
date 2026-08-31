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
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int first = -1;
        int prevCritical = -1;
        int minDist = Integer.MAX_VALUE;
        int maxDist = -1;

        int pos = 1;

        ListNode prev = head;
        ListNode curr = head.next;

        while (curr != null && curr.next != null) {
            boolean isCritical =
                (curr.val > prev.val && curr.val > curr.next.val) ||
                (curr.val < prev.val && curr.val < curr.next.val);

            if (isCritical) {
                if (first == -1) 
                    first = pos;

                if (prevCritical != -1) {
                    minDist = Math.min(minDist, pos - prevCritical);
                }
                prevCritical = pos;
                maxDist = pos - first;
            }
            prev = curr;
            curr = curr.next;
            pos++;
        }
        if (minDist == Integer.MAX_VALUE) {
            return new int[]{-1, -1};
        }
        return new int[]{minDist, maxDist};
    }
}