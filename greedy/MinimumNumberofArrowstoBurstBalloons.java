package greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * There are a number of spherical balloons spread in two-dimensional space. For each balloon, provided input is the start and end coordinates of the horizontal diameter. Since it's horizontal, y-coordinates don't matter and hence the x-coordinates of start and end of the diameter suffice. Start is always smaller than end. There will be at most 104 balloons.
 * <p>
 * An arrow can be shot up exactly vertically from different points along the x-axis. A balloon with xstart and xend bursts by an arrow shot at x if xstart ≤ x ≤ xend. There is no limit to the number of arrows that can be shot. An arrow once shot keeps travelling up infinitely. The problem is to find the minimum number of arrows that must be shot to burst all balloons.
 * <p>
 * Example:
 * <p>
 * Input:
 * [[10,16], [2,8], [1,6], [7,12]]
 * <p>
 * Output:
 * 2
 * <p>
 * Explanation:
 * One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] and [1,6]) and another arrow at x = 11 (bursting the other two balloons).
 * 20190824
 * <p>
 * 56 Merge Intervals <- very similar😈
 * 435 Non-overlapping Intervals <- very similar😈
 * 252 Meeting Rooms
 * 253 Meeting Rooms II
 */
public class MinimumNumberofArrowstoBurstBalloons {
    /**
     * 题意：给你一些气球在x轴的起始结束位置，问最少需要多少枝箭可以射爆所有气球。
     * 解法：greedy，和435一样，interval schedule问题。贪心地计算最多不重合的interval，结果就是最小弓箭数量。
     * 详细地，用区间结束位置从小到大排序，然后维护一个end, 如果有气球的start在这个end右边了，那就需要一枝新的箭了，更新cnt和end。
     **/
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;
        // Arrays.sort(points, (a, b) -> (a[1] - b[1]));
        Arrays.sort(points, Comparator.comparingInt(o -> o[1]));
        int end = points[0][1];
        int cnt = 1;
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > end) {
                end = points[i][1];
                cnt++;
            }
        }
        return cnt;
    }
}
