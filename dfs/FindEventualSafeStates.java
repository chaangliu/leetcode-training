package dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * In a directed graph, we start at some node and every turn, walk along a directed edge of the graph.  If we reach a node that is terminal (that is, it has no outgoing directed edges), we stop.
 * Now, say our starting node is eventually safe if and only if we must eventually walk to a terminal node.  More specifically, there exists a natural number K so that for any choice of where to walk, we must have stopped at a terminal node in less than K steps.
 * Which nodes are eventually safe?  Return them as an array in sorted order.
 * The directed graph has N nodes with labels 0, 1, ..., N-1, where N is the length of graph.  The graph is given in the following form: graph[i] is a list of labels j such that (i, j) is a directed edge of the graph.
 * Example:
 * Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
 * Output: [2,4,5,6]
 * Note:
 * graph will have length at most 10000.
 * The number of edges in the graph will not exceed 32000.
 * Each graph[i] will be a sorted list of different integers, chosen within the range [0, graph.length - 1].
 * 20191121
 */
public class FindEventualSafeStates {
    /**
     * 题意：读了半天，意思是在有向图中找出一些node，这些node满足从它随便选一条路出发都不会遇到环。graph[i]的二维数组代表i的出度
     **/
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> res = new ArrayList<>();
        Integer[] memo = new Integer[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (!dfs(graph, i, new boolean[graph.length], memo)) {
                res.add(i);
            }
        }
        return res;
    }

    /**
     * dfs返回从node出发是否会遇到环, 如果有环，返回true
     * 我用一个visited数组记录是否走过某个node，memo记录dfs过程中的结果，0代表无环，1代表有环
     **/
    private boolean dfs(int[][] graph, int node, boolean[] visited, Integer[] memo) {
        if (visited[node]) return true;//回到某个v过的点
        if (memo[node] != null) return memo[node] == 1;//来到某个有环的node
        visited[node] = true;
        for (int next : graph[node]) {
            if (dfs(graph, next, visited, memo)) {
                memo[node] = 1;
                return true;
            }
        }
        visited[node] = false;
        memo[node] = 0;
        return false;
    }

    /**
     * 网上的solution，5ms，我那个要50+ms，很奇怪，把memo[node]拿到外面也一样，但是我记录了递归的执行次数，在题中的case看来都是7次.可能创建空间越少越好，以及int比Integer好。
     * 直接用memo来记录visited了，先把node标记成有环，如果执行了一圈都没发现环，就改成无环。
     */
    public List<Integer> eventualSafeNodes__(int[][] graph) {
        List<Integer> res = new ArrayList<>();
        if (graph == null || graph.length == 0) return res;
        int nodeCount = graph.length;
        int[] color = new int[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            if (dfs(graph, i, color)) res.add(i);
        }
        return res;
    }

    /**
     * dfs返回是否无环
     */
    public boolean dfs(int[][] graph, int start, int[] color) {
        if (color[start] != 0) return color[start] == 1;
        color[start] = 2;//2代表有环
        for (int newNode : graph[start]) {
            if (!dfs(graph, newNode, color)) return false;//其中一个子路径有环，就返回false
        }
        color[start] = 1;
        return true;//无环
    }

    /**
     * 模仿网上这个solution，可以这么写
     */
    public List<Integer> eventualSafeNodes____(int[][] graph) {
        List<Integer> res = new ArrayList<>();
        int[] memo = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (!dfsss(graph, i, memo)) {
                res.add(i);
            }
        }
        return res;
    }

    /**
     * dfs返回从node出发是否会遇到环, 如果有环，返回true
     **/
    private boolean dfsss(int[][] graph, int node, int[] memo) {
        if (memo[node] != 0) return memo[node] == 1;
        memo[node] = 1;
        for (int next : graph[node]) {
            if (dfsss(graph, next, memo)) {//回到了v过的node，或者来到了某个有环node的地方，说明当前node出发也有环
                return true;
            }
        }
        memo[node] = 2;
        return false;
    }
}
