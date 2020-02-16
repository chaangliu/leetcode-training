package greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Given an array of events where events[i] = [startDayi, endDayi]. Every event i starts at startDayi and ends at endDayi.
 * You can attend an event i at any day d where startTimei <= d <= endTimei. Notice that you can only attend one event at any time d.
 * Return the maximum number of events you can attend.
 * Example 1:
 * Input: events = [[1,2],[2,3],[3,4]]
 * Output: 3
 * Explanation: You can attend all the three events.
 * One way to attend them all is as shown.
 * Attend the first event on day 1.
 * Attend the second event on day 2.
 * Attend the third event on day 3.
 * Example 2:
 * <p>
 * Input: events= [[1,2],[2,3],[3,4],[1,2]]
 * Output: 4
 * Example 3:
 * <p>
 * Input: events = [[1,4],[4,4],[2,2],[3,4],[1,1]]
 * Output: 4
 * Example 4:
 * <p>
 * Input: events = [[1,100000]]
 * Output: 1
 * Example 5:
 * <p>
 * Input: events = [[1,1],[1,2],[1,3],[1,4],[1,5],[1,6],[1,7]]
 * Output: 7
 * Constraints:
 * 1 <= events.length <= 10^5
 * events[i].length == 2
 * 1 <= events[i][0] <= events[i][1] <= 10^5
 * 20200116
 */
public class MaximumNumberofEventsThatCanBeAttended {
    /**
     * 题意：给你一些events的开始/结束时间，同一天只能参加一个活动，问最多能参加多少活动
     * 解法：贪心地先attend结束时间早的event。
     **/
    public int maxEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> q = new PriorityQueue<>();// Priority queue pq keeps the current open meeting.
        int res = 0, eventId = 0;
        for (int d = 1; d <= 100000; d++) {
            while (eventId < events.length && d == events[eventId][0]) {
                q.offer(events[eventId++][1]);// 把d当天开始的活动加入队列
                //eventId++;
            }
            while (!q.isEmpty() && q.peek() < d) q.poll();// 把d当天已经结束的events去掉
            if (!q.isEmpty()) {
                res++;
                q.poll();
            }
        }
        return res;
    }
}
