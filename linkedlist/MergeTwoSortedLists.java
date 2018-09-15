package linkedlist;

/**
 * Merge two sorted linked lists and return it as a new list.
 * The new list should be made by splicing together the nodes of the first two lists.
 * Created by DrunkPiano on 2017/3/1.
 */

public class MergeTwoSortedLists {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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

    public static void main(String args[]) {
        MergeTwoSortedLists mergeTwoSortedLists = new MergeTwoSortedLists();
        ListNode fakeHead = new ListNode(0);

//        ListNode pointer = fakeHead;
        ListNode pointer = fakeHead;
        pointer.next = new ListNode(1);
        pointer = pointer.next;
        pointer.next = new ListNode(2);
        pointer = pointer.next;
        pointer.next = new ListNode(4);
        ListNode result = fakeHead.next;

        ListNode l2 = new ListNode(5);
//        ListNode result = mergeTwoSortedLists.mergeTwoLists(l1, l2);
        System.out.println(result.val);
        System.out.println(result.next.val);

    }
}
