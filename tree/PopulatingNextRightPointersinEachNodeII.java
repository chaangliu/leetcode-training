package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree
 * <p>
 * struct TreeLinkNode {
 * TreeLinkNode *left;
 * TreeLinkNode *right;
 * TreeLinkNode *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 * <p>
 * Initially, all next pointers are set to NULL.
 * <p>
 * Note:
 * <p>
 * You may only use constant extra space.
 * Recursive approach is fine, implicit stack space does not count as extra space for this problem.
 * Example:
 * <p>
 * Given the following binary tree,
 * <p>
 * 1
 * /  \
 * 2    3
 * / \    \
 * 4   5    7
 * After calling your function, the tree should look like:
 *         1 -> NULL
 *        / \
 *       2 -> 3 -> NULL
 *      / \    \
 * vv  4-> 5 -> 7 -> NULL
 * <p>
 * 20190211 @ PVG airport
 */

public class PopulatingNextRightPointersinEachNodeII {
    /**
     * 题意：把每个node指向它右边的node。
     * 这题和I一样，都可以用bfs，prev记录前一个就可以了。
     */
    public Node connect(Node root) {
        if (root == null) return null;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            Node prev = null;
            for (int sz = q.size() - 1; sz >= 0; sz--) {
                Node cur = q.poll();
                if (prev == null) {
                    prev = cur;
                } else {
                    prev.next = cur;
                    prev = cur;
                }
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
        }
        return root;
    }

    //下面的解法从leetcode讨论区看来的:
    public class Solution {
        public void connect(TreeLinkNode root) {
            if (root == null) return;
            TreeLinkNode dummy = new TreeLinkNode(0);
            TreeLinkNode p = dummy;
            TreeLinkNode head = root;
            while (head != null) { //if the head of the traverse layer is not null, then traverse that layer...
                TreeLinkNode node = head;
                while (node != null) {
                    if (node.left != null) {
                        p.next = node.left;
                        p = p.next;
                    }
                    if (node.right != null) {
                        p.next = node.right;
                        p = p.next;
                    }
                    node = node.next;
                }
                //after traversed to the end of current layer, move to the next layer
                head = dummy.next;
                dummy.next = null;
                p = dummy;
            }
        }
    }
}
