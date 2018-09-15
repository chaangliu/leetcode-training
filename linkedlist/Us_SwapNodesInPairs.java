package linkedlist;

/**
 * Created by DrunkPiano on 13/06/2017.
 */

public class Us_SwapNodesInPairs {
	public ListNode swapPairs(ListNode head) {
		ListNode fakeHead = new ListNode(-1);
		fakeHead.next = head;

		//fakeHead的必要性在于：
		//如果只是把head的地址保存下来，最后return head，这时候head已经改变了引用地址，所以你不能用一个新的node指向head，
		//但我奇怪的是，fakeHead.next指向的引用变了，不是跟return head一样吗？不是的
		//fakeHead.next一旦指向了head，那么即便head再指向其他地址，fakeHead.next指向的地址是不会变的。
		//
		while (head != null && head.next != null) {
			ListNode nextStart = head.next.next;
			head.next.next = head;
			head.next = nextStart;
			head = nextStart;
		}
		return fakeHead.next;
	}

	public static void main(String args[]) {
		ListNode head = new ListNode(1);
//		ListNode fakeHead = new ListNode(-1);
//		ListNode ano = new ListNode(2);
//		fakeHead.next = head;
//		head = ano;

		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		new Us_SwapNodesInPairs().swapPairs(head);
//		Us_SwapNodesInPairs a = new Us_SwapNodesInPairs();
//		System.out.print(a);
//		Us_SwapNodesInPairs b = new Us_SwapNodesInPairs();
//		System.out.print(b);
//		a = b;
//		System.out.print(a);
//		Us_SwapNodesInPairs c = new Us_SwapNodesInPairs();
//		b = c;
//		System.out.print(a);


	}
}
