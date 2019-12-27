package array;


import linkedlist.ListNode;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * Example:
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 */

public class AddTwoNumbers {
    /**
     * 题意：两个链表代表两个数字，每一位反向存储在链表里，求相加的结果，输出仍用链表表示。
     * 解法：容易错的地方是，在两个list都end之后还要考虑有没有额外的进位
     * 20191227 review
     **/
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1), cur = dummy;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int v1 = l1 != null ? l1.val : 0;
            int v2 = l2 != null ? l2.val : 0;
            int sum = v1 + v2 + carry;
            int num = sum % 10;
            carry = sum / 10;
            cur.next = new ListNode(num);
            cur = cur.next;
            if (l1 != null) l1 = l1.next;//已犯错误1. 忘记迭代l1 l2
            if (l2 != null) l2 = l2.next;
        }
        if (carry == 1) cur.next = new ListNode(1);//已犯错误2. 忘记考虑有没有额外的进位
        return dummy.next;
    }

    /**
     * 老代码
     */
    public ListNode addTwoNumbers__(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode p1 = l1, p2 = l2, fakeHead = new ListNode(-1), p3 = fakeHead;
        while (p1 != null || p2 != null) {
            if (p1 != null) {
                carry += p1.val;
                p1 = p1.next;
            }
            if (p2 != null) {
                carry += p2.val;
                p2 = p2.next;
            }
            p3.next = new ListNode(carry % 10);
            p3 = p3.next;
            carry = carry / 10;
        }
        if (carry == 1)
            p3.next = new ListNode(carry);
        return fakeHead.next;
    }
}
