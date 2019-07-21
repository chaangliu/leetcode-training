package bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Consider a directed graph, with nodes labelled 0, 1, ..., n-1.  In this graph, each edge is either red or blue, and there could be self-edges or parallel edges.
 * <p>
 * Each [i, j] in red_edges denotes a red directed edge from node i to node j.  Similarly, each [i, j] in blue_edges denotes a blue directed edge from node i to node j.
 * <p>
 * Return an array answer of length n, where each answer[X] is the length of the shortest path from node 0 to node X such that the edge colors alternate along the path (or -1 if such a path doesn't exist).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 3, red_edges = [[0,1],[1,2]], blue_edges = []
 * Output: [0,1,-1]
 * Example 2:
 * <p>
 * Input: n = 3, red_edges = [[0,1]], blue_edges = [[2,1]]
 * Output: [0,1,-1]
 * Example 3:
 * <p>
 * Input: n = 3, red_edges = [[1,0]], blue_edges = [[2,1]]
 * Output: [0,-1,-1]
 * Example 4:
 * <p>
 * Input: n = 3, red_edges = [[0,1]], blue_edges = [[1,2]]
 * Output: [0,1,2]
 * Example 5:
 * <p>
 * Input: n = 3, red_edges = [[0,1],[0,2]], blue_edges = [[1,0]]
 * Output: [0,1,1]
 * Constraints:
 * <p>
 * 1 <= n <= 100
 * red_edges.length <= 400
 * blue_edges.length <= 400
 * red_edges[i].length == blue_edges[i].length == 2
 * 0 <= red_edges[i][j], blue_edges[i][j] < n
 * 20190721
 */
public class ShortestPathWithAlternatingColors {

    /**
     * 这题我一开始觉得应该用Dijkstra的单源最短路径的改版（事实上也确实看到有人这么做），但是一方面我对Dijkstra不熟，另一方面也不知道怎么改。。
     * 看了别人，都用的BFS做（我看了Network Delay Time那题，也可以用BFS做，而且代码短很多）参考：https://leetcode.com/problems/shortest-path-with-alternating-colors/discuss/339972/JAVA-BFS
     * 【最短路径->BFS】，做过很多这种题了，尤其是在那种二维数组里比较常见，这个其实应该行程一个条件反射的，但我这次完全没有想到BFS
     * 这题的BFS也比较有特点，两个源头，二维的visited数组
     */
    class Node {
        int val;
        int dist;//distance在BFS里就是level
        int color;//denotes the color of edge start from this node

        Node(int v, int d, int c) {
            val = v;
            dist = d;
            color = c;
        }
    }

    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        final int RED = 0;
        final int BLUE = 1;
        boolean[][] visited = new boolean[n][2];//不能用一维数组的visited，否则可能发生覆盖的情况
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, 0, RED));
        queue.offer(new Node(0, 0, BLUE));
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (res[node.val] == -1 || node.dist < res[node.val]) res[node.val] = node.dist;//如果另一种颜色交替的效果更好，更新res
            if (node.color == RED) {
                for (int[] edge : red_edges) {
                    int u = edge[0], v = edge[1];
                    if (u == node.val && !visited[v][BLUE]) {//BFS的精髓；如果v被涂成过BLUE，说明之前已经访问过v，distance(level)一定比现在小，所以跳过
                        queue.offer(new Node(v, node.dist + 1, BLUE));//distance在BFS里就是level
                        visited[v][BLUE] = true;
                    }
                }
            } else {
                for (int[] edge : blue_edges) {
                    int u = edge[0], v = edge[1];
                    if (u == node.val && !visited[v][RED]) {
                        queue.offer(new Node(v, node.dist + 1, RED));
                        visited[v][RED] = true;
                    }
                }
            }
        }
        return res;
    }
}
