package linkedlist;

/**
 * Created by DrunkPiano on 04/07/2017.
 */

public class SortList {
	//用快慢指针来找到中间点
	public ListNode sortList(ListNode head) {

		if (head == null || head.next == null) {
			return head;
		}
		ListNode prev = new ListNode(-1);
		ListNode slow = head;
		ListNode fast = head;
		while (fast != null && fast.next != null) {
			prev = slow;
			slow = slow.next;
			fast = fast.next.next;
		}
		//prev把slow前面那个node跟slow之间的link断开
		prev.next = null;
		ListNode l1 = sortList(head);
		ListNode l2 = sortList(slow);
		return merge(l1, l2);
	}

	private ListNode merge(ListNode l1, ListNode l2) {
		ListNode node = new ListNode(-1);
		ListNode l = node;
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				l.next = l1;
				l1 = l1.next;
			} else {
				l.next = l2;
				l2 = l2.next;
			}
			l = l.next;
		}
		if (l1 != null) {
			l.next = l1;
		}
		if (l2 != null) {
			l.next = l2;
		}
		return node.next;
	}

}
