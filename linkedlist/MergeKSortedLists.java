package linkedlist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import linkedlist.ListNode;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 * <p>
 * Example:
 * <p>
 * Input:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 * Created by DrunkPiano on 2017/3/27.
 * 相似题目：Find K Pairs With Smallest Sums
 */

public class MergeKSortedLists {
    /**
     * 题意：给你k个sorted listNode，让你排序，输出一个排序后的listNode。
     * 解法1：priority queue，因为queue中只有k个node，所以这个解法的复杂度是O(n * log k)；而如果把node都撸下来再排序，速度是O(n*logn)，不够好
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        for (ListNode list : lists) {
            if (list != null) queue.offer(list);
        }
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        while (!queue.isEmpty()) {
            tail.next = queue.poll();
            tail = tail.next;
            //把每个链表上的node全撸下来加入到queue,注意这个撸下来的过程可能不是连续的，可能先撸了第一个链表的next就去撸下一个链表了
            if (tail.next != null) {
                queue.offer(tail.next);
            }
        }
        return dummy.next;
    }

    /**
     * 解法2：divide and conquer
     * 利用merge Two Sorted Lists的解法
     * 写了个test case，执行顺序跟merge sort一样。
     * merging == > 0  1
     * merging == > 2  3
     * merging == > 0  2
     * merging == > 4  5
     * merging == > 6  7
     * merging == > 4  6
     * merging == > 0  4
     */
    public static ListNode mergeKLists_DivideAndConquer(ListNode[] lists) {
        return partion(lists, 0, lists.length - 1);
    }

    public static ListNode partion(ListNode[] lists, int s, int e) {
        if (s == e) return lists[s];
        if (s < e) {
            int mid = (s + e) / 2;
            ListNode l1 = partion(lists, s, mid);
            ListNode l2 = partion(lists, mid + 1, e);
            System.out.println("merging == > " + l1.val + "  " + l2.val);
            return merge(l1, l2);
        } else
            return null;
    }

    //This function is from Merge Two Sorted Lists.
    public static ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        } else {
            l2.next = merge(l1, l2.next);
            return l2;
        }
    }

    /**
     * 把node都撸下来，不是好的解法，速度是O(n * logn)
     * 20200103 review
     */
    public ListNode mergeKLists_(ListNode[] lists) {
        List<ListNode> list = new ArrayList<>();
        for (ListNode node : lists) {
            while (node != null) {
                list.add(node);
                node = node.next;
            }
        }
        Collections.sort(list, (a, b) -> a.val - b.val);
        ListNode dummy = new ListNode(-1);
        ListNode head = dummy;
        for (ListNode node : list) {
            head.next = node;
            head = head.next;
        }
        return dummy.next;
    }

    public static void main(String args[]) {
        mergeKLists_DivideAndConquer(new ListNode[]{new ListNode(0), new ListNode(1), new ListNode(2), new ListNode(3), new ListNode(4), new ListNode(5), new ListNode(6), new ListNode(7)});
    }
}
