package array;

import java.util.LinkedList;
import java.util.Queue;

import tree.TreeNode;

/**
 * Created by DrunkPiano on 2017/4/23.
 */

public class SumRootToLeafNumbers {
    /**
     * 题意：计算从root到leaf组成的所有数字的和。
     * 解法：DFS或者BFS。
     */
    public int sumNumbers(TreeNode root) {
        dfs(root, 0);
        return res;
    }

    int res = 0;

    private void dfs(TreeNode node, int cur) {
        if (node == null) return;
        int v = cur * 10 + node.val;
        if (node.left == null && node.right == null) {
            res += v;
            return;
        }
        dfs(node.left, v);
        dfs(node.right, v);
    }

    /**
     * 不用全局变量的dfs, 思想是遇到叶子结点就返回以便累加；从这个角度看，dfs_返回的值意思就是从当前结点开始找所有leaf能拿到的sum。
     */
    public int sumNumbers_d(TreeNode root) {
        return dfs_(root, 0);
    }

    public int dfs_(TreeNode root, int prevSum) {
        if (root == null) {
            return 0;
        }
        int sum = prevSum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return sum;
        } else {
            return dfs_(root.left, sum) + dfs_(root.right, sum);
        }
    }

    /**
     * BFS需要两个queue，一个装Node，一个装迄今为止的值
     */
    public int sumNumbers_bfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int sum = 0;
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> numQueue = new LinkedList<>();
        nodeQueue.offer(root);
        numQueue.offer(root.val);
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            int num = numQueue.poll();
            TreeNode left = node.left, right = node.right;
            if (left == null && right == null) {
                sum += num;
            } else {
                if (left != null) {
                    nodeQueue.offer(left);
                    numQueue.offer(num * 10 + left.val);
                }
                if (right != null) {
                    nodeQueue.offer(right);
                    numQueue.offer(num * 10 + right.val);
                }
            }
        }
        return sum;
    }
}
