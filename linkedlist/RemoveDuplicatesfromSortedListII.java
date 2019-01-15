package linkedlist;

/**
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
 * <p>
 * Example 1:
 * <p>
 * Input: 1->2->3->3->4->4->5
 * Output: 1->2->5
 * Example 2:
 * <p>
 * Input: 1->1->1->2->3
 * Output: 2->3
 * <p>
 * 20190114
 */
public class RemoveDuplicatesfromSortedListII {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode fakeHead = new ListNode(-1);
        fakeHead.next = head;
        ListNode walker = fakeHead;
        ListNode runner = head;
        while (runner != null && runner.next != null) {
            //如果runner和下一个节点的val相同，那么找到不重复的那个为止
            if (runner.val == runner.next.val) {
                int value = runner.val;
                while (runner != null && runner.val == value) {
                    runner = runner.next;
                }//跳出循环的时候runner已经是重复节点的下一个节点了
                walker.next = runner;
            } else {
                //runner和下一个节点的val不相同
                walker = walker.next;
                runner = runner.next;
            }
        }
        return fakeHead.next;
    }
}
