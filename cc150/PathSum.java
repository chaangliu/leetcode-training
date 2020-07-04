package cc150;

import java.util.HashMap;
import java.util.Map;

import tree.TreeNode;

/**
 * 给定一棵二叉树，其中每个节点都含有一个整数数值(该值或正或负)。设计一个算法，打印节点数值总和等于某个给定值的所有路径的数量。注意，路径不一定非得从二叉树的根节点或叶节点开始或结束，但是其方向必须向下(只能从父节点指向子节点方向)。
 *
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * 返回:
 *
 * 3
 * 解释：和为 22 的路径有：[5,4,11,2], [5,8,4,5], [4,11,7]
 * 提示：
 *
 * 节点总数 <= 10000
 */
public class PathSum {
    /**
     * 题意：找出tree中路径和为sum的路径的总数，可以不从root开始。
     * 解法：dfs，prefix。我犯了个错，map忘记backtrack了, 回到上一层的时候，int可以自动backtrack，但是map要手动backtrack。导致[0,1,1] 0这样的case，我返回了2.
     */
    int res = 0;

    public int pathSum(TreeNode root, int sum) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        dfs(root, sum, 0, map);
        return res;
    }

    private void dfs(TreeNode node, int sum, int cur, HashMap<Integer, Integer> map) {
        if (node == null) return;
        int d = cur + node.val - sum;
        if (map.containsKey(d)) res += map.get(d);
        map.put(cur + node.val, map.getOrDefault(cur + node.val, 0) + 1);
        dfs(node.left, sum, cur + node.val, map);
        dfs(node.right, sum, cur + node.val, map);
        map.put(cur + node.val, map.getOrDefault(cur + node.val, 0) - 1);// backtrack!
    }


    /**
     * return int 写法。返回从root开始一共有多少种解
     */
    public int pathSum_(TreeNode root, int sum) {
        Map<Integer, Integer> prefixSumCount = new HashMap<>();
        prefixSumCount.put(0, 1);
        return recursionPathSum(root, prefixSumCount, sum, 0);
    }

    private int recursionPathSum(TreeNode node, Map<Integer, Integer> prefixSumCount, int target, int currSum) {
        if (node == null) {
            return 0;
        }
        int res = 0;
        currSum += node.val;
        res += prefixSumCount.getOrDefault(currSum - target, 0);
        prefixSumCount.put(currSum, prefixSumCount.getOrDefault(currSum, 0) + 1);
        res += recursionPathSum(node.left, prefixSumCount, target, currSum);
        res += recursionPathSum(node.right, prefixSumCount, target, currSum);
        prefixSumCount.put(currSum, prefixSumCount.get(currSum) - 1);
        return res;
    }
}
