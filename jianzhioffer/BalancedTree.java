package jianzhioffer;

/**
 * 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
 */
public class BalancedTree {
    //approach 1 自顶向下
    //简单的想法，对[每个节点]分别计算左右子树的高度。
    //注意牛客网的CASE即便不加IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right)也能AC，但是并不对，因为平衡二叉树要求每个子树也是平衡二叉树。
    //这种方法的弊端就是自顶向上判断高度会重复走很多路。
    public boolean IsBalanced_Solution_SLOW(TreeNode root) {
        if (root == null) return true;
        return Math.abs(treeDepth(root.left) - treeDepth(root.right)) <= 1 && IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right);
    }

    private int treeDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(treeDepth(root.left), treeDepth(root.right)) + 1;
    }


    //approach 2 自底向上, post order
    public boolean IsBalanced_Solution(TreeNode root) {
        if (root == null) return true;
        return getDepth(root) != -1;
    }

    private int getDepth(TreeNode root) {
        if (root == null) return 0;
        int leftDepth = getDepth(root.left);
        if (leftDepth == -1) return -1;
        int rightDepth = getDepth(root.right);
        if (rightDepth == -1) return -1;
        return Math.abs(leftDepth - rightDepth) > 1 ? -1 : Math.max(leftDepth, rightDepth) + 1;
    }
}
