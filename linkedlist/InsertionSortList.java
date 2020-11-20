package linkedlist;

/**
 * /**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 * https://en.wikipedia.org/wiki/Insertion_sort
 * Created by DrunkPiano on 2017/3/22.
 */

class InsertionSortList {
    /**
     * 题意：给你一个无序的链表，请模拟插入排序让它有序。
     * 插入排序的基本思想是，维护一个有序序列，初始时有序序列只有一个元素，每次将一个新的元素插入到有序序列中，将有序序列的长度增加1，直到全部元素都加入到有序序列中。
     * 思路：模拟。
     * 朴素方法，维护待插入node(cur), 每次从头遍历插入位置
     **/
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        ListNode cur = head;
        while (cur != null) {
            ListNode tmp = cur.next;
            while (prev.next != null && prev.next.val < cur.val) prev = prev.next; // 技巧，判断prev.next与cur的大小关系
            cur.next = prev.next;
            prev.next = cur;
            cur = tmp;
            prev = dummy;
        }
        return dummy.next;
    }

    /**
     * 加个tail提速
     * 维护两个node，一个是当前待插入的，一个是已经排序的最后一个node（这个是关键，可以减少遍历次数）。
     */
    public ListNode insertionSortList_TAIL(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode lastSorted = head, curr = head.next;
        while (curr != null) {
            if (lastSorted.val <= curr.val) {
                lastSorted = lastSorted.next;
            } else {
                ListNode prev = dummyHead;
                while (prev.next.val <= curr.val) {
                    prev = prev.next;
                }
                lastSorted.next = curr.next;
                curr.next = prev.next;
                prev.next = curr;
            }
            curr = lastSorted.next;
        }
        return dummyHead.next;
    }
}
