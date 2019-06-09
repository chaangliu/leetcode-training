package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree with N nodes, each node has a different value from {1, ..., N}.
 * <p>
 * A node in this binary tree can be flipped by swapping the left child and the right child of that node.
 * <p>
 * Consider the sequence of N values reported by a preorder traversal starting from the root.  Call such a sequence of N values the voyage of the tree.
 * <p>
 * (Recall that a preorder traversal of a node means we report the current node's value, then preorder-traverse the left child, then preorder-traverse the right child.)
 * <p>
 * Our goal is to flip the least number of nodes in the tree so that the voyage of the tree matches the voyage we are given.
 * <p>
 * If we can do so, then return a list of the values of all nodes flipped.  You may return the answer in any order.
 * <p>
 * If we cannot do so, then return the list [-1].
 * <p>
 * 20190608
 */
public class FlipBinaryTreeToMatchPreorderTraversal {

    /**
     * 用全局的i记录index，如果跟voyage里的不一样，就把右边的和左边的颠倒过来递归；否则正常递归。
     **/
    int i = 0;

    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        List<Integer> res = new ArrayList<>();
        List<Integer> res1 = new ArrayList<>();
        res1.add(-1);
        return dfs(res, root, voyage) ? res : res1;
    }

    private boolean dfs(List<Integer> res, TreeNode root, int[] voyage) {
        if (root == null) return true;
        if (root.val != voyage[i++]) return false;
        if (root.left != null && root.left.val != voyage[i]) {
            res.add(root.val);
            return dfs(res, root.right, voyage) && dfs(res, root.left, voyage);
        }
        return dfs(res, root.left, voyage) && dfs(res, root.right, voyage);
    }
}
