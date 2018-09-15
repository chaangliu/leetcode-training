package array;


/**
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * <p>
 * 这题意思是，数字是反过来存储在linked list里的，比如上面那个其实就是计算
 * 342
 * 465 = 807的过程。
 */

public class AddTwoNumbers {

    static class ListNode {
        ListNode next;
        int val;

        public ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        int carry = 0;
        ListNode p1 = l1, p2 = l2, fakeHead = new ListNode(-1), p3 = fakeHead;

        while (p1 != null || p2 != null) {
            if (p1 != null) {
                carry += p1.val;
                p1 = p1.next;
            }
            if (p2 != null) {
                carry += p2.val;
                p2 = p2.next;
            }

            p3.next = new ListNode(carry % 10);
            p3 = p3.next;
            carry = carry / 10;
        }
        if (carry == 1)
            p3.next = new ListNode(carry);
        return fakeHead.next;
    }

    public static void main(String args[]) {
        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();

        ListNode l1 = new ListNode(2);
        ListNode l1Next1 = new ListNode(4);
        ListNode l1Next2 = new ListNode(6);
        l1.next = l1Next1;
        l1Next1.next = l1Next2;

        ListNode l2 = new ListNode(8);
        ListNode l2Next1 = new ListNode(6);
        ListNode l2Next2 = new ListNode(4);
        l2.next = l2Next1;
        l2Next1.next = l2Next2;

        System.out.println(addTwoNumbers.addTwoNumbers(l1, l2));
        System.out.println("finish");
    }
}
