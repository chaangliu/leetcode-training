package graph.toplogicalsort;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 * <p>
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 * <p>
 * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
 * <p>
 * There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.
 * <p>
 * Example 1:
 * <p>
 * Input: 2, [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
 * course 0. So the correct course order is [0,1] .
 * Example 2:
 * <p>
 * Input: 4, [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,1,2,3] or [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
 * courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 * So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
 * Note:
 * <p>
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 * 20190801
 */
public class CourseScheduleII {
    /**
     * 题意：跟第一题一样，不过要打印出一个order。
     * 解法：跟第一题一样，用拓扑排序。我用了邻接表+BFS
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] adjList = (ArrayList<Integer>[]) new ArrayList[numCourses];
        int[] indegree = new int[numCourses];
        for (int[] edge : prerequisites) {
            int u = edge[1], v = edge[0];
            if (adjList[u] == null) adjList[u] = new ArrayList<>();
            adjList[u].add(v);
            indegree[v]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) if (indegree[i] == 0) queue.offer(i);
        int[] res = new int[numCourses];
        int count = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            res[count++] = node;
            List<Integer> adj = adjList[node];
            if (adj == null) continue;
            for (int nextNode : adj) {
                if (--indegree[nextNode] == 0) queue.offer(nextNode);
            }
        }
        return count != numCourses ? new int[0] : res;
    }
}
