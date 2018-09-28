package linkedlist;

/**
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 * <p>
 * <p>
 * For example, the following two linked lists:
 * <p>
 * A:          a1 → a2
 * ↘
 * c1 → c2 → c3
 * ↗
 * B:     b1 → b2 → b3
 * begin to intersect at node c1.
 * <p>
 * <p>
 * Notes:
 * <p>
 * If the two linked lists have no intersection at all, return null.
 * The linked lists must retain their original structure after the function returns.
 * You may assume there are no cycles anywhere in the entire linked structure.
 * Your code should preferably run in O(n) time and use only O(1) memory.
 */


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 剑指offer原题。先搞清楚，两个节点相等（也就是内存地址相同）就代表这是同一个节点，也就是intersection point。
 */
public class IntersectionOfTwoLinkedLists {
    //approach 1, brute force, 对于a中的每个节点都去b中比较，时间O(m*n)。

    //approach 2, hashmap。key是内存地址，value是任意值。存储第一个map，然后在第二个map里的每个节点都去map里找。时间O(m*n)空间O(m)或者O(n)
//    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
//        Map<ListNode, Boolean> map = new HashMap<>();
//        ListNode fakeA = headA;
//        ListNode fakeB = headB;
//        while (fakeA != null) {
//            map.put(fakeA, true);
//            fakeA = fakeA.next;
//        }
//
//        while (fakeB != null) {
//            if (map.containsKey(fakeB)) return fakeB;
//            fakeB = fakeB.next;
//        }
//        return null;
//    }

    //approach 3, trick。
    //   2 4 6
    // 1 3 4 6
    // 2 4 6 | 1 3 4 6
    // 1 3 4 6 | 2 4 6
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode fakeA = headA;
        ListNode fakeB = headB;
        int countA = 0, countB = 0;
        while (fakeA != null) {
            fakeA = fakeA.next;
            countA++;
        }
        fakeA = headB;
        while (fakeB != null) {
            fakeB = fakeB.next;
            countB++;
        }
        fakeB = headA;
        if (countA > countB) {
            for (int i = 0; i < countA - countB; i++) {
                fakeB = fakeB.next;
            }
        } else {
            for (int i = 0; i < countB - countA; i++) {
                fakeA = fakeA.next;
            }
        }
        while (fakeA != null && fakeB != null) {
            if (fakeA == fakeB) return fakeA;
            fakeA = fakeA.next;
            fakeB = fakeB.next;
        }
        return null;
    }

    //第四种方式，剑指offer上的。先遍历两个链表的长度，然后让长的那个先走两者长度差。然后一起走，开始对比。
    //Here's my solution which is like approach 3 but a little different. I store the size of ListA and ListB as len1 and len2. Then I reset the pointers to headA and headB and find the difference between len1 and len2, and then let the pointer of the longer list proceed by the difference between len1 and len2. Finally, traverse through the lists again, the intersection node can be easily found.

    public static void main(String args[]) {
        //关于指针
        //这么做，node的值会变
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
//
//        ListNode fake = node;
//        fake.val = 100;
//
//        System.out.println();
//        ListNode node3 = new ListNode(2);//test case不能这么写！！因为node3跟node.next内存地址不同了
        ListNode node3 = node.next;

        ListNode res = new IntersectionOfTwoLinkedLists().getIntersectionNode(node, node3);
        if (res != null) {
            System.out.println(new IntersectionOfTwoLinkedLists().getIntersectionNode(node, node3).val);
        }
    }
}
