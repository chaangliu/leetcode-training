package greedy;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Given a list of intervals, remove all intervals that are covered by another interval in the list. Interval [a,b) is covered by interval [c,d) if and only if c <= a and b <= d.
 * After doing so, return the number of remaining intervals.
 * Example 1:
 * Input: intervals = [[1,4],[3,6],[2,8]]
 * Output: 2
 * Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.
 * Constraints:
 * 1 <= intervals.length <= 1000
 * 0 <= intervals[i][0] < intervals[i][1] <= 10^5
 * intervals[i] != intervals[j] for all i != j
 * 20191215
 */
public class RemoveCoveredIntervals {
    /**
     * 题意：remove掉intervals里面被其他intervals覆盖的interval，返回剩下的interval的数量。
     * 双周赛第二题。感觉这种interval的题目技巧性挺强，不过都是要先sort，后面服么操作就千奇百怪了。
     * 这次我看到数据范围不大，就试着用了一下暴力，也夹杂着一点贪心吧，AC了。
     * 一个隐含条件，如果a包含b，b包含c，那么a一定包含c。
     */
    public int removeCoveredIntervals(int[][] intervals) {
        HashSet<Integer> rm = new HashSet<>();
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        for (int i = 0; i < intervals.length; i++) {
            if (rm.contains(i)) continue;
            int s1 = intervals[i][0], e1 = intervals[i][1];
            for (int j = i + 1; j < intervals.length; j++) {
                int s2 = intervals[j][0], e2 = intervals[j][1];
                if (s2 >= e1) break;
                if (s1 <= s2 && e2 <= e1) rm.add(j);
            }
        }
        return intervals.length - rm.size();
    }

    /**
     * lee的答案
     */
    public int removeCoveredIntervals__(int[][] A) {
        int res = 0, right = 0;
        Arrays.sort(A, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
        for (int[] v : A) {
            if (v[1] > right) ++res;
            right = Math.max(right, v[1]);
        }
        return res;
    }
}
