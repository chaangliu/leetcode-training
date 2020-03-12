package tree;

import linkedlist.ListNode;

/**
 * Given a binary tree root and a linked list with head as the first node.
 * <p>
 * Return True if all the elements in the linked list starting from the head correspond to some downward path connected in the binary tree otherwise return False.
 * <p>
 * In this context downward path means a path that starts at some node and goes downwards.
 * Example 1:
 * Input: head = [4,2,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
 * Output: true
 * Explanation: Nodes in blue form a subpath in the binary Tree.
 * Example 2:
 * Input: head = [1,4,2,6], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
 * Output: true
 * Example 3:
 * <p>
 * Input: head = [1,4,2,6,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
 * Output: false
 * Explanation: There is no path in the binary tree that contains all the elements of the linked list from head.
 * Constraints:
 * 1 <= node.val <= 100 for each node in the linked list and binary tree.
 * The given linked list will contain between 1 and 100 nodes.
 * The given binary tree will contain between 1 and 2500 nodes.
 * 20200301
 */
public class LinkedListinBinaryTree {
    /**
     * 题意：在从上到下的二叉树中找一个链表。
     * 解法：dfs，但是我写的时候有个case一直过不了，后来发现，原来是我的写法有漏洞， 允许了非连续的list node的存在。
     * 所以这题需要把val相同和不相同做不同处理。
     */
    public boolean isSubPath(ListNode head, TreeNode root) {
        if (head == null) return true;
        if (root == null) return false;
        return dfs(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    private boolean dfs(ListNode head, TreeNode root) {
        if (head == null) return true;
        if (root == null) return false;
        return head.val == root.val && (dfs(head.next, root.left) || dfs(head.next, root.right));
    }

    /**
     * WA做法，当前node匹配之后，下一轮允许了ListNode中断
     */
    public boolean isSubPath__(ListNode head, TreeNode root) {
        if (head == null) return true;
        if (root == null) return false;
        if (root.val == head.val) {
            return isSubPath(head.next, root.left) || isSubPath(head.next, root.right);
        }
        return isSubPath(head, root.left) || isSubPath(head, root.right);
    }
}
