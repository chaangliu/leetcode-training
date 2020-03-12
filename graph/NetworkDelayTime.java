package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * There are N network nodes, labelled 1 to N.
 * Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.
 * Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.
 * Example 1:
 * Input: times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
 * Output: 2
 * <p>
 * 20190606
 */
public class NetworkDelayTime {
    /**
     * 题意：给你N个网络结点，问从任意一个node发出信号，最早需要多少时间可以让所有node都收到信号。
     * 这题其实是求「最小」网络时延，因为不同路径时延不一样。
     * 可以转换成求K到其他所有节点的最短路径中最大的那个路径，也就是至少需要的时间。
     * 可以用单源最短路径算法解决，比如Bellman-Ford，Dijkstra；Floyd是任意两点最短路径的算法在这里似乎不适用。
     * 下面是O(n2)的dijkstra解法，很典型的textbook算法；当年考研的时候回画图找最短路径，代码是肯定写不出的。。
     */
    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        /**
         * build graph,
         * key是u，value是[[v,t],[v,t]..]
         */
        for (int[] edge : times) {
            if (!graph.containsKey(edge[0]))
                graph.put(edge[0], new ArrayList<int[]>());
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }
        /**
         * dist保存K到每个Node的距离
         */
        Map<Integer, Integer> dist = new HashMap<>();
        for (int node = 1; node <= N; ++node) dist.put(node, Integer.MAX_VALUE);
        dist.put(K, 0);
        boolean[] visited = new boolean[N + 1];
        while (true) {
            int u = -1;
            int minDist = Integer.MAX_VALUE;
            /**
             * 关键：在没有访问过的node中选一个与上一个node距离最短的node，作为新的起始节点（第一轮candNode(也就是u)肯定是K了，minDist = 0）
             * 操作：遍历一遍所有node，如果没visit过并且K到node的距离<minDist，下一个节点选取这个点
             */
            for (int i = 1; i <= N; ++i) {
                if (!visited[i] && dist.get(i) < minDist) {
                    minDist = dist.get(i);
                    u = i;
                }
            }
            if (u < 0) break;
            visited[u] = true;
            if (graph.containsKey(u)) {
                /**
                 * 对于u的每一个每一个adjacent node v，更新K到v的最小距离；
                 * 最小距离 = min(已保存的K到v的最小距离,K到u的最小距离+u到v的距离)
                 */
                for (int[] info : graph.get(u)) {
                    int v = info[0], t = info[1];
                    dist.put(v, Math.min(dist.get(v), dist.get(u) + t));
                }
            }
        }
        int ans = 0;
        for (int cand : dist.values()) {
            if (cand == Integer.MAX_VALUE) return -1;
            ans = Math.max(ans, cand);
        }
        return ans;
    }

    /**
     * Dijkstra/bfs，推荐这种写法。类似题目787
     * 最小堆存放二元组[node到K的distance，node]，每轮遍历临接点
     * dijkstra的bfs和普通bfs的显著区别是，dijkstra不是严格按照层数bfs，而是每次add之后从队列中取出最小的，
     * 两点不同：
     * 1. 这个queue不是linkedlist，而是priorityqueue，
     * 2. 不能像普通bfs一样在加入queue之前就标记visited，而要先把当前层全部加进去，取出来之后再判断是否v过。比如case：
     * [[1,2,1],[2,3,2],[1,3,4]]，3，1；如果不把当前层都加进去排序，就会造成3已经被v过，2，3这条更短的路就没法v了。
     * <p>
     * 时间：O(nlogn)
     */
    public int networkDelayTime__(int[][] times, int N, int K) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int[] time : times) {
            map.putIfAbsent(time[0], new HashMap<>());
            map.get(time[0]).put(time[1], time[2]);
        }
        //distance, node into pq
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        pq.add(new int[]{0, K});
        boolean[] visited = new boolean[N + 1];
        int res = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.remove();
            int curNode = cur[1];
            int curDist = cur[0];
            if (visited[curNode]) continue;//v过就跳过，这样能保证res是minDist
            visited[curNode] = true;
            res = curDist;
            N--;
            if (map.containsKey(curNode)) {
                for (int next : map.get(curNode).keySet()) {// 每次从neighbours里挑最近的；这儿不用判断或添加visited
                    pq.add(new int[]{curDist + map.get(curNode).get(next), next});
                }
            }
        }
        return N == 0 ? res : -1;
    }
}
