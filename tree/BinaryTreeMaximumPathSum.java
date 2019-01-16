package tree;

/**
 * Given a non-empty binary tree, find the maximum path sum.
 * <p>
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.
 * <p>
 * Example 1:
 * <p>
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
 * <p>
 * Output: 42
 * <p>
 * 20190116
 */
public class BinaryTreeMaximumPathSum {
    int res = Integer.MIN_VALUE;//node.val可能negative

    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        maxPathDown(root);
        return res;
    }

    /**
     * 这个函数求从node这个点出发到向下某个child为止的最大路径的值(包含当前节点。)
     * 为了帮助理解，可以打印例2里的数据:
     * 0 0 9 9
     * 0 0 15 15
     * 0 0 7 15
     * 15 7 20 42
     * 9 35 -10 42
     */
    private int maxPathDown(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, maxPathDown(node.left));
        int right = Math.max(0, maxPathDown(node.right));
        res = Math.max(res, left + right + node.val);
        System.out.println(left + " " + right + " " + node.val + " " + res);
        return node.val + Math.max(left, right);
    }


    public static void main(String args[]) {
        TreeNode node = new TreeNode(-10);
        node.left = new TreeNode(9);
        node.right = new TreeNode(20);
        node.right.left = new TreeNode(15);
        node.right.right = new TreeNode(7);

        new BinaryTreeMaximumPathSum().maxPathSum(node);
    }
}
