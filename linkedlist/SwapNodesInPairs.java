package linkedlist;


/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 * For example,
 * <p>
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


    /**
     * approach 2. recursion
     * 20190304
     */
    //    First, we swap the first two nodes in the list, i.e. head and head.next;
    //    Then, we call the function self as swap(head.next.next) to swap the rest of the list following the first two nodes.
    //    Finally, we attach the returned head of the sub-list in step (2) with the two nodes swapped in step (1) to form a new linked list.
    //    1234 2143
    public linkedlist.ListNode swapPairs(linkedlist.ListNode head) {
        if (head != null && head.next != null) {
            linkedlist.ListNode n = head.next;
            linkedlist.ListNode tmp = head.next.next;
            head.next.next = head;
            head.next = swapPairs(tmp);
            return n;
        }
        return head;
    }
}
