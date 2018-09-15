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

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) return head;

        int count = 0;
        ListNode dummy = new ListNode(-1);
        dummy.next = head ;
        //pre永远指向k pairs开始的前一位
        ListNode pre = dummy ;
        ListNode cur = head ;
        while (cur!=null) {
            ListNode next = cur.next ;
            count ++ ;
            if(count == k){
                pre = reverseList1(pre , next);
            }
            cur = next ;
        }

        return dummy.next;
    }
    //end 指向k pairs的后一位
    public ListNode reverseList1(ListNode pre , ListNode end) {
        if (pre.next == null) return null;
        //dummy node 头插法
        ListNode curr = pre.next;
        while (curr != end) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        pre.next = end;
        return pre;
    }
}
