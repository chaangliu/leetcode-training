package tree;

/**
 * We are given the head node root of a binary tree, where additionally every node's value is either a 0 or a 1.
 *
 * Return the same tree where every subtree (of the given tree) not containing a 1 has been removed.
 *
 * (Recall that the subtree of a node X is X, plus every node that is a descendant of X.)
 *
 * Example 1:
 * Input: [1,null,0,0,1]
 * Output: [1,null,0,null,1]
 * Explanation:
 * Only the red nodes satisfy the property "every subtree not containing a 1".
 * The diagram on the right represents the answer.
 * Example 2:
 * Input: [1,0,1,0,0,0,1]
 * Output: [1,null,1,null,1]
 * Example 3:
 * Input: [1,1,0,1,1,0,1,0]
 * Output: [1,1,0,1,1,null,1]
 */
public class BinaryTreePruning {
    /**
     * 题意：给你一棵二叉树，把所有不包含1的子树都剪掉。
     * 解法：dfs即可，one pass。
     */
    public TreeNode pruneTree(TreeNode root) {
        dfs(root);
        return root;
    }

    /**
     * 如果当前tree不包含1，返回true。
     **/
    private boolean dfs(TreeNode root) {
        if (root == null) return true;
        boolean l = false, r = false;
        if (dfs(root.left)) {
            root.left = null;
            l = true;
        }
        if (dfs(root.right)) {
            root.right = null;
            r = true;
        }
        return l && r && root.val != 1;
    }


    /**
     * lee的代码
     * 很神奇，没有利用辅助函数
     * 底下有人说，'Feel refreshed every time reading your code'. 说到我心坎里了
     */
    public TreeNode pruneTree_(TreeNode root) {
        if (root == null) return null;
        root.left = pruneTree(root.left); // 左子树等于修剪后的左子树，妙啊
        root.right = pruneTree(root.right);
        if (root.left == null && root.right == null && root.val == 0) return null;
        return root;
    }
}
