package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 * Example 1:
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * Example 2:
 * <p>
 * Input: [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 * NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 * Created by DrunkPiano on 26/05/2017.
 * 20200115 --review
 */

public class MergeIntervals {
    /**
     * 题意：合并重合的intervals，输出合并后的interval。
     * 解法：注意这种题目一般都是sort，要么按照start要么按照end sort，然后greedy。NonoverlappingIntervals那题是典型的按照end sort。
     */
    public int[][] merge(int[][] intervals) {
        List<List<Integer>> list = new ArrayList<>();
        if (intervals.length == 0) return new int[0][0];
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int n = intervals.length, start = intervals[0][0], end = intervals[0][1];
        for (int i = 1; i < n; i++) {
            int[] iv = intervals[i];
            if (iv[0] <= end) { //当前和前一对overlap
                end = Math.max(iv[1], end);// 已犯错误：忘记math.max，也就是fail case [1,4][2,3]
            } else {
                list.add(Arrays.asList(start, end)); //当前和前一对non-overlap，添加前一对，更新start,end
                start = iv[0];
                end = iv[1];
            }
        }
        list.add(Arrays.asList(start, end));// last interval
        int[][] res = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) res[i] = new int[]{list.get(i).get(0), list.get(i).get(1)};
        return res;
    }


    /**
     * 一开始的想法，这种题目我喜欢类似计算器/parenthesis那样把所有node撸下来排序，把start加入到stack里处理。
     * 时间是O(2n)，比较慢，用到了额外空间。
     */
    public int[][] merge__STACK(int[][] intervals) {
        List<List<Integer>> res = new ArrayList<>();
        List<Pair> list = new ArrayList<>();
        for (int[] i : intervals) {
            list.add(new Pair(i[0], 0));
            list.add(new Pair(i[1], 1));
        }
        Collections.sort(list, (a, b) -> a.val == b.val ? a.isStart - b.isStart : a.val - b.val);
        Stack<Pair> stack = new Stack<>();
        for (Pair p : list) {
            if (stack.empty() || p.isStart == 0) {
                stack.push(p);
            } else {// p.isEnd
                if (stack.size() > 1) {
                    stack.pop();
                } else if (stack.size() == 1) {
                    int s = stack.pop().val;
                    int e = p.val;
                    res.add(Arrays.asList(s, e));
                }
            }

        }
        int[][] ret = new int[res.size()][2];
        for (int i = 0; i < res.size(); i++) ret[i] = new int[]{res.get(i).get(0), res.get(i).get(1)};
        return ret;
    }

    class Pair {
        int val;
        int isStart;// 0 true, 1 false, 如果直接用boolean，不好compare

        Pair(int n, int s) {
            val = n;
            isStart = s;
        }
    }


    /**
     * 摘抄一个写法，用List<int[]>，最后可以直接toArray
     */
    public int[][] merge_(int[][] intervals) {
        if (intervals.length <= 1)
            return intervals;

        // Sort by ascending starting point
        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));

        List<int[]> result = new ArrayList<>();
        int[] newInterval = intervals[0];
        result.add(newInterval);
        for (int[] interval : intervals) {
            if (interval[0] <= newInterval[1]) // Overlapping intervals, move the end if needed
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            else {                             // Disjoint intervals, add the new interval to the list
                newInterval = interval;
                result.add(newInterval);
            }
        }

        return result.toArray(new int[result.size()][]);
    }

    /**
     * 20200416 review
     */
    public int[][] merge__(int[][] intervals) {
        if (intervals.length == 0) return intervals;
        List<int[]> res = new ArrayList<>();
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int s = intervals[0][0], e = intervals[0][1];
        for (int[] arr : intervals) {
            if (arr[0] <= e) {
                e = Math.max(e, arr[1]); //[1, 4][2, 3]
            } else {
                res.add(new int[]{s, e});
                s = arr[0];
                e = Math.max(e, arr[1]);
            }
        }
        res.add(new int[]{s, e});
        int[][] result = new int[res.size()][2];
        for (int i = 0; i < res.size(); i++) result[i] = res.get(i);
        return result;
    }
}
