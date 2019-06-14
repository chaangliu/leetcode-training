package tree;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * An undirected, connected tree with N nodes labelled 0...N-1 and N-1 edges are given.
 * <p>
 * The ith edge connects nodes edges[i][0] and edges[i][1] together.
 * <p>
 * Return a list ans, where ans[i] is the sum of the distances between node i and all other nodes.
 * <p>
 * Example 1:
 * <p>
 * Input: N = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
 * Output: [8,12,6,10,10,10]
 * Explanation:
 * Here is a diagram of the given tree:
 * 0
 * / \
 * 1   2
 * /|\
 * 3 4 5
 * We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
 * equals 1 + 1 + 2 + 2 + 2 = 8.  Hence, answer[0] = 8, and so on.
 * Note: 1 <= N <= 10000
 * <p>
 * 20190614
 */
public class SumofDistancesinTree {
    int[] res, count;
    ArrayList<HashSet<Integer>> tree;

    /**
     * 一道HARD，有点像树形DP(https://www.cnblogs.com/ZhaoxiCheung/p/LeetCode-SumofDistancesinTree.html)
     * 先建无向图，然后post order自底向上地记录每个node的res和拥有的children数，
     * 最后preorder自顶向下地利用一个巧妙的公式计算以每个node为root时的res。
     *
     * 相似题目：968、979
     */
    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        tree = new ArrayList<HashSet<Integer>>();
        res = new int[N];
        count = new int[N];
        for (int i = 0; i < N; ++i)
            tree.add(new HashSet<Integer>());
        for (int[] e : edges) {
            tree.get(e[0]).add(e[1]);//u -> v
            tree.get(e[1]).add(e[0]);//v -> u
        }
        dfs(0, -1);
        dfs2(0, -1);
        return res;
    }

    public void dfs(int root, int pre) {
        for (int i : tree.get(root)) {
            if (i == pre) continue;//是无向图，child也指向parent，这里的意思是跳过child指向parent的情况
            dfs(i, root);
            count[root] += count[i];//count[i]记录i有多少个children，【bottom up】地计算，所以post order
            res[root] += res[i] + count[i];//res[i]记录i到所有children的距离之和。+ count[i]的意思是root到每个child的距离都要比i多1
        }
        count[root]++;//把自己算上
    }


    public void dfs2(int root, int pre) {
        for (int i : tree.get(root)) {
            if (i == pre) continue;
            //When we move our root from parent to its child i, count[i] points get 1 closer to root, n - count[i] nodes get 1 futhur to root.
            //总数是确定的res[root]，变更root到i，那么count[i]个node离新root更近了
            res[i] = res[root] - count[i] + count.length - count[i];
            dfs2(i, root);
        }
    }

    public static void main(String args[]) {
        int[][] arr = new int[][]{{0, 1}, {0, 2}, {2, 3}, {2, 4}, {2, 5}};
        new SumofDistancesinTree().sumOfDistancesInTree(6, arr);
    }
}
