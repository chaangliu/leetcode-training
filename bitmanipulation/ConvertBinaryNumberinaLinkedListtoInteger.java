package bitmanipulation;

import linkedlist.ListNode;

/**
 * Given head which is a reference node to a singly-linked list. The value of each node in the linked list is either 0 or 1. The linked list holds the binary representation of a number.
 * Return the decimal value of the number in the linked list.
 * Example 1:
 * Input: head = [1,0,1]
 * Output: 5
 * Explanation: (101) in base 2 = (5) in base 10
 * Example 2:
 * Input: head = [0]
 * Output: 0
 * Example 3:
 * Input: head = [1]
 * Output: 1
 * Example 4:
 * Input: head = [1,0,0,1,0,0,1,1,1,0,0,0,0,0,0]
 * Output: 18880
 * Example 5:
 * Input: head = [0,0]
 * Output: 0
 * Constraints:
 * The Linked List is not empty.
 * Number of nodes will not exceed 30.
 * Each node's value is either 0 or 1.
 * 20191217
 */
public class ConvertBinaryNumberinaLinkedListtoInteger {
    /**
     * 题意：把一个链表转化成integer。链表最长30个node。
     * 因为遍历链表先遇到的node是integer的高位，所以我跑了两遍，然后才从低到高开始计算。
     * 其实不用，可以用 num = num * 2 + bit-val 这个式子实现one pass
     */
    public int getDecimalValue(ListNode head) {
        int ans = 0;
        while (head != null) {
            ans = (ans << 1) | head.val;
            head = head.next;
        }
        return ans;
    }

    public int getDecimalValue_(ListNode head) {
        int num = 0;
        while (head != null) {
            num = num * 2 + head.val;
            head = head.next;
        }
        return num;
    }

    /**
     * my code
     */
    public int getDecimalValue__(ListNode head) {
        int res = 0, len = 0;
        ListNode node = head;
        while (node != null) {
            node = node.next;
            len++;
        }
        while (head != null) {
            res += head.val * (int) Math.pow(2, --len);
            head = head.next;
        }
        return res;
    }
}
