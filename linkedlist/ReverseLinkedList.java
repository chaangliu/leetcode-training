package linkedlist;


/**
 * Reverse a singly linked list(单链表).
 * <p>
 * <p>
 * Created by DrunkPiano on 2017/3/23.
 */

class ReverseLinkedList {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode reverseList1(ListNode head) {
        if (head == null) return null;
        //dummy node 头插法
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

        My thought: 在if语句的return之前, head会指向最后一个结点(p->next == null了),所以p会指向最后一个节点；然后就返回到上一层递归，这时候head已经不是最后一个节点了，而是倒数第二个节点。然后把p指向head。head.next=null，不然会产生环。再返回逆序后的首节点p。至于为什么每次都返回p，想不清了，暂时就只要想因为我们最终就需要返回p。
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }


    public static void main(String[] args) {

        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);

//        ListNode n4 = new ListNode(3);
//        ListNode n5 = new ListNode(4);
//        ListNode n6 = new ListNode(5);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
//        n3.next = n4;
//        n4.next = n5;
//        n5.next = n6;

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
