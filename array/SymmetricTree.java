package array;

import tree.TreeNode;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 * <p>
 * Created by DrunkPiano on 2017/3/29.
 */

class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return recursion(root.left, root.right);
    }

    private boolean recursion(TreeNode left, TreeNode right) {
        if (left == null || right == null) return left == right;

        //
        if (left.val == right.val) return true;
        return recursion(left.right, right.left) && recursion(left.left, right.right);
    }

    public static void main(String args[]) {
        TreeNode root = new TreeNode(1);
        TreeNode root1 = new TreeNode(2);
        TreeNode root2 = new TreeNode(2);
        TreeNode root3 = new TreeNode(3);
        TreeNode root4 = new TreeNode(3);
        root.left = root1;
        root.right = root2;
        root1.right = root3;
        root2.right = root4;

        SymmetricTree symmetricTree = new SymmetricTree();
        System.out.println(symmetricTree.isSymmetric(root));
        System.out.println(symmetricTree.isSymmetric(root));
    }
}
