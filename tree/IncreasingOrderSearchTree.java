package tree;

/**
 * Given a binary search tree, rearrange the tree in in-order so that the leftmost node in the tree is now the root of the tree, and every node has no left child and only 1 right child.
 * Example 1:
 * Input: [5,3,6,2,4,null,8,1,null,null,null,7,9]
 *
 *        5
 *       / \
 *     3    6
 *    / \    \
 *   2   4    8
 *  /        / \
 * 1        7   9
 *
 * Output: [1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
 *
 *  1
 *   \
 *    2
 *     \
 *      3
 *       \
 *        4
 *         \
 *          5
 *           \
 *            6
 *             \
 *              7
 *               \
 *                8
 *                 \
 *                  9
 *
 *
 * Constraints:
 *
 * The number of nodes in the given tree will be between 1 and 100.
 * Each node will have a unique integer value from 0 to 1000.
 * 20200509
 */
public class IncreasingOrderSearchTree {
    /**
     * 题意：给你一颗BST，让你把它flatten成一根向右的棍子。
     * 解法：就是普通dfs，但是这题的非常容易犯的错误是，在flatten过程中忘记把left child置空。这也是很多递归链表、tree题目常见的错误。
     */
    TreeNode dummy = new TreeNode(0), res = dummy;

    public TreeNode increasingBST(TreeNode root) {
        if (root == null) return null;
        increasingBST(root.left);
        add(root);
        increasingBST(root.right);
        return res.right;
    }

    private void add(TreeNode node) {
        node.left = null; // 容易漏掉！
        dummy.right = node;
        dummy = dummy.right;
    }

    /**
     * lee的解法，很难理解。。
     */
    public TreeNode increasingBST_lee(TreeNode root) {
        return increasingBST(root, null);
    }

    public TreeNode increasingBST(TreeNode root, TreeNode tail) {
        if (root == null) return tail;
        TreeNode res = increasingBST(root.left, root);
        root.left = null;
        root.right = increasingBST(root.right, tail);
        return res;
    }
}
