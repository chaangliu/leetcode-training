package dfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个无向图graph，当这个图为二分图时返回true。
 * <p>
 * 如果我们能将一个图的节点集合分割成两个独立的子集A和B，并使图中的每一条边的两个节点一个来自A集合，一个来自B集合，我们就将这个图称为二分图。
 * <p>
 * graph将会以邻接表方式给出，graph[i]表示图中与节点i相连的所有节点。每个节点都是一个在0到graph.length-1之间的整数。这图中没有自环和平行边： graph[i] 中不存在i，并且graph[i]中没有重复的值。
 * 示例 1:
 * 输入: [[1,3], [0,2], [1,3], [0,2]]
 * 输出: true
 * 解释:
 * 无向图如下:
 * 0----1
 * |    |
 * |    |
 * 3----2
 * 我们可以将节点分成两组: {0, 2} 和 {1, 3}。
 * <p>
 * 示例 2:
 * 输入: [[1,2,3], [0,2], [0,1,3], [0,2]]
 * 输出: false
 * 解释:
 * 无向图如下:
 * 0----1
 * | \  |
 * |  \ |
 * 3----2
 * 我们不能将节点分割成两个独立的子集。
 * 注意:
 * <p>
 * graph 的长度范围为 [1, 100]。
 * graph[i] 中的元素的范围为 [0, graph.length - 1]。
 * graph[i] 不会包含 i 或者有重复的值。
 * 图是无向的: 如果j 在 graph[i]里边, 那么 i 也会在 graph[j]里边。
 * 20200414
 */
public class IsGraphBipartite {
    /**
     * 题意：给你一个邻接表表示的graph，判断是否是二分图。
     * 解法：利用二分图充要条件：着色法；对所有node着色，如果相邻node颜色都不想同，就是二分图。这题给出的是邻接表，不是edge，所以不需要build graph。
     * DFS
     */
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n];
        for (int i = 0; i < n; i++) {
            if (colors[i] == 0) {
                if (!dfs(i, 1, graph, colors)) return false;
            }
        }
        return true;
    }

    /**
     * 这个dfs应该注意的点是，1. 中断条件是u已经被着色 2. 着色放到for循环外面, 否则不好处理第一个node
     */
    private boolean dfs(int u, int color, int[][] graph, int[] colors) {
        if (colors[u] != 0) {
            return colors[u] == color; // 如果它已经被涂过色了，那它理应被涂成color这个颜色
        }
        colors[u] = color;
        for (int v : graph[u]) {
            if (!dfs(v, color == 2 ? 1 : 2, graph, colors)) return false;
        }
        return true;
    }

    /**
     * BFS，把周围的都涂成异色
     */
    public boolean isBipartite_BFS(int[][] graph) {
        int len = graph.length;
        int[] colors = new int[len];

        for (int i = 0; i < len; i++) {
            if (colors[i] != 0) continue;
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(i);
            colors[i] = 1;   // Blue: 1; Red: -1.
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                for (int next : graph[cur]) {
                    if (colors[next] == 0) {          // If this node hasn't been colored;
                        colors[next] = -colors[cur];  // Color it with a different color;
                        queue.offer(next);
                    } else if (colors[next] != -colors[cur]) {   // If it is colored and its color is different, return false;
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
