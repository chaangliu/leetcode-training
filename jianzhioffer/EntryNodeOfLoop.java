package jianzhioffer;

import java.util.HashSet;
import java.util.Set;

import linkedlist.ListNode;

public class EntryNodeOfLoop {


    //最简单的方法，第一个重复的内存，需要O(n)空间
    public ListNode EntryNodeOfLoop2(ListNode pHead) {
        ListNode node = pHead;
        Set<ListNode> set = new HashSet<>();
        while (node != null) {
            if (!set.contains(node)) {
                set.add(node);
            } else {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    //快慢指针的话，只能保证交点在环中，不适用；牛客上有tricky的方法，这里不列出了
//    public ListNode EntryNodeOfLoop(ListNode pHead) {
//        ListNode slow = pHead;
//        ListNode fast = pHead;
//        while (slow != null && fast != null) {
//            slow = slow.next;
//            if (slow != null)
//                fast = slow.next;
//            if (slow == fast) {
//                return slow;
//            }
//        }
//        return slow;
//    }

    public static void main(String args[]) {
        //1 - 2 - 3 - 4 - 2
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;
//        new EntryNodeOfLoop().EntryNodeOfLoop(node1);
    }
}
