package linkedlist;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 * You may not alter the values in the nodes, only nodes itself may be changed.
 * Only constant memory is allowed.
 * <p>
 * For example,
 * Given this linked list: 1->2->3->4->5
 * For k = 2, you should return: 2->1->4->3->5
 * For k = 3, you should return: 3->2->1->4->5
 * <p>
 * <p>
 * Created by DrunkPiano on 2017/3/26.
 */

class ReverseNodesInKGroup {

    /**
     * 题意：把node按照k个一组完成倒序。
     * 解法1，简洁解法, 遍历两次，
     * 做法和普通的reverse linked list不同，不会中途断开list，相当于每次把一个新的node移到前面完成倒序，写起来不那么容易
     * 12345, k = 3
     * 012345
     * se
     * 021345
     * s_e
     * 032145
     * s__e
     * 032145
     * ___se
     * ..
     **/
    public ListNode reverseKGroup(ListNode head, int k) {
        int cnt = 0;
        for (ListNode node = head; node != null; node = node.next, cnt++) ;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        for (ListNode s = dummy, e = head; cnt - k >= 0; cnt -= k) {
            int n = k;
            while (n-- > 1) {
                ListNode tmp = e.next.next;
                e.next.next = s.next;//213=>321，把e后面的那个node指向开头，e.next就是即将加入倒序的node
                s.next = e.next;//改变s的指向为新的开头，这次操作后s到e完成倒序
                e.next = tmp;
            }
            s = e;
            e = e.next;
        }
        return dummy.next;
    }


    /**
     * 解法2，只需遍历一次；双指针维护begin和end，每走k步之后就进行普通的单链表翻转，比较清晰。
     * https://leetcode.com/problems/reverse-nodes-in-k-group/discuss/11440/Non-recursive-Java-solution-and-idea
     */
    public ListNode reverseKGroup__(ListNode head, int k) {
        ListNode begin;
        if (head == null || head.next == null || k == 1)
            return head;
        ListNode dummyhead = new ListNode(-1);
        dummyhead.next = head;
        begin = dummyhead;
        int i = 0;
        while (head != null) {
            i++;
            if (i % k == 0) {
                begin = reverse(begin, head.next);
                head = begin.next;
            } else {
                head = head.next;
            }
        }
        return dummyhead.next;

    }

    private ListNode reverse(ListNode begin, ListNode end) {
        ListNode curr = begin.next;
        ListNode next, first;
        ListNode prev = begin;
        first = curr;
        while (curr != end) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        begin.next = prev;
        first.next = curr;
        return first;
    }

    public static void main(String args[]) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);
        new ReverseNodesInKGroup().reverseKGroup(node, 4);
    }
}
