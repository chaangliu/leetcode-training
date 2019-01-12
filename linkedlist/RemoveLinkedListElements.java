package linkedlist;

/**
 * Remove all elements from a linked list of integers that have value val.
 * <p>
 * Example:
 * <p>
 * Input:  1->2->6->3->4->5->6, val = 6
 * Output: 1->2->3->4->5
 * <p>
 * 20190112
 */
public class RemoveLinkedListElements {

    //best solution : recursion :
    public ListNode removeElementsR(ListNode head, int val) {
        if (head == null) return null;
        head.next = removeElementsR(head.next, val);
        return head.val == val ? head.next : head;
    }

    //iteration :
    //这题需要按照思路写出来，五分钟内。适当定义pre + cur或者cur + next辅助节点
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;
        ListNode fakeHead = new ListNode(-1);
        fakeHead.next = head;
        ListNode curNode = fakeHead;
        ListNode nextNode;
        while (curNode != null) {
            nextNode = curNode.next;
            while (nextNode != null && nextNode.val == val) {
                nextNode = nextNode.next;
            }
            curNode.next = nextNode;
            curNode = curNode.next;
        }
        return fakeHead.next;
    }

    public static void main(String args[]) {
        ListNode node = new ListNode(1);
        new RemoveLinkedListElements().removeElements(node, 1);
    }
}
