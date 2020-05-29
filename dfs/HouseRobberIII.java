package dfs;

import java.util.HashMap;
import java.util.Map;

import tree.TreeNode;

/**
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.
 * <p>
 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
 * <p>
 * Example 1:
 * <p>
 * Input: [3,2,3,null,3,null,1]
 * <p>
 * 3
 * / \
 * 2   3
 * \   \
 * 3   1
 * <p>
 * Output: 7
 * Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 * Example 2:
 * Input: [3,4,5,1,3,null,1]
 * 3
 * / \
 * 4   5
 * / \   \
 * 1   3   1
 * Output: 9
 * Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
 */
public class HouseRobberIII {

    /**
     * 题意：房子是二叉树形式分布，不能偷两根线相连的房子。问最大收益。
     * 解法：20200529 review 再看真是很清晰明了；
     * DFS WITH MEMO。本质上，跟第一题一样，只是换了一种数据结构；因为Tree没有index，所以我们可以直接用Map保存对象，记录从当前位置向下的最大收益。
     * 恍然大悟，原来这就是所谓的「树形DP」
     **/
    public int rob(TreeNode root) {
        return dfs(root, new HashMap<>());
    }

    private int dfs(TreeNode root, HashMap<TreeNode, Integer> memo) {
        if (root == null) return 0;
        if (memo.containsKey(root)) return memo.get(root);
        int notrob = dfs(root.left, memo) + dfs(root.right, memo);
        int rob = root.val;
        if (root.left != null) {
            rob += dfs(root.left.left, memo) + dfs(root.left.right, memo);
        }
        if (root.right != null) {
            rob += dfs(root.right.left, memo) + dfs(root.right.right, memo);
        }
        memo.put(root, Math.max(rob, notrob));
        return memo.get(root);
    }

    /**
     * 树形DP，int[2]，0代表不偷，1代表偷
     */
    public int rob__DP(TreeNode root) {
        int[] res = robSub(root);
        return Math.max(res[0], res[1]);
    }

    private int[] robSub(TreeNode root) {
        if (root == null) return new int[2];
        int[] left = robSub(root.left);
        int[] right = robSub(root.right);
        int[] res = new int[2];
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]); // 不偷
        res[1] = root.val + left[0] + right[0]; // 偷
        return res;
    }


    /**
     * 通过找规律发现是奇数line和偶数line的和的max；考虑bfs
     * 后来发现不行，因为隔一行也可以
     * 9:36 ~ 11：30..
     */
//    public int rob(TreeNode root) {
//        if (root == null) return 0;
//        List<List<Integer>> rows = new ArrayList<>();
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.offer(root);
//        while (!queue.isEmpty()) {
//            int size = queue.size();
//            List<Integer> row = new ArrayList<>();
//            for (int i = 0; i < size; i++) {
//                TreeNode node = queue.poll();
//                row.add(node.val);
//                if (node.left != null) {
//                    queue.offer(node.left);
//                }
//                if (node.right != null) {
//                    queue.offer(node.right);
//                }
//            }
//            rows.add(row);
//        }
//        int res = Integer.MIN_VALUE;
//        List<List<Integer>> permutations = new ArrayList<>();
//        permute(0, rows.size(), new ArrayList<Integer>(), permutations, new boolean[rows.size()]);
////        permute(0, 5, new ArrayList<Integer>(), permutations, new boolean[5]);
//        for (int i = 0; i < permutations.size(); i++) {
//            int sum = 0;
//            List<Integer> item = permutations.get(i);
//            for (Integer k : item) {
//                for (int j = 0; j < rows.get(k).size(); j++) {
//                    sum += rows.get(k).get(j);
//                }
//            }
//            res = Math.max(res, sum);
//        }
//        return res;
//    }
//
//    //寻找不相邻的数的所有可能性
//    private void permute(int start, int n, List<Integer> item, List<List<Integer>> res, boolean[] visited) {
//        //这里的条件要怎么写呢。。
//        //1. 上一轮加进去的数是倒数第二个数或者最后一个数
//        if (start >= n - 1) {
//            res.add(new ArrayList<Integer>(item));
//            return;
//        }
//        for (int i = start; i < n; i++) {
//            if (!visited[i]) {
//                if (i == 0) {
//                    if (!visited[i + 1]) {
//                        item.add(i);
//                        visited[i] = true;
//                    }
//                } else if (i < visited.length - 1) {
//                    if (!visited[i - 1] && !visited[i + 1]) {
//                        item.add(i);
//                        visited[i] = true;
//                    }
//                } else if (i == visited.length - 1) {
//                    if (!visited[i - 1]) {
//                        item.add(i);
//                        visited[i] = true;
//                    }
//                }
//                if (visited[i]) {
//                    permute(i + 1, n, item, res, visited);
//                    visited[i] = false;
//                    item.remove(item.size() - 1);
//                }
//            }
//        }
//    }
    public static void main(String args[]) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(4);
        new HouseRobberIII().rob(root);
    }
}
