package graph;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.
 * <p>
 * Each person may dislike some other people, and they should not go into the same group.
 * <p>
 * Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.
 * <p>
 * Return true if and only if it is possible to split everyone into two groups in this way.
 * Example 1:
 * <p>
 * Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
 * Output: true
 * Explanation: group1 [1,4], group2 [2,3]
 * Example 2:
 * <p>
 * Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
 * Output: false
 * Example 3:
 * <p>
 * Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
 * Output: false
 * <p>
 * 20190330
 */
public class PossibleBipartition {

    /**
     * 这题是图的思想。想通了之后蛮容易写的。
     * 从每个起点开始建图，
     * 然后把所有node涂成两种颜色，neighbour的颜色交替涂色；
     * 如果在涂色的过程中发现要涂的那个node已经被涂上了另一种颜色，就返回false
     */
    public boolean possibleBipartition(int N, int[][] dislikes) {
        //build graph
        ArrayList<Integer> graph[] = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) graph[i] = new ArrayList<>();
        for (int[] edge : dislikes) {
            int u = edge[0], v = edge[1];
            graph[u].add(v);
        }

        HashMap<Integer, Integer> colorMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            //只要所有节点都被涂色并且没冲突就行
            if (!colorMap.containsKey(N) && !color(colorMap, graph, i, 0)) return false;
        }
        return true;
    }

    //涂色，返回是否遇到冲突
    private boolean color(HashMap<Integer, Integer> colorMap, ArrayList<Integer>[] graph, int node, int color) {
        //要涂色的点已经被涂色，判断有没有冲突
        if (colorMap.containsKey(node)) {
            return colorMap.get(node) == color;
        }
        colorMap.put(node, color);
        for (int neighbor : graph[node]) {
            if (!color(colorMap, graph, neighbor, color ^ 1)) return false;
        }
        return true;
    }
}
