package tree;

import java.util.Stack;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * <p>
 * Assume a BST is defined as follows:
 * <p>
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * Example 1:
 * 2
 * / \
 * 1   3
 * Binary tree [2,1,3], return true.
 * Example 2:
 * 1
 * / \
 * 2   3
 * Binary tree [1,2,3], return false.
 * <p>
 * <p>
 * Created by DrunkPiano on 2017/3/24.
 */

class ValidateBinarySearchTree {

    /**
     * 题意：判断binary tree是不是BST。
     * 注意不能只判断当前node的左右两个节点，而要top down地夹逼，左边所有children的val都在[min, root.val]之间这样。
     * 解法：递归和迭代。
     */
    public boolean isValidBST1(TreeNode root) {
        if (root == null) return true;
        return recursion(root, Long.MIN_VALUE, Long.MAX_VALUE); // 用long因为case里有个Integer.MAX_VALUE
    }

    private boolean recursion(TreeNode root, long min, long max) {
        if (root == null) return true;
        if (root.val <= min || root.val >= max) return false; // BST不能有[1,1]这样的相同节点
        return recursion(root.left, min, root.val) && recursion(root.right, root.val, max);
    }


    /**
     * iterative写法
     * https://leetcode.com/problems/validate-binary-search-tree/discuss/32112/Learn-one-iterative-inorder-traversal-apply-it-to-multiple-tree-questions-(Java-Solution)
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || !stack.empty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (pre != null && pre.val >= root.val) return false;
            pre = root;
            root = root.right;
        }
        return true;
    }
}
