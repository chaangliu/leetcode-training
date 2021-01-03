package linkedlist;

/**
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 * <p>
 * You should preserve the original relative order of the nodes in each of the two partitions.
 * <p>
 * Example:
 * <p>
 * Input: head = 1->4->3->2->5->2, x = 3
 * Output: 1->2->2->4->3->5
 * <p>
 * 20190115
 */
public class PartitionList {
    /**
     * 题意：给你一个链表和一个数字x，把小于x的node放在前面大于等于x的node放在后面。
     * 解法：直接撸下来放到两个list上，最后在接起来就好了。初学者容易忘记把tail.next置空。
     */
    public ListNode partition(ListNode head, int x) {
        ListNode l1 = new ListNode(-1), l2 = new ListNode(-1);
        ListNode l1Head = l1, l2Head = l2;
        while (head != null) {
            if (head.val < x) {
                l1.next = head;
                l1 = l1.next;
            } else {
                l2.next = head;
                l2 = l2.next;
            }
            head = head.next;
        }
        l1.next = l2Head.next;
        l2.next = null;
        return l1Head.next;
    }
}
