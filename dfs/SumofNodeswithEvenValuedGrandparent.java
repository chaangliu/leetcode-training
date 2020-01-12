package dfs;

import tree.TreeNode;

/**
 * Given a binary tree, return the sum of values of nodes with even-valued grandparent.  (A grandparent of a node is the parent of its parent, if it exists.)
 * If there are no nodes with an even-valued grandparent, return 0.
 * Example 1:
 * Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
 * Output: 18
 * Explanation: The red nodes are the nodes with even-value grandparent while the blue nodes are the even-value grandparents.
 * Constraints:
 * The number of nodes in the tree is between 1 and 10^4.
 * The value of nodes is between 1 and 100.
 * 20200111
 */
public class SumofNodeswithEvenValuedGrandparent {

    /**
     * 题意：求所有grand parent的val是偶数的node的val之和。
     * 周赛第三题，我一开始一直想利用距离来实现O(n)的解法，也就是祖父的距离是2，到0的时候就把孩子加进去，但是写的时候发现无法区分爷爷和父亲。
     * 然后写了个intuitive的答案能过，但是速度不好，后面贴上。
     * 第一种方法，lee的答案。告诉当前node，它的parent和grandparent的val是多少。
     */
    public int sumEvenGrandparent(TreeNode root) {
        return helper(root, 1, 1);
    }

    public int helper(TreeNode node, int p, int gp) {
        if (node == null) return 0;
        // 当前的node的node就是下一层的父亲，当前node的父亲就是下一层的爷爷
        return helper(node.left, node.val, p) + helper(node.right, node.val, p) + (gp % 2 == 0 ? node.val : 0);
    }


    /**
     * 我的答案：向下两层遍历。时间复杂度O(n * log n)?
     */
    int res = 0;

    public int sumEvenGrandparent__(TreeNode root) {
        dfs1(root);
        return res;
    }

    private void dfs1(TreeNode root) {
        if (root == null) return;
        if ((root.val & 1) == 0) dfs2(root, 2);
        dfs1(root.left);
        dfs1(root.right);
    }

    private void dfs2(TreeNode root, int d) {
        if (root == null) return;
        if (d == 0) res += root.val;
        dfs2(root.left, d - 1);
        dfs2(root.right, d - 1);
    }

    /**
     * 也可以这么写
     */
    public int sumEvenGrandparent_(TreeNode root) {
        return dfs(root, -1, -1);
    }

    private int dfs(TreeNode root, int p, int gp) {
        if (root == null) return 0;
        return (gp == 2 ? root.val : 0) + dfs(root.left, root.val % 2 == 0 ? 2 : -1, p) + dfs(root.right, root.val % 2 == 0 ? 2 : -1, p);
    }
}
