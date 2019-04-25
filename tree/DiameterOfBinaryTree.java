package tree;

/**
 * Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 * <p>
 * Example:
 * Given a binary tree
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 * <p>
 * Note: The length of path between two nodes is represented by the number of edges between them.
 * <p>
 * 20190425
 */
public class DiameterOfBinaryTree {
    /**
     * MY CODE
     * 观察可以发现，此题的结果就是每个node的左子树和右子树深度的和的最大值。但我感觉直接对每个节点重复求解应该会TLE？
     * --------------
     * AC了，并没有TLE，复杂度O(n * n)
     */
    int res = 0;

    public int diameterOfBinaryTree__SLOW(TreeNode root) {
        if (root == null) return 0;
        dfs(root);
        return res;
    }

    private void dfs(TreeNode root) {
        if (root == null) return;
        res = Math.max(res, getDepth(root.left) + getDepth(root.right));
        dfs(root.left);
        dfs(root.right);
    }

    private int getDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(getDepth(root.left), getDepth(root.right)) + 1;
    }

    /**
     * Approach 2
     * 看了下答案，发现事实上在求树的高度的过程中我们已经bottom-up地求过每一个子树的高度了，所以只要O（n）即可；
     * dfs return之前保存结果的写法跟前几天做的一道dfs + memo的题很像
     */
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        depth(root);
        return res;
    }

    private int depth(TreeNode root) {
        if (root == null) return 0;
        int ld = getDepth(root.left);
        int rd = getDepth(root.right);
        res = Math.max(res, ld + rd);
        return Math.max(ld, rd) + 1;
    }
}
