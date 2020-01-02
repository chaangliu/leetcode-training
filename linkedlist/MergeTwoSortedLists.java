package linkedlist;

/**
 * Merge two sorted linked lists and return it as a new list.
 * The new list should be made by splicing together the nodes of the first two lists.
 * //    Example:
 * //    Input: 1->2->4, 1->3->4
 * //    Output: 1->1->2->3->4->4
 * Created by DrunkPiano on 2017/3/1.
 * 20200102 --review
 */

public class MergeTwoSortedLists {

    /**
     * 题意：合并两个sorted list
     * 这题iterative很容易，recursion写起来略有思维难度，不过画个图很容易理解
     * approach1: recursion
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l2.next, l1);
            return l2;
        }
    }

    /**
     * approach2: iteration
     */
    public ListNode mergeTwoLists__ITERAION(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode fakeHead = new ListNode(0);
        //pointer是fakeHead对象的引用，以后的操作都在fakehead上
        //pointer去跑，找不到回家的路的路，fakehead在原地等他
        ListNode pointer = fakeHead;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                pointer.next = l1;
                l1 = l1.next;
            } else {
                pointer.next = l2;
                l2 = l2.next;
            }
            pointer = pointer.next;
        }

        if (l1 != null) {
            pointer.next = l1;
        } else {
            pointer.next = l2;
        }
        return fakeHead.next;
    }

}
