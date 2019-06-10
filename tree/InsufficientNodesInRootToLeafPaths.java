package tree;

/**
 * Given the root of a binary tree, consider all root to leaf paths: paths from the root to any leaf.  (A leaf is a node with no children.)
 * <p>
 * A node is insufficient if every such root to leaf path intersecting this node has sum strictly less than limit.
 * <p>
 * Delete all insufficient nodes simultaneously, and return the root of the resulting binary tree.
 * <p>
 * 20190610
 */
public class InsufficientNodesInRootToLeafPaths {
    /**
     * 这题意思是说，删除树中所有insufficient的node，insufficient是这么定义的，如果经过这个node的所有path，每一条的sum都<limit，
     * 就说这个node是insufficient的。
     * <p>
     * 这题本身很好，自底向上的dfs，但是昨天的contest中case出错了。
     */
    public TreeNode sufficientSubset(TreeNode root, int limit) {
        //最小条件：如果root是leaf，直接判断root.val是否<limit，也可以写成root.left == root.right
        if (root.left == null && root.right == null)
            return root.val < limit ? null : root;
        //左边node不为空，判断是否要把node.left设为null
        if (root.left != null) root.left = sufficientSubset(root.left, limit - root.val);
        if (root.right != null) root.right = sufficientSubset(root.right, limit - root.val);
        //最后如果左右都没了，说明当前node也是sufficient node
        return root.left == null && root.right == null ? null : root;
    }
}
