package other;

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

    //BFS
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0 || prerequisites[0].length == 0) return true;
        int[][] matrix = new int[numCourses][numCourses];
        int[] indegree = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            //当前课程
            int ready = prerequisites[i][0];
            //当前课程的前驱课程
            int pre = prerequisites[i][1];
            if (matrix[pre][ready] == 0) {
                //完成当前课程的前驱课程（入度）+1；这个if是防止prerequisites数组有重复
                indegree[ready]++;
            }
            //用邻接矩阵描述有向图；matrix的指向：需要先上的课指向后上的课
            matrix[pre][ready] = 1;
        }
        int count = 0;
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
}
