class Solution {
    public ListNode insertionSortList(ListNode head) {

        // Dummy node to make insertion easier
        ListNode dummy = new ListNode(0);

        ListNode curr = head;

        while (curr != null) {

            // Save next node
            ListNode next = curr.next;

            // Start searching from beginning
            ListNode prev = dummy;

            // Find correct position
            while (prev.next != null && prev.next.val < curr.val) {
                prev = prev.next;
            }

            // Insert curr
            curr.next = prev.next;
            prev.next = curr;

            // Move to next unsorted node
            curr = next;
        }

        return dummy.next;
    }
}