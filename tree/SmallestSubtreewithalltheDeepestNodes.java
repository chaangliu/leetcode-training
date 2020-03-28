package tree;

/**
 * Given a binary tree rooted at root, the depth of each node is the shortest distance to the root.
 *
 * A node is deepest if it has the largest depth possible among any node in the entire tree.
 *
 * The subtree of a node is that node, plus the set of all descendants of that node.
 *
 * Return the node with the largest depth such that it contains all the deepest nodes in its subtree.
 * Example 1:
 *
 * Input: [3,5,1,6,2,0,8,null,null,7,4]
 * Output: [2,7,4]
 * Explanation:
 *
 *
 *
 * We return the node with value 2, colored in yellow in the diagram.
 * The nodes colored in blue are the deepest nodes of the tree.
 * The input "[3, 5, 1, 6, 2, 0, 8, null, null, 7, 4]" is a serialization of the given tree.
 * The output "[2, 7, 4]" is a serialization of the subtree rooted at the node with value 2.
 * Both the input and output have TreeNode type.
 *
 *
 * Note:
 *
 * The number of nodes in the tree will be between 1 and 500.
 * The values of each node are unique.
 * 20200328
 */
public class SmallestSubtreewithalltheDeepestNodes {
    /**
     * 题意：找出包含所有最深层node的LCA。
     * 解法：one pass
     */
    class Solution {
        public TreeNode subtreeWithAllDeepest(TreeNode root) {
            return dfs(root).node;
        }

        /**
         * dfs(node) 返回的结果有两个部分：
         * Result.node：包含所有最深节点的最小子树的根节点。
         * Result.dist：node 到最深节点的距离。
         */
        private Result dfs(TreeNode root) {
            if (root == null) return new Result(null, 0);
            Result l = dfs(root.left), r = dfs(root.right);
            if (l.dist > r.dist) return new Result(l.node, l.dist + 1); // 左边包含了所有最深节点
            if (l.dist < r.dist) return new Result(r.node, r.dist + 1);
            return new Result(root, l.dist + 1);
        }
    }

    class Result {
        TreeNode node;
        int dist;
        Result(TreeNode n, int d) {
            node = n;
            dist = d;
        }
    }
}
