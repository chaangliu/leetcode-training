package bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Given an undirected tree, return its diameter: the number of edges in a longest path in that tree.
 * The tree is given as an array of edges where edges[i] = [u, v] is a bidirectional edge between nodes u and v.  Each node has labels in the set {0, 1, ..., edges.length}.
 * Example 1:
 * Input: edges = [[0,1],[0,2]]
 * Output: 2
 * Explanation:
 * A longest path of the tree is the path 1 - 0 - 2.
 * Example 2:
 * Input: edges = [[0,1],[1,2],[2,3],[1,4],[4,5]]
 * Output: 4
 * Explanation:
 * A longest path of the tree is the path 3 - 2 - 1 - 4 - 5.
 * Constraints:
 * 0 <= edges.length < 10^4
 * edges[i][0] != edges[i][1]
 * 0 <= edges[i][j] <= edges.length
 * The given edges form an undirected tree.
 * 20191105
 */
public class TreeDiameter {
    /**
     * 双周赛第三题。
     * 题意：给你二维数组，每个数组包含[u,v]，总体构成一个无向图（树）。问你这个树的直径（也就任意两个node之间最长的路径）是多少。
     * 思路：如果这个树是一棵普通的二叉树，最远距离怎么算？你可以知道树中任意一个node。最长的路径显然是树的底层的node到另一个底层的node。
     * 这题既然是undirected graph，那么可以用bfs找边缘，然后从边缘再bfs一次找另一个边缘，就知道距离了。
     * 另外还有一种方式是DFS，具体方法明天再看吧。。感冒咳嗽，giao不动了。
     * Approach1：BFS
     * 1. build tree and bfs from any chosen node to the farest rim node A
     * 2. bfs from node A to another farest rim node
     */
    public int treeDiameter(int[][] edges) {
        if (edges == null || edges.length == 0) return 0;
        //build tree(邻接表)
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            if (!graph.containsKey(u)) graph.put(u, new ArrayList<>());
            if (!graph.containsKey(v)) graph.put(v, new ArrayList<>());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        int[] pair = bfs(edges[0][0], graph);
        int rim = pair[0];
        int[] pair2 = bfs(rim, graph);
        return pair2[1];
    }

    //bfs returns an array of length 2,
    //arr[0] stores the farest node from root, arr[1] returns the distance between arr[0] and root
    private int[] bfs(int root, Map<Integer, List<Integer>> graph) {
        Queue<Integer> queue = new LinkedList<>();
        int dist = -1, rim = -1;
        queue.add(root);
        HashSet<Integer> visited = new HashSet<>();
        visited.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int u = queue.poll();
                if (!graph.containsKey(u)) continue;
                for (int v : graph.get(u)) {
                    if (!visited.contains(v)) {
                        visited.add(v);
                        queue.offer(v);
                        rim = v;
                    }
                }
            }
            dist++;
        }
        System.out.println("rim and dist are " + rim + ", " + dist);
        return new int[]{rim, dist};
    }


    /**
     * Approach2. DFS
     * 类似求树的高度，但是这里是求某个node向所有末梢dfs，沿途找到最长的两个树枝，和就是结果
     * https://www.acwing.com/solution/LeetCode/content/5794/
     */
    int res = 0;

    public int treeDiameter__DFS(int[][] edges) {
        if (edges == null || edges.length == 0) return 0;
        //build tree(邻接表)
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            if (!graph.containsKey(u)) graph.put(u, new ArrayList<>());
            if (!graph.containsKey(v)) graph.put(v, new ArrayList<>());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        dfs(0, -1, graph);
        return res;
    }

    private int dfs(int x, int fa, Map<Integer, List<Integer>> graph) {
        int max1 = 0, max2 = 0;
        if (graph.containsKey(x)) {
            for (int v : graph.get(x))
                if (v != fa) {
                    int t = dfs(v, x, graph) + 1;
                    if (max1 < t) {
                        max2 = max1;
                        max1 = t;
                    } else if (max2 < t) {
                        max2 = t;
                    }
                }
        }
        res = Math.max(res, (max1 + max2));
        return max1;
    }

    public static void main(String args[]) {
        new TreeDiameter().treeDiameter__DFS(new int[][]{{0, 1}, {1, 2}, {2, 3}, {1, 4}, {4, 5}});
    }
}
