package tree;

/**
 * Given a binary tree root, a node X in the tree is named good if in the path from root to X there are no nodes with a value greater than X.
 * <p>
 * Return the number of good nodes in the binary tree.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * Input: root = [3,1,4,3,null,1,5]
 * Output: 4
 * Explanation: Nodes in blue are good.
 * Root Node (3) is always a good node.
 * Node 4 -> (3,4) is the maximum value in the path starting from the root.
 * Node 5 -> (3,4,5) is the maximum value in the path
 * Node 3 -> (3,1,3) is the maximum value in the path.
 * Example 2:
 * Input: root = [3,3,null,4,2]
 * Output: 3
 * Explanation: Node 2 -> (3, 3, 2) is not good, because "3" is higher than it.
 * Example 3:
 * <p>
 * Input: root = [1]
 * Output: 1
 * Explanation: Root is considered as good.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the binary tree is in the range [1, 10^5].
 * Each node's value is between [-10^4, 10^4].
 * 20200517
 */
public class CountGoodNodesinBinaryTree {
    /**
     * 题意：给你一棵根为 root 的二叉树，请你返回二叉树中好节点的数目。
     * 「好节点」X 定义为：从根到该节点 X 所经过的节点中，没有任何节点的值大于 X 的值。
     * 解法：也就是沿途所有node.val都<= cur.val <=> 沿途最大的node.val <= cur.val。dfs即可。
     */
    int res = 0;

    public int goodNodes(TreeNode root) {
        dfs(root, -10001);
        return res;
    }

    /**
     * 沿途所有node.val都<= cur.val <=> 沿途最大的node.val <= cur.val
     **/
    private void dfs(TreeNode cur, int max) {
        if (cur == null) return;
        if (max <= cur.val) res++;
        dfs(cur.left, Math.max(max, cur.val));
        dfs(cur.right, Math.max(max, cur.val));
    }

    /**
     * 不用全局变量：
     */
    public int goodNodes_(TreeNode root) {
        return goodNodes(root, -10000);
    }

    public int goodNodes(TreeNode root, int ma) {
        if (root == null) return 0;
        int res = root.val >= ma ? 1 : 0;
        res += goodNodes(root.left, Math.max(ma, root.val));
        res += goodNodes(root.right, Math.max(ma, root.val));
        return res;
    }
}
