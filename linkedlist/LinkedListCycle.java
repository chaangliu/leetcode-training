package linkedlist;

/**
 * Given a linked list, determine if it has a cycle in it.
 * <p>
 * Follow up:
 * Can you solve it without using extra space?
 * <p>
 * <p>
 * Created by DrunkPiano on 2017/3/26.
 */

public class LinkedListCycle {
    /**
     * 题意：判断链表是否有环
     * 解法：快慢指针
     */
    public boolean hasCycle(ListNode head) {
        ListNode dummy = head;
        while (head != null && dummy != null && dummy.next != null) {// head != null可以省略，dummy!=null不能省略
            head = head.next;
            dummy = dummy.next.next;
            if (head == dummy) return true;
        }
        return false;
    }
}
