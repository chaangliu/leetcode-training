package tree;

/**
 * Created by DrunkPiano on 2017/4/2.
 */

public class BalancedBinaryTree {
    //    public boolean isBalanced(TreeNode root) {
//        return helper(root) >= 0;
//    }
//
//    private int helper(TreeNode root) {
//        if (root == null) return 0;
//        //这样会把所有node都当做root遍历一遍。TimeComplexity:O(n)
//        int lh = helper(root.left);
//        int rh = helper(root.right);
//
//        if (lh == -1 || rh == -1) {
//            return -1;
//        }
//        if (Math.abs(lh - rh) >= 2) {
//            //技巧：返回-1代表某棵subtree imbalance 了
//            return -1;
//        }
//        return Math.max(helper(root.left), helper(root.right)) + 1;
//    }
    public boolean isBalanced(TreeNode root) {
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
