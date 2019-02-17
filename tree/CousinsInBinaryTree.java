package tree;

/**
 * In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.
 * <p>
 * Two nodes of a binary tree are cousins if they have the same depth, but have different parents.
 * <p>
 * We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.
 * <p>
 * Return true if and only if the nodes corresponding to the values x and y are cousins.
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,2,3,4], x = 4, y = 3
 * Output: false
 * Example 2:
 * <p>
 * <p>
 * Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
 * Output: true
 * Example 3:
 * <p>
 * Input: root = [1,2,3,null,4], x = 2, y = 3
 * Output: false
 * <p>
 * 20190217weekly contest
 */
public class CousinsInBinaryTree {

    /**
     * 我的解法
     * 先找其中一个，找到之后找另外一个，看高度是否一致
     */
    boolean res = false;
    int depth1 = -1;

    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) {
            return false;
        }
        helper(root, x, y, 0);
        return res;
    }

    private void helper(TreeNode root, int x, int y, int depth) {
        if (root.val == x || root.val == y) {
            if (depth1 != -1) {
                res = depth1 == depth;
                return;
            } else {
                depth1 = depth;
            }
        }
        if (root.left != null) {
            if (root.right != null && ((root.left.val == x && root.right.val == y) || (root.left.val == y && root.right.val == x))) {
                res = false;
                return;
            }
            helper(root.left, x, y, depth + 1);
        }
        if (root.right != null) {
            if (root.left != null && ((root.left.val == x && root.right.val == y) || (root.left.val == y && root.right.val == x))) {
                res = false;
                return;
            }
            helper(root.right, x, y, depth + 1);
        }
    }
}
