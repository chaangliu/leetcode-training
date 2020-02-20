package graph.toplogicalsort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 * <p>
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 * <p>
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 * <p>
 * Example 1:
 * <p>
 * Input: 2, [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 * Example 2:
 * <p>
 * Input: 2, [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0, and to take course 0 you should
 * also have finished course 1. So it is impossible.
 * <p>
 * 关于拓扑排序，可以参考：
 * https://blog.csdn.net/qq_24028753/article/details/77692447
 * <p>
 * 如何写出它的拓扑排序呢？这里说一种比较常用的方法：
 * 从 DAG 图中选择一个 没有前驱（即入度为0）的顶点并输出。
 * 从图中删除该顶点和所有以它为起点的有向边。
 * 重复 1 和 2 直到当前的 DAG 图为空或当前图中不存在无前驱的顶点为止。后一种情况说明有向图中必然存在环。
 * <p>
 * 20190117
 */
public class CourseSchedule {
    /**
     * 题意：给你0~n-1一共n节课的一些前置课程关系[课程，前置课程]，问能否上完所有n节课。
     * 解法：就是寻找是否有冲突的课程，比如[a,b][b,c][c,a]，也就是寻找图中是否有环。
     * 用拓扑排序可以判断图中是否有环。
     */
    // BFS 邻接矩阵(adjacent matrix) 2020 review
    public boolean canFinish(int n, int[][] prerequisites) {
        int[] indegree = new int[n];
        int[][] m = new int[n][n];
        for (int[] p : prerequisites) {
            int cur = p[0], pre = p[1];
            if (m[pre][cur] == 0) indegree[cur]++;
            m[pre][cur] = 1;
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) q.offer(i);
        }
        int cnt = 0;
        while (!q.isEmpty()) {
            int node = q.poll(); //取出当前入度为0的node
            cnt++;
            for (int i = 0; i < n; i++) {
                if (m[node][i] > 0 && --indegree[i] == 0) {
                    q.offer(i);
                }
            }
        }
        return cnt == n;
    }

    // BFS 邻接矩阵(adjacent matrix)
    public boolean canFinish____0(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0 || prerequisites[0].length == 0) return true;
        int[][] matrix = new int[numCourses][numCourses];
        int[] indegree = new int[numCourses];// 保存每个node的入度数量，这么做是为了后面把入度为0的node加入队列
        for (int i = 0; i < prerequisites.length; i++) {
            //当前课程
            int cur = prerequisites[i][0];
            //当前课程的前驱课程
            int pre = prerequisites[i][1];
            if (matrix[pre][cur] == 0) {
                //pre->cur, 完成当前课程的前驱课程（入度）+1；这个if是防止prerequisites数组有重复
                indegree[cur]++;
            }
            //用邻接矩阵描述有向图；matrix的指向：需要先上的课指向后上的课
            matrix[pre][cur] = 1;
        }
        int count = 0;
        //把所有没有前驱课程的课加入队列
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
//                break;//已知错误1：这里不能break
            }
        }
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            count++;
            //在邻接矩阵中寻找当前课程上完了可以上的课程
            for (int i = 0; i < numCourses; i++) {
                if (matrix[cur][i] != 0 && --indegree[i] == 0) {//已犯错误2：要先判断还有没有要上的课 --indegree[i] == 0
                    queue.offer(i);//已犯错误3：写成了queue.offer(matrix[i][cur]);
                }
            }
        }
        return count == numCourses;
    }

    //BFS 邻接表(adjacent list)
    public boolean canFinish____1(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0 || prerequisites[0].length == 0) return true;
        ArrayList[] adjacentList = new ArrayList[numCourses];
        int[] inDegrees = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            //当前课程
            int ready = prerequisites[i][0];
            //当前课程的前驱课程
            int pre = prerequisites[i][1];
            inDegrees[ready]++;
            if (adjacentList[pre] == null) {//已犯错误1 没有初始化
                adjacentList[pre] = new ArrayList();
            }
            adjacentList[pre].add(ready);
        }
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int curCourse = queue.poll();
            count++;
            //在邻接表中寻找当前课程上完了可以上的课程
            for (int i = 0; adjacentList[curCourse] != null && i < adjacentList[curCourse].size(); i++) {
                int nextCourse = (int) adjacentList[curCourse].get(i);
                //如果nextCourse在curCourse结束之后就没有要上的课了
                if (--inDegrees[nextCourse] == 0) {
                    queue.offer(nextCourse);//已犯错误2：写成了queue.offer(i)
                }
            }

        }
        return count == numCourses;
    }

    //DFS 邻接表(adjacent list)
    //TODO 这题dfs好像也可以借助indgree表，然后通过visited的数组是否都为true判断
    public boolean canFinish____2(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0 || prerequisites[0].length == 0) return true;
        ArrayList[] adjacentList = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adjacentList[i] = new ArrayList();
        }
//        int[] inDegrees = new int[numCourses];// doesn't need a indegree list here
        for (int i = 0; i < prerequisites.length; i++) {
            //当前课程
            int ready = prerequisites[i][0];
            //当前课程的前驱课程
            int pre = prerequisites[i][1];
            if (adjacentList[pre] == null) {//已犯错误1 没有初始化
                adjacentList[pre] = new ArrayList();
            }
            adjacentList[pre].add(ready);
        }
        //从每个节点开始判断有没有环
        for (int i = 0; i < numCourses; i++) {
            if (!helper(i, adjacentList, new boolean[numCourses])) {
                return false;
            }
        }
        return true;
    }

    private boolean helper(int cur, ArrayList[] adjacentList, boolean[] visited) {
        if (visited[cur]) {
            //有环
            return false;
        } else {
            visited[cur] = true;
        }
        //当前课程上完了可以上的课程列表
        for (int i = 0; i < adjacentList[cur].size(); i++) {
            if (!helper((int) adjacentList[cur].get(i), adjacentList, visited)) {
                return false;
            }
        }
        visited[cur] = false;
        return true;
    }

    public static void main(String args[]) {
        int[][] prerequisites = new int[2][2];
        prerequisites[0] = new int[]{1, 0};
        prerequisites[1] = new int[]{2, 1};
        new CourseSchedule().canFinish____1(3, prerequisites);
    }
}
