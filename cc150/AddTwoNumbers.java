package cc150;

import linkedlist.ListNode;

/**
 * 给定两个用链表表示的整数，每个节点包含一个数位。
 * 这些数位是反向存放的，也就是个位排在链表首部。
 * 编写函数对这两个整数求和，并用链表形式返回结果。
 * 示例：
 * <p>
 * 输入：(7 -> 1 -> 6) + (5 -> 9 -> 2)，即617 + 295
 * 输出：2 -> 1 -> 9，即912
 * 进阶：假设这些数位是正向存放的，请再做一遍。
 * 示例：
 * 输入：(6 -> 1 -> 7) + (2 -> 9 -> 5)，即617 + 295
 * 输出：9 -> 1 -> 2，即912
 */
public class AddTwoNumbers {
    /**
     * 题意：把两个list node的值做加法，返回一个list node。
     * 解法：leetcode原题，随手写了一下。主要就是要考虑一些corner case，尤其是最后一位有进位。这题不需要考虑数据范围，因为是返回listnode而不是数字。
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1), runner = dummy;
        while (l1 != null || l2 != null) {
            int carry = 0, res = 0;
            while (l1 != null || l2 != null) {
                int sum = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + carry;
                int curDigit = (sum) % 10;
                carry = (sum) / 10;
                if (l1 != null) l1 = l1.next;
                if (l2 != null) l2 = l2.next;
                runner.next = new ListNode(curDigit);
                runner = runner.next;
            }
            if (carry == 1) runner.next = new ListNode(1);
        }
        return dummy.next;
    }

    /**
     * 递归写法
     */
    public ListNode addTwoNumbers__(ListNode l1, ListNode l2) {
        return sum(l1, l2, 0);
    }

    ListNode sum(ListNode s1, ListNode s2, int buy) {
        int currentLevelSum = s1.val + s2.val + buy;
        ListNode currentNode = new ListNode(currentLevelSum % 10);
        if (s1.next == null && s2.next == null) {
            if (currentLevelSum / 10 != 0) {
                currentLevelSum = currentLevelSum / 10;
                currentNode.next = new ListNode(currentLevelSum);
            }
            return currentNode;
        }
        s1 = s1.next != null ? s1.next : new ListNode(0);
        s2 = s2.next != null ? s2.next : new ListNode(0);
        currentNode.next = sum(s1, s2, currentLevelSum / 10);
        return currentNode;
    }
}
