package tree;

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
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isValidBST1(TreeNode root) {
        if(root ==  null) return  true;
        return recursion(root, Long.MIN_VALUE , Long.MAX_VALUE);
    }
    private boolean recursion(TreeNode root , long min , long max ){
        if (root == null ) return  true;
        if (root.val<= min || root.val >= max) return false;
        return recursion(root.left , min , root.val) && recursion(root.right , root.val , max);
    }



//    public boolean isValidBST(TreeNode root) {
//
//    }
}
