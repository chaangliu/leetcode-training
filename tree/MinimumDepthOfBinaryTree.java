package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, find its minimum depth.
 * <p>
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 * Created by DrunkPiano on 2017/3/1.
 */

public class MinimumDepthOfBinaryTree {
    /**
     * 题意：求二叉树最小深度。也就是离root最近的leaf node的深度。
     * 解法：BFS/DFS。虽然都是O(n)但是bfs不用完全遍历。
     */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int depth = 1;
        while (!q.isEmpty()) {
            for (int size = q.size(); size > 0; size--) {
                TreeNode node = q.poll();
                if (node.left == null && node.right == null) return depth;
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            depth++;
        }
        return depth;
    }


    /**
     * 我的dfs思路是找所有的leaf node比较深度。
     */
    int res = Integer.MAX_VALUE;

    public int minDepth_(TreeNode root) {
        if (root == null) return 0;
        dfs(root, 1);
        return res;
    }

    private void dfs(TreeNode node, int depth) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            res = Math.min(depth, res);
            return;
        }
        dfs(node.left, depth + 1);
        dfs(node.right, depth + 1);
    }


    /**
     * 如果不用全局变量，需要考虑左右子树有一个为空的情况。
     */
    public int minDepth___(TreeNode root) {
        if (root == null) return 0;
        //这道题递归条件里分为三种情况
        //1.左孩子和有孩子都为空的情况，说明到达了叶子节点，直接返回1即可
        if (root.left == null && root.right == null) return 1;
        //2.如果左孩子和由孩子其中一个为空，那么需要返回比较大的那个孩子的深度
        int m1 = minDepth___(root.left);
        int m2 = minDepth___(root.right);
        //这里其中一个节点为空，说明m1和m2有一个必然为0，所以可以返回m1 + m2 + 1;
        if (root.left == null || root.right == null) return m1 + m2 + 1;

        //3.最后一种情况，也就是左右孩子都不为空，返回最小深度+1即可
        return Math.min(m1, m2) + 1;
    }

    /**
     * 另一种方式，借助MAX_VALUE
     */
    public int minDepth__(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        int min_depth = Integer.MAX_VALUE; // 为了排除左右子树只有一棵为空的情况；如果直接比较left，right，那left right可能为0
        if (root.left != null) min_depth = Math.min(minDepth(root.left), min_depth);
        if (root.right != null) min_depth = Math.min(minDepth(root.right), min_depth);
        return min_depth + 1; // 左右子树的最小深度中较小的那个+1
    }
}
