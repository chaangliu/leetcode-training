package jianzhioffer;

import linkedlist.ListNode;

/**
 * 输入一个链表，反转链表后，输出新链表的表头。
 */

public class ReverseList {
    /**
     * iteration
     */
//    public ListNode ReverseList(ListNode head) {
//        if (head == null) return null;
//        ListNode prev = null, cur = head;
//        while (cur != null) {
//            ListNode tmp = cur.next;
//            cur.next = prev;
//            prev = cur;
//            cur = tmp;
//        }
//        return prev;
//    }

    /**
     * Recursion
     * 没写出来。值得注意的是终止条件，head.next == null，保证最后一层递归结束后head是倒数第二个node，tail是最后一个node。
     */
    public ListNode ReverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode tail = ReverseList(head.next);
        head.next.next = head;
        head.next = null;
        return tail;
    }

    public static void main(String args[]) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        new ReverseList().ReverseList(node);
    }
}
