package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * There are n cities connected by m flights. Each fight starts from city u and arrives at v with a price w.
 * <p>
 * Now given all the cities and flights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.
 * <p>
 * Example 1:
 * Input:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 1
 * Output: 200
 * Explanation:
 * The graph looks like this:
 * <p>
 * <p>
 * The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
 * Example 2:
 * Input:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 0
 * Output: 500
 * Explanation:
 * The graph looks like this:
 * The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
 * Note:
 * The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
 * The size of flights will be in range [0, n * (n - 1) / 2].
 * The format of each flight will be (src, dst, price).
 * The price of each flight will be in the range [1, 10000].
 * k is in the range of [0, n - 1].
 * There will not be any duplicated flights or self cycles.
 * 20190921
 */
public class CheapestFlightsWithinKStops {

    /**
     * 这题其实就是求单源最短路径，只是加上了stops的限制。我一开始被题目的tag引导写了个bfs，超时了。还是不够敏锐。
     * 这题有个特点，不需要visited保存走过的路径，因为有k的limit，不用担心造成cycle。
     * 类似题目：NetworkDelayTime
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, Map<Integer, Integer>> prices = new HashMap<>();
        for (int[] f : flights) {
            prices.putIfAbsent(f[0], new HashMap<>());
            prices.get(f[0]).put(f[1], f[2]);//build graph [u - > (v, cost)]
        }
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> (Integer.compare(a[1], b[1])));
        queue.offer(new int[]{src, 0, k + 1});//city, minCost, maxStopsLeft
        while (!queue.isEmpty()) {
            int[] tuple = queue.poll();
            int city = tuple[0], minCost = tuple[1], stops = tuple[2];
            if (city == dst) return minCost;
            if (stops > 0 && prices.containsKey(city)) {
                for (int adj : prices.get(city).keySet()) {
                    queue.offer(new int[]{adj, minCost + prices.get(city).get(adj), stops - 1});
                }
            }
        }
        return -1;
    }


    /**
     * 我的BFS做法，TLE
     * map存储ajacent node，bfs k次，每个node保存从src到当前的最小cost
     **/
    public int findCheapestPrice_TLE(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, ArrayList<Node>> map = new HashMap<>();
        for (int[] f : flights) {
            map.putIfAbsent(f[0], new ArrayList<Node>());
            map.get(f[0]).add(new Node(f[1], f[2], Integer.MAX_VALUE));//val, cost
        }
        int res = Integer.MAX_VALUE;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(src, 0, 0));
        while (!queue.isEmpty() && K-- >= 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node root = queue.poll();
                if (!map.containsKey(root.val)) continue;
                for (Node node : map.get(root.val)) {
                    node.minDist = Math.min(node.minDist, node.cost + root.minDist);
                    if (node.val == dst) {
                        res = Math.min(res, node.minDist);
                    } else {
                        queue.offer(node);
                    }
                }
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    class Node {
        int val;
        int cost;
        int minDist;

        public Node(int v, int c, int m) {
            val = v;
            cost = c;
            minDist = m;
        }
    }
}
