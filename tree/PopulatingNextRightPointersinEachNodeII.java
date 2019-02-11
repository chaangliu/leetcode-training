package tree;

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

//    public void connect(TreeLinkNode root) {
//        if (root == null) return;
//        TreeLinkNode pre = root;
//        TreeLinkNode cur;
//        while (pre.left != null) {
//            cur = pre;
//            while (cur != null) {
//                //pre.left!=null保证了cur.left不为Null
//                if (cur.left != null) cur.left.next = cur.right;
//                if (cur.right != null && cur.next != null) {
//                    cur.right.next = cur.next.left != null ? cur.next.left : cur.next.right;
//                }
//                cur = cur.next;
//            }
//            pre = pre.left;
//        }
//    }

    //这题不能简单地从PopulatingNextRightPointersinEachNode那题复制过来判断是否next.left为空，因为有个case过不去1,2,3,4,#,#,5过不去
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
