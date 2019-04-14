package tree;

/**
 * Given the root of a binary tree, find the maximum value V for which there exists different nodes A and B where V = |A.val - B.val| and A is an ancestor of B.
 * <p>
 * (A node A is an ancestor of B if either: any child of A is equal to B, or any child of A is an ancestor of B.)
 * Example 1:
 * Input: [8,3,10,1,6,null,14,null,null,4,7,13]
 * Output: 7
 * Explanation:
 * We have various ancestor-node differences, some of which are given below :
 * |8 - 3| = 5
 * |3 - 7| = 4
 * |8 - 1| = 7
 * |10 - 13| = 3
 * Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.
 * Note:
 * The number of nodes in the tree is between 2 and 5000.
 * Each node will have value between 0 and 100000.
 * <p>
 * 20190414
 */
public class MaximumDifferenceBetweenNodeAndAncestor {
    /**
     * 这题就是每一条dfs路径上最大值和最小值的差的最大值，想清楚就行了
     */
    int res = 0;

    public int maxAncestorDiff(TreeNode root) {
        dfs(root, root.val, root.val);
        return res;
    }

    private void dfs(TreeNode node, int min, int max) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            res = Math.max(res, Math.abs(Math.max(max, node.val) - Math.min(min, node.val)));
            return;
        }
        dfs(node.left, Math.min(min, node.val), Math.max(max, node.val));
        dfs(node.right, Math.min(min, node.val), Math.max(max, node.val));
    }

    /**
     * 另一种写法，每遍历一个node都更新res
     */
    public int maxAncestorDiff_(TreeNode root) {
        return dfs_(root, root.val, root.val);
    }

    public int dfs_(TreeNode root, int mx, int mn) {
        if (root == null) return 0;
        int res = Math.max(mx - root.val, root.val - mn);
        mx = Math.max(mx, root.val);
        mn = Math.min(mn, root.val);
        res = Math.max(res, dfs_(root.left, mx, mn));
        res = Math.max(res, dfs_(root.right, mx, mn));
        return res;
    }
}
