package greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import basics.Interval;

/**
 * Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 * Example 1:
 * Input: [[1,2],[2,3],[3,4],[1,3]]
 * Output: 1
 * Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
 * Example 2:
 * <p>
 * Input: [[1,2],[1,2],[1,2]]
 * Output: 2
 * Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
 * Example 3:
 * <p>
 * Input: [[1,2],[2,3]]
 * Output: 0
 * Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 * <p>
 * <p>
 * Note:
 * <p>
 * You may assume the interval's end point is always bigger than its start point.
 * Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.
 * <p>
 * 56 Merge Intervals <- very similar, i did it with just 3 lines different
 * 252 Meeting Rooms
 * 253 Meeting Rooms II
 * 452 Minimum Number of Arrows to Burst Balloons
 * <p>
 * Practice them in a row for better understanding 😉
 */
public class NonoverlappingIntervals {
    /**
     * 这题是说，在一个数组里移除最小个数的区间，让剩下的区间都不重叠
     * 我在纸上画了很多情况试图找规律，最后找到一个规律: 在一个区间内如果包含了其它区间的结尾，就把当前区间remove掉。
     * 【我的解法】
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o2[1] - o1[1]);
        List<Pair> list = new ArrayList<>();
        int res = 0;
        for (int i = 0; i < intervals.length; i++) {
            list.add(new Pair(i, intervals[i][0], intervals[i][1]));
        }
        Iterator<Pair> iterator = list.iterator();
        while (iterator.hasNext()) {//for(Pair p : list)会产生“ConcurrentModificationException”，因为在iterate的时候remove了。所以改用iterator。
            Pair p = iterator.next();
            for (Pair q : list) {
                boolean flag = false;
                if (p.id == q.id) continue;
                for (int i = p.start; i <= p.end; i++) {
                    if (q.end > p.start && q.end <= p.end) {
                        iterator.remove();
                        res++;
                        flag = true;
                        break;
                    }
                }
                if (flag)
                    break;
            }
        }
        return res;
    }

    class Pair {
        int id;
        int start;
        int end;

        Pair(int id, int start, int end) {
            this.id = id;
            this.start = start;
            this.end = end;
        }
    }


    /**
     * 【官方解法】经典的interval scheduling问题，贪心做法，按照end排序，顺序地统计没有overlap的interval个数（https://en.wikipedia.org/wiki/Interval_scheduling#Interval_Scheduling_Maximization）
     * Actually, the problem is the same as "Given a collection of intervals, find the maximum number of intervals that are non-overlapping."
     * (the classic Greedy problem: Interval Scheduling). With the solution to that problem, guess how do we get the minimum number of intervals to remove? : )
     * Sorting Interval.end in ascending order is O(nlogn), then traverse intervals array to get the maximum number of non-overlapping intervals is O(n). Total is O(nlogn).
     */
    public int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, (a, b) -> a.end - b.end);
        int end = intervals[0].end;
        int count = 1;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start >= end) {
                end = intervals[i].end;
                count++;//统计不重合的个数
            }
        }
        return intervals.length - count;
    }
}
