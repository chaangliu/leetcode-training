package cc150;

import linkedlist.ListNode;

/**
 * cc150: 02.04
 * 编写程序以 x 为基准分割链表，使得所有小于 x 的节点排在大于或等于 x 的节点之前。如果链表中包含 x，x 只需出现在小于 x 的元素之后(如下所示)。分割元素 x 只需处于“右半部分”即可，其不需要被置于左右两部分之间。
 * 示例:
 * 输入: head = 3->5->8->5->10->2->1, x = 5
 * 输出: 3->1->2->10->5->5->8
 */
public class PartitionList {
    /**
     * 题意：把链表中小于x的节点排在大于等于x的节点之前，顺序不重要。
     * 解法：头插法，遍历，小于的话，就拎到开头，并且把head指向它；否则继续遍历。注意，这个写法，第一个node并没有一开始就处理。ref:https://leetcode-cn.com/problems/partition-list-lcci/solution/tou-cha-fa-java-by-panc-2/
     */
    public ListNode partition(ListNode head, int x) {
        if (head == null)
            return null;
        ListNode curNode = head;
        while (curNode.next != null) {
            if (curNode.next.val < x) {
                ListNode tmp = curNode.next;
                curNode.next = curNode.next.next;
                tmp.next = head;
                head = tmp;
            } else
                curNode = curNode.next;
        }
        return head;
    }
}
