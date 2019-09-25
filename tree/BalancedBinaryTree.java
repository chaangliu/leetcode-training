package tree;

/**
 * Given a binary tree, determine if it is height-balanced.
 *
 * For this problem, a height-balanced binary tree is defined as:
 *
 * a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 *
 * Example 1:
 *
 * Given the following tree [3,9,20,null,null,15,7]:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * Return true.
 *
 * Example 2:
 *
 * Given the following tree [1,2,2,3,3,null,null,4,4]:
 *
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * Return false.
 * Created by DrunkPiano on 2017/4/2.
 */

public class BalancedBinaryTree {
    /**
     * bottom up, TimeComplexity:O(n)
     *
     * 这题是剑指offer原题。bottom up效率高， top down有很多重复子问题。
     */
    public boolean isBalanced(TreeNode root) {
        return depth(root) != -1;
    }

    private int depth(TreeNode root) {
        if (root == null) return 0;
        int lh = depth(root.left);
        if(lh == -1) return  -1;
        int rh = depth(root.right);
        if (rh == -1) return -1;
        return Math.abs(lh - rh) > 1 ? -1 : Math.max(lh, rh) + 1;
    }


    /**
     * top down, slow
     */
    public boolean isBalanced_SLOW(TreeNode root) {
        if (root == null) return true;
        int lh = height(root.left);
        int rh = height(root.right);
        return Math.abs(lh - rh) <= 1 && isBalanced(root.left) && isBalanced(root.right);

    }

    private int height(TreeNode root) {
        if (root == null) return 0;
        return Math.max(height(root.left), height(root.right)) + 1;
    }

}
