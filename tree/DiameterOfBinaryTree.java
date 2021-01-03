package tree;

/**
 * Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 * <p>
 * Example:
 * Given a binary tree
 *     1
 *    / \
 *   2   3
 *  / \
 * 4   5
 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 * <p>
 * Note: The length of path between two nodes is represented by the number of edges between them.
 * <p>
 * 20190425
 */
public class DiameterOfBinaryTree {
    /**
     * 题意：求二叉树的直径，也就是二叉树中最长的一条路径。
     * 利用求二叉树高度的代码就行；有点像binary tree maximum path sum，不需要缓存，在求树的高度的过程中我们已经bottom-up地求过每一个子树的高度了。
     */
    int res = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        depth(root);
        return res;
    }

    private int depth(TreeNode root) {
        if (root == null) return 0;
        int ld = depth(root.left);
        int rd = depth(root.right);
        res = Math.max(res, ld + rd);
        return Math.max(ld, rd) + 1;
    }
}
