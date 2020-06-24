package graph.minimumspanningtree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a weighted undirected connected graph with n vertices numbered from 0 to n-1, and an array edges where edges[i] = [fromi, toi, weighti] represents a bidirectional and weighted edge between nodes fromi and toi. A minimum spanning tree (MST) is a subset of the edges of the graph that connects all vertices without cycles and with the minimum possible total edge weight.
 * Find all the critical and pseudo-critical edges in the minimum spanning tree (MST) of the given graph. An MST edge whose deletion from the graph would cause the MST weight to increase is called a critical edge. A pseudo-critical edge, on the other hand, is that which can appear in some MSTs but not all.
 * Note that you can return the indices of the edges in any order.
 * 20200624
 */
public class FindCriticalandPseudoCriticalEdgesinMinimumSpanningTree {

    /**
     * 题意：给你一些edges和它们代表的edge的权值，问你哪些边是MST（最小代价生成树）的关键边，哪些不是。
     * 解法：先建立MST，然后对于每条边e，去计算1. 必须使用e 2. 一定不用e 这两个值，如果不用e就会比MST的cost大，它就是关键边，否则不是。
     * 带有名字的算法一般比较长，不是重点，所以就摘抄了。
     * 以下代码来源：https://leetcode.com/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree/discuss/697750/Java-Simple-Solution-based-on-finding-MST
     */
    class Solution {
        public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {

            List<Integer> criticals = new ArrayList<>();
            List<Integer> pseduos = new ArrayList<>();

            Map<int[], Integer> map = new HashMap<>();
            for (int i = 0; i < edges.length; i++) {
                map.put(edges[i], i);
            }

            Arrays.sort(edges, (e1, e2) -> Integer.compare(e1[2], e2[2]));
            int minCost = buildMST(n, edges, null, null);

            for (int i = 0; i < edges.length; i++) {
                int[] edge = edges[i];
                int index = map.get(edge);
                int costWithout = buildMST(n, edges, null, edge);
                if (costWithout > minCost) {
                    criticals.add(index);
                } else {
                    int costWith = buildMST(n, edges, edge, null);
                    if (costWith == minCost) {
                        pseduos.add(index);
                    }
                }

            }

            return Arrays.asList(criticals, pseduos);
        }

        private int buildMST(int n, int[][] edges, int[] pick, int[] skip) {
            UnionFind uf = new UnionFind(n);
            int cost = 0;
            if (pick != null) {
                uf.union(pick[0], pick[1]);
                cost += pick[2];
            }

            for (int[] edge : edges) {
                if (edge != skip && uf.union(edge[0], edge[1])) {
                    cost += edge[2];
                }
            }
            return uf.count == 1 ? cost : Integer.MAX_VALUE;
        }
    }

    class UnionFind {
        final int[] parents;
        int count;

        public UnionFind(int n) {
            this.parents = new int[n];
            reset();
        }

        public void reset() {
            for (int i = 0; i < parents.length; i++) {
                parents[i] = i;
            }
            count = parents.length;
        }

        public int find(int i) {
            int parent = parents[i];
            if (parent == i) {
                return i;
            } else {
                int root = find(parent);
                parents[i] = root;
                return root;
            }
        }

        public boolean union(int i, int j) {
            int r1 = find(i);
            int r2 = find(j);
            if (r1 != r2) {
                count--;
                parents[r1] = r2;
                return true;
            } else {
                return false;
            }
        }

    }
}
