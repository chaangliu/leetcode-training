package jianzhioffer;

import tree.TreeNode;

/**
 * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 * <p>
 * 参考：https://www.jianshu.com/p/2d1703364032
 */
public class HasSubTree {

//    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
//        if (root1 == null || root2 == null) return false;
//        if (root1.val == root2.val) {//错误！这样如果node1跟node2不同，那根本没递归下去就结束了，好蠢
//            return helper(root1, root2);
//        }
//        return HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
//    }

    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null)
            return false;
        return helper(root1, root2) || HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
    }

    private boolean helper(TreeNode root1, TreeNode root2) {
        if (root2 == null) {
            return true;
        }
        if (root1 == null) {
            return false;
        }
        if (root1.val != root2.val) {
            return false;
        }
        return helper(root1.left, root2.left) && helper(root1.right, root2.right);
    }


    public static void main(String args[]) {
        TreeNode p = new TreeNode(8);
        TreeNode root1 = new TreeNode(8);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(2);
        root1.right.left = new TreeNode(4);
        root1.right.right = new TreeNode(7);
        p.left = root1;
        p.right = new TreeNode(7);

        TreeNode root2 = new TreeNode(8);
        root2.left = new TreeNode(9);
        root2.right = new TreeNode(2);

        System.out.println(new HasSubTree().HasSubtree(p, root2));
        int a = 1;
    }
}
