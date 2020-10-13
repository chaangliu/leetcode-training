package linkedlist;


/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 * For example,
 * <p>
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 * Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
 * Created by DrunkPiano on 2017/3/4.
 */

public class SwapNodesInPairs {
    /**
     * 题意：两两反转链表。
     * 解法：迭代写了挺久没写出来，递归写出来了，技巧是把next1,2都保存下来，不然很容易覆盖。
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode next2 = head.next.next;
        ListNode next1 = head.next;
        head.next.next = head;
        head.next = swapPairs(next2);
        return next1;
    }

    /**
     * 迭代, 也是同时保存后两个node
     */
    public ListNode swapPairs__(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode temp = dummyHead;
        while (temp.next != null && temp.next.next != null) {
            ListNode node1 = temp.next;
            ListNode node2 = temp.next.next;
            temp.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            temp = node1;
        }
        return dummyHead.next;
    }
}
