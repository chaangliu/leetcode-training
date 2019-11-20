package linkedlist;


/**
 * Reverse a singly linked list(单链表).
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 * Created by DrunkPiano on 2017/3/23.
 */

class ReverseLinkedList {

    /**
     * approach1. iteration
     */
    public ListNode reverseList1(ListNode head) {
        if (head == null) return null;
        //dummy node 头插法。这题不需要三个node;只需要prev和cur
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    /**
     * approach2. recursion
     * 两个注意的点
     * 1. 只在最后返回了p，中途没有对p进行操作
     * 2. 打印p.val，打印了n - 1次head的值(取最后一次)
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        System.out.println(p.val);
        return p;
    }


    /**
     * recursion我的写法
     */
    ListNode res = null;

    public ListNode reverseList___REDUNDANT(ListNode head) {
        ListNode tmp;
        if (head != null && head.next != null) {
            tmp = head.next.next;
            head.next.next = head;
            if (tmp != null) {
                reverseList___REDUNDANT(tmp);
                tmp.next = head.next;
                head.next = null;
            } else {
                res = head.next;
                head.next = null;
            }
        } else {
            res = head;
        }
        return res;
    }


    /**
     * test-----------------------------------------------------------------------------------------------------------------------
     */
    public static void main(String[] args) {

        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n1 = reverseList(n1);
        printList(n1);
    }

    public static void printList(ListNode x) {
        if (x != null) {
            System.out.print(x.val + " ");
            while (x.next != null) {
                System.out.print(x.next.val + " ");
                x = x.next;
            }
            System.out.println();
        }
    }
}
