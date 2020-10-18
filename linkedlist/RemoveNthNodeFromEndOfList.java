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
 * 20200102 review
 */

public class RemoveNthNodeFromEndOfList {
    /**
     * 题意：删除倒数第n个node
     * 解法：one pass的方法就是快慢指针，但是要注意特殊case比如[1]1, [1,2]2
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1), fast = dummy, slow = dummy;// 已犯错误：一开始把fast,slow都指向head了，这样没法处理[1]1
        dummy.next = head;
        for (int i = 0; i < n; i++) fast = fast.next;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    /**
     * O(2n)写法，先计算长度，再走len - n步。这样没有上面的方法好。
     */
    public ListNode removeNthFromEnd_(ListNode head, int n) {
        ListNode dummy = head;
        int len = 0;
        while (head != null) {
            head = head.next;
            len++;
        }
        if (n == len) return dummy.next;
        int steps = len - n - 1;
        head = dummy;
        while (steps-- > 0) {
            head = head.next;
        }
        if (head != null) head.next = head.next.next;
        return dummy;
    }
}
