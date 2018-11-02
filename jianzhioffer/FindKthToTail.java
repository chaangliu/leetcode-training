package jianzhioffer;

import linkedlist.ListNode;

/**
 * 输入一个链表，输出该链表中倒数第k个结点。
 */
public class FindKthToTail {

    //快慢指针。这题改了好几遍才AC.. k<=0那儿，还有k>1那儿需要注意。要考虑k > len的时候。
    public ListNode FindKthToTail(ListNode head, int k) {
        if (head == null || k < 1) return null;
        ListNode fast = head, slow = head;
        while (fast.next != null) {
            fast = fast.next;
            k--;
            if (k <= 0) {
                slow = slow.next;
            }
        }
        if (k > 1) return null;//nowcoder的corner case
        return slow;
    }

    public static void main(String args[]) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);
        ListNode res = new FindKthToTail().FindKthToTail(node, 5);
    }
}
