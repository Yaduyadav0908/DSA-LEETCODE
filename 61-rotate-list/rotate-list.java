class Solution {
    public ListNode rotateRight(ListNode head, int k) {

        if (head == null || head.next == null || k == 0)
            return head;

        // Find length and last node
        ListNode last = head;
        int length = 1;

        while (last.next != null) {
            last = last.next;
            length++;
        }

        k = k % length;

        if (k == 0)
            return head;

        // Make circular list
        last.next = head;

        // Find new tail
        int steps = length - k;

        ListNode newTail = head;

        for (int i = 1; i < steps; i++) {
            newTail = newTail.next;
        }

        // New head
        ListNode newHead = newTail.next;

        // Break the circle
        newTail.next = null;

        return newHead;
    }
}