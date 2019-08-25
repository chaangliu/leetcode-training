package linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * Given the head of a linked list, we repeatedly delete consecutive sequences of nodes that sum to 0 until there are no such sequences.
 * <p>
 * After doing so, return the head of the final linked list.  You may return any such answer.
 * <p>
 * <p>
 * <p>
 * (Note that in the examples below, all sequences are serializations of ListNode objects.)
 * <p>
 * Example 1:
 * <p>
 * Input: head = [1,2,-3,3,1]
 * Output: [3,1]
 * Note: The answer [1,2,1] would also be accepted.
 * Example 2:
 * <p>
 * Input: head = [1,2,3,-3,4]
 * Output: [1,2,4]
 * Example 3:
 * <p>
 * Input: head = [1,2,3,-3,-2]
 * Output: [1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The given linked list will contain between 1 and 1000 nodes.
 * Each node in the linked list has -1000 <= node.val <= 1000.
 * 20190825
 */
public class RemoveZeroSumConsecutiveNodesfromLinkedList {
    /**
     * 把链表里所有的连续和为0的node删除掉，让最终的list里不存在subarray和为0。
     * 【我的解法】
     * 一开始我感觉要用dp？但看了下数据觉得可以用brute force。
     * 我用了递归A的。
     */
    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        helper(dummy);
        return dummy.next;
    }

    private ListNode helper(ListNode dummy) {
        boolean foundHead = false;
        ListNode prev = dummy;
        ListNode head = prev.next;
        while (head != null) {
            ListNode cut = findStart(head);
            if (cut == null) {// reached end
                foundHead = true;
                prev.next = null;
                break;
            }
            if (cut.val != 1001) {
                foundHead = true;
                prev.next = cut;
                break;
            }
            prev = prev.next;// prev = head X
            head = head.next;
        }
        if (foundHead) {
            helper(dummy);
        }
        return dummy;
    }

    private ListNode findStart(ListNode head) {
        int cnt = 0;
        while (head != null) {
            cnt += head.val;
            if (cnt == 0) {
                return head.next;
            }
            head = head.next;
        }
        return new ListNode(1001);
    }

    /**
     * 前缀和解法，遇到了相同的前缀和，说明中间有一段相加抵消掉了。
     */
    public ListNode removeZeroSumSublists__(ListNode head) {
        if (head == null) return head;
        Map<Integer, ListNode> sums = new HashMap<>();
        int sum = 0;
        ListNode curr = head;
        while (curr != null) {
            sum += curr.val;
            if (sum == 0)
                head = curr.next;
            if (sums.containsKey(sum))
                sums.get(sum).next = curr.next;
            else
                sums.put(sum, curr);
            curr = curr.next;
        }
        return head;
    }
}
