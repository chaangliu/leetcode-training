package linkedlist;

/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 * Example 1:
 * Input: 4->2->1->3
 * Output: 1->2->3->4
 * Example 2:
 * Input: -1->5->3->4->0
 * Output: -1->0->3->4->5
 * Created by DrunkPiano on 04/07/2017.
 * 20200208 review
 */

public class SortList {
    /**
     * 题意：sort一个链表，要求constant space。
     * 解法：merge sort。注意做法：快慢指针分成两个list，然后递归sort，最后merge。
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode prev = new ListNode(-1);
        ListNode slow = head;
        ListNode fast = head;
        // 用快慢指针来找到中间点
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        // prev把slow前面那个node跟slow之间的link断开
        prev.next = null;
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);
        return merge(l1, l2);
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode node = new ListNode(-1);
        ListNode l = node;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                l.next = l1;
                l1 = l1.next;
            } else {
                l.next = l2;
                l2 = l2.next;
            }
            l = l.next;
        }
        if (l1 != null) {
            l.next = l1;
        }
        if (l2 != null) {
            l.next = l2;
        }
        return node.next;
    }
}
