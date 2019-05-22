package linkedlist;

/**
 * Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.
 * <p>
 * You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
 * <p>
 * Example 1:
 * <p>
 * Input: 1->2->3->4->5->NULL
 * Output: 1->3->5->2->4->NULL
 * Example 2:
 * <p>
 * Input: 2->1->3->5->6->4->7->NULL
 * Output: 2->3->6->7->1->5->4->NULL
 * Note:
 * <p>
 * The relative order inside both the even and odd groups should remain as it was in the input.
 * The first node is considered odd, the second node even and so on ...
 * 20190522
 */
public class OddEvenLinkedList {
    /**
     * 我的代码，
     * head向前走，分发到两个链表上，最后拼接起来
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy, evenDummy, oddTracker = new ListNode(-1), evenTracker = new ListNode(-1);
        dummy = oddTracker;
        evenDummy = evenTracker;
        boolean isOdd = true;
        while (head != null) {
            if (isOdd) {
                oddTracker.next = head;
                oddTracker = oddTracker.next;
            } else {
                evenTracker.next = head;
                evenTracker = evenTracker.next;
            }
            isOdd = !isOdd;
            head = head.next;
        }
        evenTracker.next = null;
        oddTracker.next = evenDummy.next;
        return dummy.next;
    }

    /**
     * 高赞代码：无需用dummy指向head，因为head的引用没有重定向
     * 用evenHead保存even的head，然后用两个tracker：odd和even交替向前走
     */
    public ListNode oddEvenList_(ListNode head) {
        if (head == null) return null;
        ListNode odd = head, even = head.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}
