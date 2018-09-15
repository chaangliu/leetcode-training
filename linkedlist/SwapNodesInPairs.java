package linkedlist;


/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 * For example,
 *
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 * Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
 * Created by DrunkPiano on 2017/3/4.
 */

public class SwapNodesInPairs {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null) return null;
        //因为要return its head，所以把head保存下来
        ListNode fakeHead = new ListNode(-1);
        ListNode pre = fakeHead;
        fakeHead.next = head;
        ListNode curS = head;
        while (curS != null && curS.next != null) {
            ListNode nextS = curS.next.next;
            pre.next = curS.next;
            curS.next.next = curS;
            curS.next = nextS;

            pre = curS;
            //这里亦可以写成：curS = nextS; 因为虽然nextS指向的是curS的引用，但是curS对象迄今为止并没有被改变过
            curS = curS.next;
        }
        return fakeHead.next;
    }
}
