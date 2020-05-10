package tree;

import java.util.List;

/**
 * Given an undirected tree consisting of n vertices numbered from 0 to n-1, which has some apples in their vertices. You spend 1 second to walk over one edge of the tree. Return the minimum time in seconds you have to spend in order to collect all apples in the tree starting at vertex 0 and coming back to this vertex.
 * The edges of the undirected tree are given in the array edges, where edges[i] = [fromi, toi] means that exists an edge connecting the vertices fromi and toi. Additionally, there is a boolean array hasApple, where hasApple[i] = true means that vertex i has an apple, otherwise, it does not have any apple.
 * Example 1:
 * Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,true,true,false]
 * Output: 8
 * Explanation: The figure above represents the given tree where red vertices have an apple. One optimal path to collect all apples is shown by the green arrows.
 * Example 2:
 * Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,false,true,false]
 * Output: 6
 * Explanation: The figure above represents the given tree where red vertices have an apple. One optimal path to collect all apples is shown by the green arrows.
 * Example 3:
 * <p>
 * Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,false,false,false,false,false]
 * Output: 0
 * Constraints:
 * <p>
 * 1 <= n <= 10^5
 * edges.length == n-1
 * edges[i].length == 2
 * 0 <= fromi, toi <= n-1
 * fromi < toi
 * hasApple.length == n
 * 20200510
 */
public class MinimumTimetoCollectAllApplesinaTree {
    /**
     * 题意：给你一个无向树，一个hasApple数组记录着某个node上是否有苹果。走一个edge需要1秒，问从0开始收集所以苹果需要的最短时间。
     * 解法：DFS。从node出发往0走。参考：https://leetcode-cn.com/problems/minimum-time-to-collect-all-apples-in-a-tree/solution/dfsshen-ru-qian-chu-by-geguanting/
     * 我一开始想用BFS，发现没法处理一条路上有多个苹果的情况。
     */
    int res = 0;

    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        int[] reversed = new int[n];
        for (int[] e : edges) {
            reversed[e[1]] = e[0]; //每个node都只有一个自上而下的node指向它
        }
        boolean[] visited = new boolean[n];
        visited[0] = true;
        for (int i = 0; i < n; i++) {
            if (hasApple.get(i)) {
                dfs(i, reversed, visited);
            }
        }
        return res * 2;
    }

    /**
     * edges.length == n-1说明每两个点之间都有且仅有一条路线（其实树的定义本来就是没有环的图）
     * 那么从每个有苹果🍎的node向0的方向遍历，把沿途的node数量记录下来，并且标记为v过
     **/
    private void dfs(int node, int[] reversed, boolean[] visited) {
        if (!visited[node]) {
            visited[node] = true;
            res++;
            dfs(reversed[node], reversed, visited);
        }
    }
}
