package linkedlist;

import java.util.ArrayList;

/**
 * We are given a linked list with head as the first node.  Let's number the nodes in the list: node_1, node_2, node_3, ... etc.
 * <p>
 * Each node may have a next larger value: for node_i, next_larger(node_i) is the node_j.val such that j > i, node_j.val > node_i.val, and j is the smallest possible choice.  If such a j does not exist, the next larger value is 0.
 * <p>
 * Return an array of integers answer, where answer[i] = next_larger(node_{i+1}).
 * <p>
 * Note that in the example inputs (not outputs) below, arrays such as [2,1,5] represent the serialization of a linked list with a head node value of 2, second node value of 1, and third node value of 5.
 * <p>
 * Example 1:
 * <p>
 * Input: [2,1,5]
 * Output: [5,5,0]
 * Example 2:
 * <p>
 * Input: [2,7,4,3,5]
 * Output: [7,0,5,5,0]
 * Example 3:
 * <p>
 * Input: [1,7,5,1,9,2,5,1]
 * Output: [7,9,9,9,0,5,0,0]
 * <p>
 * 20190331
 */
public class NextGreaterNodeInLinkedList {

    /**
     * 1. my approach
     * 这题感觉做过Array版本的，brute force应该会超时；但没想到没超时。。我想应该会有更高效的做法比如DP。
     */
    public int[] nextLargerNodes(ListNode head) {
        ArrayList<ListNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ListNode node = list.get(i);
            int val = node.val;
            while (node.next != null) {
                if (node.next.val > val) {
                    res[i] = node.next.val;
                    break;
                }
                node = node.next;
            }
        }
        return res;
    }
}
