package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * You have N gardens, labelled 1 to N.  In each garden, you want to plant one of 4 types of flowers.
 * paths[i] = [x, y] describes the existence of a bidirectional path from garden x to garden y.
 * Also, there is no garden that has more than 3 paths coming into or leaving it.
 * Your task is to choose a flower type for each garden such that, for any two gardens connected by a path, they have different types of flowers.
 * Return any such a choice as an array answer, where answer[i] is the type of flower planted in the (i+1)-th garden.  The flower types are denoted 1, 2, 3, or 4.  It is guaranteed an answer exists.
 * Example 1:
 * Input: N = 3, paths = [[1,2],[2,3],[3,1]]
 * Output: [1,2,3]
 * Example 2:
 * Input: N = 4, paths = [[1,2],[3,4]]
 * Output: [1,2,1,2]
 * Example 3:
 * Input: N = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
 * Output: [1,2,3,4]
 * Note:
 * 1 <= N <= 10000
 * 0 <= paths.size <= 20000
 * No garden has 4 or more paths coming into or leaving it.
 * It is guaranteed an answer exists.
 * 20191212
 */
public class FlowerPlantingWithNoAdjacent {
    /**
     * 题意：N个花园，每个花园最多跟3个其他花园连通（双向），让你给所有花园涂色，让相邻的两个花园都有不同的颜色。
     * 我的做法：建图。
     */
    public int[] gardenNoAdj(int N, int[][] paths) {
        int[] c = new int[N];
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] p : paths) {
            graph.putIfAbsent(p[0], new ArrayList<>());
            graph.putIfAbsent(p[1], new ArrayList<>());
            graph.get(p[0]).add(p[1]);
            graph.get(p[1]).add(p[0]);
        }
        for (int i = 1; i <= N; i++) {
            List<Integer> list = graph.get(i);
            HashSet<Integer> set = new HashSet<>();
            set.add(1);
            set.add(2);
            set.add(3);
            set.add(4);
            if (list != null && list.size() != 0)//important
                for (int nei : list) {
                    set.remove(c[nei - 1]);
                }
            for (int rem : set) {
                c[i - 1] = rem;
                break;
            }
        }
        return c;
    }

    /**
     * lee的做法，差不多
     */
    public int[] gardenNoAdj__(int N, int[][] paths) {
        Map<Integer, Set<Integer>> G = new HashMap<>();
        for (int i = 0; i < N; i++) G.put(i, new HashSet<>());
        for (int[] p : paths) {
            G.get(p[0] - 1).add(p[1] - 1);
            G.get(p[1] - 1).add(p[0] - 1);
        }
        int[] res = new int[N];
        for (int i = 0; i < N; i++) {
            int[] colors = new int[5];
            for (int j : G.get(i))
                colors[res[j]] = 1;
            for (int c = 4; c > 0; --c)
                if (colors[c] == 0)//greedy地选
                    res[i] = c;
        }
        return res;
    }
}
