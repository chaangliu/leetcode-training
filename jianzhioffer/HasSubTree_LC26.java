package jianzhioffer;

import tree.TreeNode;

/**
 * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 * <p>
 * 参考：https://www.jianshu.com/p/2d1703364032
 */
public class HasSubTree_LC26 {
    /**
     * 题意：判断B是否是A的子结构。
     * 解法：DFS，但是刚开始写犯了个错，找到第一个val相同的入口，就没继续向下找了。。
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (B == null) return false;
        if (A == null) return false;
        if (A.val == B.val) {
            if (checkContain(A, B)) return true; // 这儿WA了一次
        }
        return isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    private boolean checkContain(TreeNode A, TreeNode B) {
        if (B == null) return true;
        if (A == null) return false;
        if (A.val != B.val) return false;
        return checkContain(A.left, B.left) && checkContain(A.right, B.right);
    }

    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null)
            return false;
        return helper(root1, root2) || HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
    }

    /**
     * 这个跟leetcode的subtree of another tree不同，那题要判断sameTree
     */
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


    //复习一下572. subtree of another tree

    /**
     * Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s.
     * A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) return false;
        return sameTree(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private boolean sameTree(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        if (s.val != t.val) {
            return false;
        }
        return sameTree(s.left, t.left) && sameTree(s.right, t.right);
    }

    //leetcode上另一种substring的解法太奇葩了不看了 讲解都错了

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

//        System.out.println(new HasSubTree().HasSubtree(p, root2));
        System.out.println(new HasSubTree_LC26().isSubtree(p, root2));
        int a = 1;
    }
}
