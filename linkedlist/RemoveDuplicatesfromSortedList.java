package linkedlist;

/**
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 * <p>
 * Example 1:
 * <p>
 * Input: 1->1->2
 * Output: 1->2
 * Example 2:
 * <p>
 * Input: 1->1->2->3->3
 * Output: 1->2->3
 * <p>
 * 20190114
 */
public class RemoveDuplicatesfromSortedList {
    //这题的迭代和递归都跟RemoveLinkedListElements类似

    //iteration
    public ListNode deleteDuplicates(ListNode head) {
        ListNode fakeHead = new ListNode(-1);
        fakeHead.next = head;
        ListNode walker = fakeHead;
        ListNode runner = head;
        while (runner != null && runner.next != null) {
            //如果runner和下一个节点的val相同，那么找到不重复的那个为止
            while (runner.next != null && runner.val == runner.next.val) {
                runner = runner.next;
            }
            walker.next = runner;
            walker = walker.next;
            runner = runner.next;// do not forget this
        }
        return fakeHead.next;
    }

    //recursion
    public ListNode deleteDuplicates2(ListNode head) {
        if(head == null || head.next == null)return head;
        head.next = deleteDuplicates(head.next);
        return head.val == head.next.val ? head.next : head;
    }

    public static void main(String args[]) {
        ListNode node = new ListNode(1);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        node.next = node1;
        node1.next = node2;
        new RemoveDuplicatesfromSortedList().deleteDuplicates(node);
    }
}
