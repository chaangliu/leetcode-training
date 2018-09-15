package linkedlist;

/**
 * Given a linked list, remove the nth node from the end of list and return its head.
 * For example,
 * Given linked list: 1->2->3->4->5, and n = 2.
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:
 * Given n will always be valid.
 * Try to do this in one pass.
 * Created by DrunkPiano on 2017/3/4.
 */

public class RemoveNthNodeFromEndOfList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (0 == n) return null;

        ListNode runner = head;
        ListNode walker = head;

        int i = 0;
        while (runner.next != null) {
            if (i < n) {
                runner = runner.next;
                i++;
            } else {
                break;
            }
        }
        while (runner.next != null) {
            walker = walker.next;
            runner = runner.next;
        }
        walker.next = walker.next.next;
        return head;
    }
}
