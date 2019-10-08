package jianzhioffer;

import java.util.HashSet;
import java.util.Set;

import linkedlist.ListNode;

public class EntryNodeOfLoop {


    /**
     * 题意：找到环的入口结点。
     * 注意不是判断是否有环，而是要找入口结点。
     * 方法1. 最简单的方法，第一个重复的内存，需要O(n)空间
     * 方法2. 剑指offer上的解法， 先用快慢指针判断是否有环，然后计算环的长度n，然后再用两个指针，一个先走n步，一个从头开始走，他们相遇的结点就是entry node。
     */
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

    //快慢指针的话，只能保证交点在环中，不适用(20190126update: 环中相遇也是可以的，再让一个点从头开始就行了)；牛客上有tricky的方法，这里不列出了
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

    //20190126update，相关问题，leetcode 142, 287
    //左神讲的
//先说个定理：两个指针一个fast、一个slow同时从一个链表的头部出发
//fast一次走2步，slow一次走一步，如果该链表有环，两个指针必然在环内相遇
//此时只需要把其中的一个指针重新指向链表头部，另一个不变（还在环内），
//这次两个指针一次走一步，相遇的地方就是入口节点。
//这个定理可以自己去网上看看证明。
//    public class Solution {
//        public ListNode EntryNodeOfLoop(ListNode pHead){
//            ListNode fast = pHead;
//            ListNode slow = pHead;
//            while(fast != null && fast.next !=null){
//                fast = fast.next.next;
//                slow = slow.next;
//                if(fast == slow)
//                    break;
//            }
//            if(fast == null || fast.next == null)
//                return null;
//            fast = pHead;
//            while(fast != slow){
//                fast = fast.next;
//                slow = slow.next;
//            }
//            return fast;
//        }

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
