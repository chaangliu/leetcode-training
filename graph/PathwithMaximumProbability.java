package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import bfs.SteppingNumbers;

/**
 * You are given an undirected weighted graph of n nodes (0-indexed), represented by an edge list where edges[i] = [a, b] is an undirected edge connecting the nodes a and b with a probability of success of traversing that edge succProb[i].
 * Given two nodes start and end, find the path with the maximum probability of success to go from start to end and return its success probability.
 * If there is no path from start to end, return 0. Your answer will be accepted if it differs from the correct answer by at most 1e-5.
 * Example 1:
 * Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
 * Output: 0.25000
 * Explanation: There are two paths from start to end, one having a probability of success = 0.2 and the other has 0.5 * 0.5 = 0.25.
 * Example 2:
 * Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
 * Output: 0.30000
 * Example 3:
 * Input: n = 3, edges = [[0,1]], succProb = [0.5], start = 0, end = 2
 * Output: 0.00000
 * Explanation: There is no path between 0 and 2.
 * Constraints:
 * 2 <= n <= 10^4
 * 0 <= start, end < n
 * start != end
 * 0 <= a, b < n
 * a != b
 * 0 <= succProb.length == edges.length <= 2*10^4
 * 0 <= succProb[i] <= 1
 * There is at most one edge between every two nodes.
 * 20200713
 */
public class PathwithMaximumProbability {
    /**
     * 题意：给你一个无向图，和每条边能通行的概率，问从start到end两个点的最大概率是多少。
     * 解法：我用DFS做的，但是有个1000个node的case通过不了，不知为啥。另外据说即便过了，DFS也会被5000 node的case卡超时。对于最短路径，BFS还是比DFS合适，这题在于你要看出它是一道最短路径题。
     * 一个解法是BFS，Bellman Ford Algorithm: BFS with Queue
     * 就是记录到达每个点的最大概率，在BFS过程中不断更新。
     */
    static class State {
        int node;
        double prob;

        State(int _node, double _prob) {
            node = _node;
            prob = _prob;
        }
    }

    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        // build graph -> double[0]: node, double[1]: edge prob
        Map<Integer, List<double[]>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; ++i) {
            int[] edge = edges[i];
            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.putIfAbsent(edge[1], new ArrayList<>());
            graph.get(edge[0]).add(new double[]{edge[1], succProb[i]});
            graph.get(edge[1]).add(new double[]{edge[0], succProb[i]});
        }

        double[] probs = new double[n];  // best prob so far for each node
        Queue<State> queue = new LinkedList<>();
        queue.offer(new State(start, 1.0));
        while (!queue.isEmpty()) {
            State state = queue.poll();
            int u = state.node;
            double uProb = state.prob;
            for (double[] pair : graph.getOrDefault(u, new ArrayList<>())) {
                int v = (int) pair[0];
                double newProb = uProb * pair[1]; // 已犯错误：这儿一开始没用uProb来计算，而用了probs[u]，这是不对的，因为而用了probs[u]可能还没有被更新，只有Queue中的才能保证是最新的prob
                if (newProb <= probs[v]) continue;
                probs[v] = newProb;
                queue.offer(new State(v, newProb));
            }
        }
        return probs[end];
    }
}
