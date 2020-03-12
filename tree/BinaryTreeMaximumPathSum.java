package tree;

/**
 * Given a non-empty binary tree, find the maximum path sum.
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.
 * Example 1:
 * Input: [1,2,3]
 * <p>
 * 1
 * / \
 * 2   3
 * <p>
 * Output: 6
 * Example 2:
 * <p>
 * Input: [-10,9,20,null,null,15,7]
 * <p>
 * -10
 * / \
 * 9  20
 * /  \
 * 15   7
 * Output: 42
 * 20190116
 * 20200131 review
 */
public class BinaryTreeMaximumPathSum {
    /**
     *  题意：给你一个tree，问最大的路径和是多少。可以拐弯，但是不能出现岔路。
     */
    int res = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return res;
    }

    /**
     * dfs返回从当前node开始向左或者向右的branch的最大path sum
     **/
    private int dfs(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, dfs(node.left));//已犯错误：没有取0作为下限
        int right = Math.max(0, dfs(node.right));
        res = Math.max(res, node.val + left + right);
        return Math.max(left, right) + node.val;
    }
}
