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

//        if (left.val == right.val) return true;
        if (left.val != right.val) return false;
        return recursion(left.right, right.left) && recursion(left.left, right.right);
    }


    /**
     * //20190207 REVIEW
     * //方法1. BFS，观察到序列化的树的序列想到的
     * //方法2. BOTTOM-UP递归，这个问题可以转换为，根节点的左右两个child是不是inverse过来就相同结构了；画图想到的
     */
    public boolean isSymmetric__20190207(TreeNode root) {
        if (root == null) return true;
        // if(root.left == null) return root.right == null;
        // if(root.right == null) return false;
        return helper(root.left, root.right);
    }

    private boolean helper(TreeNode left, TreeNode right) {
        if (left == null) return right == null;
        if (right == null) return false;
        if (left.val != right.val) return false;
        return helper(left.right, right.left) && helper(left.left, right.right);//已犯错误1. 漏掉&&右边的条件
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
