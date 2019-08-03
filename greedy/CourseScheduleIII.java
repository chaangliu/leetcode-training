package greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * There are n different online courses numbered from 1 to n. Each course has some duration(course length) t and closed on dth day. A course should be taken continuously for t days and must be finished before or on the dth day. You will start at the 1st day.
 * <p>
 * Given n online courses represented by pairs (t,d), your task is to find the maximal number of courses that can be taken.
 * <p>
 * Example:
 * <p>
 * Input: [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
 * Output: 3
 * Explanation:
 * There're totally 4 courses, but you can take 3 courses at most:
 * First, take the 1st course, it costs 100 days so you will finish it on the 100th day, and ready to take the next course on the 101st day.
 * Second, take the 3rd course, it costs 1000 days so you will finish it on the 1100th day, and ready to take the next course on the 1101st day.
 * Third, take the 2nd course, it costs 200 days so you will finish it on the 1300th day.
 * The 4th course cannot be taken now, since you will finish it on the 3300th day, which exceeds the closed date.
 * Note:
 * <p>
 * The integer 1 <= d, t, n <= 10,000.
 * You can't take two courses simultaneously
 * 20190802
 */
public class CourseScheduleIII {
    /**
     * 贪心解法（贪心是只考虑当前最好情况），优先上deadline早的；遇到超出的课程，优先把学过的duration最长的去掉，为后面的课争取更多时间。
     * 详细：
     * 先按照deadline结束时间从早到晚排序；遍历的时候遇到一个课程，先加入，然后判断如果时间不够学这门课的，就从已经学过的课里面挤掉一个duration最长的。
     * 参考一个人的解说：I think the heart of the problem is Once you make sure a course fits in, you can remove it any time later and the other courses you have added after would still fit. So it is always safe to remove any course in the past. Time complexity O(nlogn) and space complexity O(n) where n = number of courses.
     * 比如对于[[100, 200], [1000, 1100], [100, 1110], [100, 1120]]，
     * 加入到index == 2(100)的时候，remove之前任何一门课都会使得当前课程fit，因为减去之前最大的一个，反而加入一个最小的，而且deadline也extend了，所以总会fit。
     **/
    public int scheduleCourse(int[][] courses) {
        //Sort the courses by their deadlines (Greedy! We have to deal with courses with early deadlines first)
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        int time = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        for (int[] course : courses) {
            time += course[0];
            if (time > course[1]) {
                time -= queue.poll();
            }
        }
        return queue.size();
    }
}
