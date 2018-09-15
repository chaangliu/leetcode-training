package linkedlist;

/**
 * Reverse a linked list from position m to n. Do it in-place and in one-pass.
 * For example:
 * Given 1->2->3->4->5->NULL, m = 2 and n = 4,
 * return 1->4->3->2->5->NULL.
 * Note:
 * Given m, n satisfy the following condition:
 * 1 ≤ m ≤ n ≤ length of list.
 * <p>
 * <p>
 * <p>
 * <p>
 * Created by DrunkPiano on 2017/3/24.
 */

public class ReverseLinkedListII {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        // we need for nodes
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode start;
        ListNode then;
        for (int i = 0; i < m - 1; i++) {
            pre = pre.next;
        }

        start = pre.next;
        then = start.next;

        for (int i = 0; i < n - m; i++) {
            start.next = then.next;
            then.next = pre.next; //it has to be pre.next，而不是start，因为start和then一直在向右平移，交换过一次后pre已经不会指向start
            pre.next = then;
            then = start.next;
        }

        return dummy.next;
    }
}
