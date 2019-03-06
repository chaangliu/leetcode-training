package jianzhioffer;

import linkedlist.ListNode;

/**
 * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 */
public class MergeTwoSortedList {

    /**
     * leetcode原题，去东京的飞机上写过
     * <p>
     * 一次通过了，注意内存指向的问题。tracer不停在走，res始终在起始位置，跟tracer一开始一样指向node.val == -1那块内存。
     */
//    public ListNode Merge(ListNode list1, ListNode list2) {
//        ListNode tracer = new ListNode(-1), res = tracer;
//        while (list1 != null && list2 != null) {
//            if (list1.val > list2.val) {
//                tracer.next = list2;
//                list2 = list2.next;
//            } else {
//                tracer.next = list1;
//                list1 = list1.next;
//            }
//            tracer = tracer.next;
//        }
//        if (list1 != null) tracer.next = list1;
//        if (list2 != null) tracer.next = list2;
//        return res.next;
//    }

    /**
     * 另：Recursion解法。
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

    /**
     * recursion 20190306
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
}
