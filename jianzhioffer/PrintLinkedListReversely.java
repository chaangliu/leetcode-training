package jianzhioffer;

import java.util.ArrayList;
import java.util.List;

import linkedlist.ListNode;

/**
 * 6. 从尾到头打印链表
 */
public class PrintLinkedListReversely {

    //    Approach 1. 逆序链表。代码略(https://www.jianshu.com/p/61d5ea0171ae)
    //    Approach 2. 用栈，空间O(n)。代码略

    //    Approach 3. 递归，每次先打印后一个节点的值，缺点是可能stack overflow
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> res = new ArrayList<>();
        dfsHelper(res, listNode);
        return res;
    }

    private void dfsHelper(List<Integer> list, ListNode node) {
        if (node != null) {
            dfsHelper(list, node.next);
            list.add(node.val);
        }
    }

    public static void main(String args[]) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);

        List list = new PrintLinkedListReversely().printListFromTailToHead(node);
        System.out.print(list);
    }

}
