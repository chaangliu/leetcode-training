package jianzhioffer;

/**
 * 给定一棵二叉搜索树，请找出其中的第k小的结点。例如， （5，3，7，2，4，6，8）    中，按结点数值大小顺序第三小结点的值为4。
 */
public class KthNodeInBST {

    ///递归的helper最好是void的
//    int count = 1;
//
//    TreeNode KthNode(TreeNode pRoot, int k) {
//        if (pRoot == null || k <= 0) return null;
//        return helper(pRoot, k);
//    }
//kt
//    private TreeNode helper(TreeNode node, int k) {
//        if (count == k) return node;
//        helper(node.left, k);
//        count++;
//        helper(node.right, k);
//
//        return null;
//    }


    TreeNode res;
    int count;

    TreeNode KthNode(TreeNode pRoot, int k) {
        if (pRoot == null || k <= 0) return null;
        count = k;
        recursion(pRoot);
        return res;
    }

    private void recursion(TreeNode root) {
        if (root == null) return;
        recursion(root.left);
        if (--count == 0) {
            res = root;
            return;
        }
        recursion(root.right);
    }
}
