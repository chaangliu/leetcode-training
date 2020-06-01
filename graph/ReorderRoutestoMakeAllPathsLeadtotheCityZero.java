package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * There are n cities numbered from 0 to n-1 and n-1 roads such that there is only one way to travel between two different cities (this network form a tree). Last year, The ministry of transport decided to orient the roads in one direction because they are too narrow.
 * Roads are represented by connections where connections[i] = [a, b] represents a road from city a to b.
 * This year, there will be a big event in the capital (city 0), and many people want to travel to this city.
 * Your task consists of reorienting some roads such that each city can visit the city 0. Return the minimum number of edges changed.
 * It's guaranteed that each city can reach the city 0 after reorder.
 * Example 1:
 * Input: n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
 * Output: 3
 * Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
 * Example 2:
 * Input: n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
 * Output: 2
 * Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
 * Example 3:
 * <p>
 * Input: n = 3, connections = [[1,0],[2,0]]
 * Output: 0
 * Constraints:
 * <p>
 * 2 <= n <= 5 * 10^4
 * connections.length == n-1
 * connections[i].length == 2
 * 0 <= connections[i][0], connections[i][1] <= n-1
 * connections[i][0] != connections[i][1]
 * 20200601
 */
public class ReorderRoutestoMakeAllPathsLeadtotheCityZero {
    /**
     * 题意：路线用 connections 表示，其中 connections[i] = [a, b] 表示从城市 a 到 b 的一条有向路线。今年，城市 0 将会举办一场大型比赛，很多游客都想前往城市 0 。重新规划路线方向，使每个城市都可以访问城市 0 。返回需要变更方向的最小路线数。
     * 解法：其实这题不存在所谓最小最大，只有一种方案，最终结果只有一种，因为只有n-1条路。所以只要遍历一遍；技巧是可以把它看成无向图，也可以标记方向：从0开始向外反向DFS或者BFS，如果遇到正常的方向，代表前往0是反向，就要+1.
     */
    public int minReorder(int n, int[][] connections) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) graph.put(i, new ArrayList<>());
        for (int[] c : connections) {
            graph.get(c[0]).add(c[1]);
            graph.get(c[1]).add(-c[0]);
        }
        return dfs(graph, 0, new boolean[n]);
    }

    private int dfs(Map<Integer, List<Integer>> graph, int u, boolean[] visited) {
        visited[u] = true;
        int res = 0;
        for (int v : graph.get(u)) {
            if (visited[Math.abs(v)]) continue;
            res += dfs(graph, Math.abs(v), visited) + (v > 0 ? 1 : 0);
        }
        return res;
    }
}
