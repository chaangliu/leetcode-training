package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, return all root-to-leaf paths.
 * <p>
 * For example, given the following binary tree:
 * <p>
 *    1
 *  /   \
 * 2     3
 * \
 * 5
 * All root-to-leaf paths are:
 * <p>
 * ["1->2->5", "1->3"]
 * <p>
 * Created by DrunkPiano on 2017/4/25.
 */

public class BinaryTreePaths {
    /**
     * 题意：给定一个二叉树，返回所有从根节点到叶子节点的路径。
     * 解法：DFS或者BFS。DFS用StringBuilder的话需要手动回溯。BFS就是找到root就add到解集里。
     */
    class Solution {
        public List<String> binaryTreePaths(TreeNode root) {
            List<String> res = new ArrayList<>();
            dfs(root, res, "");
            return res;
        }

        private void dfs(TreeNode root, List<String> res, String cur) {
            if (root == null) return;
            if (root.left == null && root.right == null) {
                res.add(cur + root.val);
                return;
            }
            dfs(root.left, res, cur + root.val + "->");
            dfs(root.right, res, cur + root.val + "->");
        }
    }

    private void dfs(List<String> res, TreeNode root, StringBuilder sb) {
        if (isLeaf(root)) {
            sb.append(root.val);
            res.add(new String(sb));
            sb.deleteCharAt(sb.length() - 1);
            return;
        }

        if (root.left != null) {
            sb.append(root.val).append("->");
            dfs(res, root.left, sb);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (root.right != null) {
            sb.append(root.val).append("->");
            dfs(res, root.right, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    private boolean isLeaf(TreeNode node) {
        return (node != null && node.left == null && node.right == null);
    }

}
