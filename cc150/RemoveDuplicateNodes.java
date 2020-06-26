package cc150;

import java.util.HashSet;

import linkedlist.ListNode;

/**
 * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
 * 示例1:
 * 输入：[1, 2, 3, 3, 2, 1]
 * 输出：[1, 2, 3]
 * 示例2:
 * 输入：[1, 1, 1, 1, 2]
 * 输出：[1, 2]
 * 提示：
 * 链表长度在[0, 20000]范围内。
 * 链表元素在[0, 20000]范围内。
 * 进阶：
 * 如果不得使用临时缓冲区，该怎么解决？
 * 20200626
 */
public class RemoveDuplicateNodes {
    /**
     * 题意：删除ListNode中重复的node。
     * one pass 解法，hashset，我还写了好一会儿。另外其实这儿的dummy只要指向head就行了。
     */
    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null) return head;
        HashSet<Integer> set = new HashSet<>();
        ListNode dummy = new ListNode(-1), prev = new ListNode(-1);
        dummy.next = head;
        while (head != null) {
            if (!set.add(head.val)) {
                prev.next = head.next;
                head = head.next;
            } else {
                prev = head;
                head = head.next;
            }
        }
        return dummy.next;
    }

    /**
     * O1 space写法，挺难写的。。for用多了，while用起来有点不熟
     */
    public ListNode removeDuplicateNodes_(ListNode head) {
        if (head == null) return head;
        ListNode dummy = head;
        while (head != null) {
            ListNode t = head;
            while (t.next != null) {
                if (head.val == t.next.val) {
                    t.next = t.next.next;
                } else {
                    t = t.next;
                }
            }
            head = head.next;
        }
        return dummy;
    }
}
