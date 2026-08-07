/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) {
 *         this.val = val;
 *         this.next = next;
 *     }
 * }
 */
class Solution {
    public void reorderList(ListNode head) {

        if (head == null || head.next == null) {
            return;
        }

        // Step 1: Find middle
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // Split the list
        prev.next = null;

        // Step 2: Reverse second half
        ListNode curr = slow;
        ListNode rev = null;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = rev;
            rev = curr;
            curr = next;
        }

        // Step 3: Merge two halves
        ListNode first = head;
        ListNode second = rev;

        while (second != null) {
            ListNode temp1 = first.next;
            ListNode temp2 = second.next;

            first.next = second;

            if (temp1 == null) {
                break;
            }

            second.next = temp1;

            first = temp1;
            second = temp2;
        }
    }
}