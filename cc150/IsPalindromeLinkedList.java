package cc150;

import linkedlist.ListNode;

public class IsPalindromeLinkedList {
    /**
     * 题意：用O(1) space判断一个链表是否是回文。
     * 解法：翻转链表前半部分，然后判断前后部分是否相等。
     */
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;

        while (fast != null && fast.next != null) {
            ListNode oldCur = slow;
            slow = slow.next;
            fast = fast.next.next;
            oldCur.next = prev; // 一边快慢指针一边翻转前半部分链表
            prev = oldCur;
        }

        if (fast != null) {
            // 链表个数为奇数
            slow = slow.next; // 实际上是往左退一格
        }

        // 判断prev（后半部分的头部）和slow（前半部分的新的头部）是否相等
        while (slow != null) {
            if (slow.val != prev.val) {
                return false;
            }
            slow = slow.next;
            prev = prev.next;
        }
        return true;
    }

    /**
     * 我的写法，先翻转后半部分，然后再对比。缺点是没有利用翻转链表的过程中实现快慢指针从而实现one pass
     */
    public boolean isPalindrome_(ListNode head) {
        ListNode dummy = head, fast = head, slow = head;
        int len = 0;
        while (head != null) {
            head = head.next;
            len++;
        }
        int half = (len + 1) / 2;
        while (half-- > 0) {
            fast = fast.next;
        }
        fast = reverse(fast);
        while (slow != null && fast != null) {
            if (slow.val != fast.val) return false;
            slow = slow.next;
            fast = fast.next;
        }
        return true;
    }

    private ListNode reverse(ListNode node) {
        if (node == null || node.next == null) return node;
        ListNode r = reverse(node.next);
        node.next.next = node;
        node.next = null;
        return r;
    }
}
