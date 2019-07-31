package graph.toplogicalsort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * There are N courses, labelled from 1 to N.
 * We are given relations[i] = [X, Y], representing a prerequisite relationship between course X and course Y: course X has to be studied before course Y.
 * In one semester you can study any number of courses as long as you have studied all the prerequisites for the course you are studying.
 * Return the minimum number of semesters needed to study all courses.  If there is no way to study all the courses, return -1.
 * Example 1:
 * Input: N = 3, relations = [[1,3],[2,3]]
 * Output: 2
 * Explanation:
 * In the first semester, courses 1 and 2 are studied. In the second semester, course 3 is studied.
 * Example 2:
 * Input: N = 3, relations = [[1,2],[2,3],[3,1]]
 * Output: -1
 * Explanation:
 * No course can be studied because they depend on each other.
 * Note:
 * 1 <= N <= 5000
 * 1 <= relations.length <= 5000
 * relations[i][0] != relations[i][1]
 * There are no repeated relations in the input.
 * 20190731
 */
public class ParallelCourses {
    /**
     * 这题跟course schedule一样，区别是怎么判断多少学期？我一开始感觉是类似判断BFS有多少层，但不对。看了b站视频发现别人又是不用思考。。
     * 直接维护一个数组记录每个课程的完成学期；找到后续node的时候，后续node的学习的学期 = 它所有前驱课程里面最晚的那个的下一学期
     */
    public int minimumSemesters(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0 || prerequisites[0].length == 0) return 0;
        ArrayList[] adjacentList = new ArrayList[numCourses];
        int[] inDegrees = new int[numCourses];//记录每个node的inDegree
        for (int[] relation : prerequisites) {
            int cur = relation[0] - 1;//当前课程
            int pre = relation[1] - 1;//当前课程的前驱课程
            inDegrees[cur]++;
            if (adjacentList[pre] == null) adjacentList[pre] = new ArrayList();
            adjacentList[pre].add(cur);
        }
        int count = 0;
        int[] ans = new int[numCourses];//记录每个课程的完成学期
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++)
            if (inDegrees[i] == 0) {
                queue.offer(i);
                ans[i] = 1;//没有pre的，放在第1学期学
            }
        while (!queue.isEmpty()) {
            int curCourse = queue.poll();
            count++;
            ArrayList adj = adjacentList[curCourse];
            if (adj == null) continue;
            for (Object nextCourse : adj) {
                ans[(int) nextCourse] = Math.max(ans[(int) nextCourse], ans[curCourse] + 1);//后续课程至少要在当前课程的下一学期学，并且是所有前驱课程里面最晚的那个的下一学期
                if (--inDegrees[(int) nextCourse] == 0) {
                    queue.offer((int) nextCourse);
                }
            }
        }
        int res = 0;
        for (int num : ans) res = Math.max(res, num);//学完最后一门课需要的最晚时间
        return count != numCourses ? -1 : res;
    }
}
