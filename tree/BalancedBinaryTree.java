package tree;

/**
 * Given a binary tree, determine if it is height-balanced.
 * <p>
 * For this problem, a height-balanced binary tree is defined as:
 * <p>
 * a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 * <p>
 * Example 1:
 * <p>
 * Given the following tree [3,9,20,null,null,15,7]:
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * Return true.
 * <p>
 * Example 2:
 * <p>
 * Given the following tree [1,2,2,3,3,null,null,4,4]:
 * <p>
 * 1
 * / \
 * 2   2
 * / \
 * 3   3
 * / \
 * 4   4
 * Return false.
 * Created by DrunkPiano on 2017/4/2.
 */

public class BalancedBinaryTree {
    /**
     * 题意：判断是否是平衡二叉树。
     * 这题是剑指offer原题。top down写法判断条件是：左右都平衡并且左右相差不超过1.
     * 时间：O(n^2)
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return isBalanced(root.left) && isBalanced(root.right) && Math.abs(depth(root.left) - depth(root.right)) <= 1;
    }

    // 会被多次重复调用
    private int depth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(depth(root.left), depth(root.right));
    }

    /**
     * bottom up写法，因为求节点高度也会顺便遍历左右子树高度，所以只需要在求root节点的depth过程中顺便判断左右子树高度是否相差<=1即可；而且一旦遇到某子树是不平衡的就返回-1，可以节约一些遍历
     * 时间：O(n)
     */
    public boolean isBalanced_(TreeNode root) {
        return depth_(root) != -1;
    }

    private int depth_(TreeNode root) {
        if (root == null) return 0;
        int lh = depth(root.left);
        if (lh == -1) return -1;
        int rh = depth(root.right);
        if (rh == -1) return -1;
        return Math.abs(lh - rh) > 1 ? -1 : Math.max(lh, rh) + 1;
    }
}
