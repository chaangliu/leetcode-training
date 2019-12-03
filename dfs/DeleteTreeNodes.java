package dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A tree rooted at node 0 is given as follows:
 * The number of nodes is nodes;
 * The value of the i-th node is value[i];
 * The parent of the i-th node is parent[i].
 * Remove every subtree whose sum of values of nodes is zero.
 * After doing so, return the number of nodes remaining in the tree.
 * Example 1:
 * Input: nodes = 7, parent = [-1,0,0,1,2,2,2], value = [1,-2,4,0,-2,-1,-1]
 * Output: 2
 * Constraints:
 * 1 <= nodes <= 10^4
 * -10^5 <= value[i] <= 10^5
 * parent.length == nodes
 * parent[0] == -1 which indicates that 0 is the root.
 */
public class DeleteTreeNodes {
    /**
     * 题意：给你一棵树，如果subtree是0就把subtree删掉。问最后剩下几个node。
     * 我按照hint来做的， 先统计每个node的sum，然后再traverse一次，遇到sum是0的就结束dfs。lee给了one pass解法
     * 我的解法：DFS
     */
    public int deleteTreeNodes(int nodes, int[] parent, int[] value) {
        //use map to indicate the tree
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < parent.length; i++) {
            map.putIfAbsent(parent[i], new ArrayList<>());
            map.get(parent[i]).add(i);
        }
        Map<Integer, Integer> sum = new HashMap<>();
        dfs(map, sum, value, 0);//the tree is rooted at node 0
        dfs2(map, sum, 0);
        return res;
    }

    int res = 0;

    //dfs返回每个node的sum
    private int dfs(Map<Integer, List<Integer>> tree, Map<Integer, Integer> sum, int[] value, int root) {
        List<Integer> children = tree.get(root);
        int total = value[root];
        if (children != null)
            for (int c : children) {//这里如果children == null会空指针错误
                total += dfs(tree, sum, value, c);
            }
        sum.put(root, total);
        return total;
    }

    //dfs2自顶向下traverse，遇到sum是0的node就不再继续
    private void dfs2(Map<Integer, List<Integer>> tree, Map<Integer, Integer> sum, int root) {
        if (sum.getOrDefault(root, -1) == 0) return;
        System.out.println("node is " + root + ", with sum : " + sum.get(root));
        res++;
        if (!tree.containsKey(root)) return;
        for (int c : tree.get(root)) {
            dfs2(tree, sum, c);
        }
    }

    /**
     * lee的one pass解法
     * 这题的隐藏条件：parent[i] < i for all i > 0
     */
    public int deleteTreeNodes____ONE_PASS(int n, int[] parent, int[] value) {
        int[] res = new int[n];
        for (int i = n - 1; i > 0; --i) {
            value[parent[i]] += value[i];
            res[parent[i]] += value[i] != 0 ? res[i] + 1 : 0;
        }
        return value[0] != 0 ? res[0] + 1 : 0;
    }
}
