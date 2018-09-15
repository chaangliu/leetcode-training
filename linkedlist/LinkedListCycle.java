package linkedlist;

/**
 * Given a linked list, determine if it has a cycle in it.
 * <p>
 * Follow up:
 * Can you solve it without using extra space?
 * <p>
 * <p>
 * Created by DrunkPiano on 2017/3/26.
 */

public class LinkedListCycle {
    //快慢指针
    //    Use two pointers, walker and runner.
    //    walker moves step by step. runner moves two steps at time.
    //    if the Linked List has a cycle walker and runner will meet at some point.
    public static boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode walker = head;
        ListNode runner = head;
        while (walker.next != null && runner.next.next != null) {
            walker = walker.next;
            runner = runner.next.next;
            if (walker == runner) return true;
        }
        return false;
    }

//    public static boolean hasCycle(ListNode head) {
//        if (head == null) return false;
//        ArrayList<ListNode> list = new ArrayList<>();
//
//        while (head != null) {
//            if (list.contains(head)) {
//                return true;
//            }
//            head = head.next;
//            list.add(head);
//        }
//        return false;
//    }

    public static void main(String args[]){
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
//        ListNode n3 = new ListNode(1);
//        ListNode n4 = new ListNode(2);
//        ListNode n5 = new ListNode(3);

        n1.next = n2;
        n2.next = n1;

        System.out.println(hasCycle(n1));
    }
    
}
