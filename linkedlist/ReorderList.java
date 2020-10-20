package linkedlist;

import java.util.Stack;

/**
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 示例 1:
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * 示例 2:
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 * 20201020
 */
public class ReorderList {
    /**
     * 题意：按规则给链表重新排序。
     * 我的解法：stack，写起来就看你细不细心。
     * 还有种方法空间O(1)，1. 寻找链表中点 2. 后半部分链表逆序 3. 合并链表
     */
    public void reorderList(ListNode head) {
        ListNode dummy = head;
        Stack<ListNode> s = new Stack<>();
        while (head != null) {
            s.push(head);
            head = head.next;
        }
        int n = s.size(), half = n / 2;
        head = dummy;
        while (half-- > 0) {
            ListNode tmp = head.next;
            head.next = s.pop();
            if (half == 0) {
                if (tmp != null) tmp.next = null;
                head.next.next = n % 2 == 0 ? null : tmp;
            } else {
                head.next.next = tmp;
                head = head.next.next;
            }
        }
        // head = dummy; // 这一行不需要
    }
}
