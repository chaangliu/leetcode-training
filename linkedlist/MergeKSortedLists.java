package linkedlist;

import java.util.Comparator;
import java.util.PriorityQueue;

import linkedlist.ListNode;

/**
 * Created by DrunkPiano on 2017/3/27.
 */

public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                //none - descending sort
                return o1.val - o2.val;
            }
        });

        for (ListNode list : lists) {
            if (list != null)
                queue.offer(list);
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


	public static void main(String[] args) {
		PriorityQueue<String> pq = new PriorityQueue<String>();
		pq.add("dog");
		pq.add("apple");
		pq.add("fox");
		pq.add("easy");
		pq.add("boy");

		while (!pq.isEmpty()) {
			System.out.print("left:");
			for (String s : pq) {
				System.out.print(s + " ");
			}
			System.out.println();
			System.out.println("poll(): " + pq.poll());
		}
	}

}
