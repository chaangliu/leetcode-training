package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 * Some courses may have direct prerequisites, for example, to take course 0 you have first to take course 1, which is expressed as a pair: [1,0]
 * Given the total number of courses n, a list of direct prerequisite pairs and a list of queries pairs.
 * You should answer for each queries[i] whether the course queries[i][0] is a prerequisite of the course queries[i][1] or not.
 * Return a list of boolean, the answers to the given queries.
 * Please note that if course a is a prerequisite of course b and course b is a prerequisite of course c, then, course a is a prerequisite of course c.
 * Example 1:
 * Input: n = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
 * Output: [false,true]
 * Explanation: course 0 is not a prerequisite of course 1 but the opposite is true.
 * Example 2:
 * <p>
 * Input: n = 2, prerequisites = [], queries = [[1,0],[0,1]]
 * Output: [false,false]
 * Explanation: There are no prerequisites and each course is independent.
 * Example 3:
 * Input: n = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
 * Output: [true,true]
 * 20200602
 */
public class CourseScheduleIV {
    /**
     * 题意：给你一些先修关系，比如[1,0]代表要上course0必须先上course1。然后给你一些查询，让你分别打印这些课程是否能完成。
     * 我的做法：dfs + memo。
     */
    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] p : prerequisites) {
            int u = p[0], v = p[1];
            graph.putIfAbsent(u, new ArrayList<>());
            graph.get(u).add(v);
        }
        List<Boolean> res = new ArrayList<>();
        HashMap<Integer, Boolean> memo = new HashMap<>();
        for (int[] q : prerequisites) {
            memo.put(1000 * q[0] + q[1], true);
        }
        for (int[] q : queries) {
            res.add(dfs(q[0], q[1], memo, graph));
        }
        return res;
    }

    /**
     * return 从u是否能到v
     */
    private boolean dfs(int u, int v, HashMap<Integer, Boolean> memo, Map<Integer, List<Integer>> graph) {
        int key = u * 1000 + v;
        if (memo.containsKey(key))
            return memo.get(key);
        if (!graph.containsKey(u)) {
            memo.put(key, false);
            return false;
        }
        for (int route : graph.get(u)) {
            if (dfs(route, v, memo, graph)) {
                memo.put(key, true);
                return true;
            }
        }
        memo.put(key, false);
        return false;
    }


    /**
     * Floyd–Warshall做法，就是判断u->v是否联通
     */
    public List<Boolean> checkIfPrerequisite_(int n, int[][] prerequisites, int[][] queries) {
        boolean[][] connected = new boolean[n][n]; // 邻接矩阵
        for (int[] p : prerequisites)
            connected[p[0]][p[1]] = true; // p[0] -> p[1]
        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    connected[i][j] = connected[i][j] || connected[i][k] && connected[k][j];
        List<Boolean> ans = new ArrayList<>();
        for (int[] q : queries)
            ans.add(connected[q[0]][q[1]]);
        return ans;
    }
}
