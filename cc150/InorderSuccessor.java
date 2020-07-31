package cc150;

import tree.TreeNode;

/**
 * 设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
 *
 * 如果指定节点没有对应的“下一个”节点，则返回null。
 *
 * 示例 1:
 *
 * 输入: root = [2,1,3], p = 1
 *
 *   2
 *  / \
 * 1   3
 *
 * 输出: 2
 * 示例 2:
 *
 * 输入: root = [5,3,6,2,4,null,null,1], p = 6
 *
 *       5
 *      / \
 *     3   6
 *    / \
 *   2   4
 *  /
 * 1
 *
 * 输出: null
 * 20200731
 */
public class InorderSuccessor {
    /**
     * 题意：找出bst的下一个节点。
     * 首先，是bst，所以一定是折半。
     * 如果不是bst，那不好直接one pass地找的，最好是申请一个额外空间遍历两遍。
     * Time: O(log n)
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) return null;
        if (p.val >= root.val) { // 后继者在右子树中，向右找
            return inorderSuccessor(root.right, p);
        } else {
            TreeNode next = inorderSuccessor(root.left, p);
            return next == null ? root : next; // 如果左边没找到，那后继者一定是root。
        }
    }
}
