package linkedlist;

/**
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 * <p>
 * You should preserve the original relative order of the nodes in each of the two partitions.
 * <p>
 * Example:
 * <p>
 * Input: head = 1->4->3->2->5->2, x = 3
 * Output: 1->2->2->4->3->5
 * <p>
 * 20190115
 */
public class PartitionList {

    ///StraitForward；
    //这句有点难理解：You should preserve the original relative order of the nodes in each of the two partitions.
    //意思是只需要保证两个partition的顺序正确就行，不需要pivot放在正确位置；这一点跟quicksort不一样
    public ListNode partition(ListNode head, int x) {
        ListNode before = new ListNode(-1);
        ListNode pre1 = before;
        ListNode after = new ListNode(-1);
        ListNode pre2 = after;

        while (head != null) {
            if (head.val < x) {
                before.next = head;
                before = before.next;
            } else {
                after.next = head;
                after = after.next;
            }
            head = head.next;
        }
        after.next = null;//已犯错误，这一句没有加，导致链表比以前长；LeetCode会提示MLE，我还以为是我用了太多辅助结点，其实是因为没收尾
        //conjunction
        before.next = pre2.next;
        return pre1.next;
    }
}
