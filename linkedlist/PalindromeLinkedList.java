package linkedlist;

/**
 * Given a singly linked list, determine if it is a palindrome.
 * <p>
 * Example 1:
 * <p>
 * Input: 1->2
 * Output: false
 * Example 2:
 * <p>
 * Input: 1->2->2->1
 * Output: true
 * Follow up:
 * Could you do it in O(n) time and O(1) space?
 * <p>
 * 20190122
 */
public class PalindromeLinkedList {
    /**
     * 题意：判断链表是否是palindrome。要求O(1)space
     * 此题要求O(1) space，所以用List保存node的方法不好。
     * 一个方案是，reverse list到中间位置，然后从中间向两端对比。
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        ListNode slow = head, fast = head, rev = null; // 一边快慢指针，一边用rev记录反转的前半部分。rev会复用slow遇到的node所以是O(1)。
        while (fast != null && fast.next != null) {
            ListNode temp = rev;
            rev = slow;
            slow = slow.next;
            fast = fast.next.next;
            rev.next = temp;
        }
        if (fast != null) {
            slow = slow.next;
        }
        while (rev != null) {
            if (slow.val != rev.val) {
                return false;
            }
            slow = slow.next;
            rev = rev.next;
        }
        return true;
    }
}
