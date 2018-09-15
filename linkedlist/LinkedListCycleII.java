package linkedlist;

/**
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * <p>
 * Note: Do not modify the linked list.
 * <p>
 * Follow up:
 * Can you solve it without using extra space?
 * Created by DrunkPiano on 2017/3/26.
 */

class LinkedListCycleII {
    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        ListNode walker = head;
        ListNode runner = head;
        ListNode meetPoint = null;
        while (runner.next != null && runner.next.next != null) {
            walker = walker.next;
            runner = runner.next.next;
            if (walker == runner) {
                meetPoint = walker;
                break;
            }
        }

        if (meetPoint == null) return null;
        while (head != meetPoint) {
            head = head.next;
            meetPoint = meetPoint.next;
        }
        return head;
    }
}
