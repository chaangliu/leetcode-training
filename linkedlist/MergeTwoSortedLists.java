package linkedlist;

/**
 * Merge two sorted linked lists and return it as a new list.
 * The new list should be made by splicing together the nodes of the first two lists.
 * //    Example:
 * //    Input: 1->2->4, 1->3->4
 * //    Output: 1->1->2->3->4->4
 * Created by DrunkPiano on 2017/3/1.
 */

public class MergeTwoSortedLists {
    /**
     * approach1: iteration
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

    /**
     * approach2: recursion
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode dummy;
        ListNode cruiser = new ListNode(-1);
        dummy = cruiser;
        helper(l1, l2, cruiser);
        return dummy.next;
    }

    private void helper(ListNode l1, ListNode l2, ListNode cruiser) {
        if (l1 == null) {
            cruiser.next = l2;
            return;
        }
        if (l2 == null) {
            cruiser.next = l1;
            return;
        }
        if (l1.val < l2.val) {
            cruiser.next = l1;
            helper(l1.next, l2, cruiser.next);
        } else {
            cruiser.next = l2;
            helper(l1, l2.next, cruiser.next);
        }
    }

    /**
     * 不借助helper和dummy node的递归
     */
    public ListNode Merge(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        //如果list1当前node的值小，那么list1.next等于合并后的list1.next和list2
        if (list1.val < list2.val) {
            list1.next = Merge(list1.next, list2);
        } else {
            list2.next = Merge(list2.next, list1);
        }
        return list1.val < list2.val ? list1 : list2;
    }

    public static void main(String args[]) {
        MergeTwoSortedLists mergeTwoSortedLists = new MergeTwoSortedLists();
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        ListNode result = mergeTwoSortedLists.mergeTwoLists(l1, l2);
        System.out.println(result.val);
        System.out.println(result.next.val);

    }
}
