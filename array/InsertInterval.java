package array;

import java.util.ArrayList;
import java.util.List;

import basics.Interval;


/**
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * Created by DrunkPiano on 21/06/2017.
 */

public class InsertInterval {
    /**
     * 题意：给你一个interval插入到一个无重叠的 ，按照区间起始端点排序的区间数组里，返回合并后的结果。
     * 解法：模拟。这题是hard，但没有用到什么奇淫巧技，就是从头遍历一遍，该合并的合并，很多人说简单；但我并没有很快写出来，写了一半放弃了，看了答案。
     * 我觉得难的地方在于重叠时候多种情况的判断，我写的时候用了前一个区间和后一个区间，答案中只用到了当前区间来判断，这一点很巧妙。
     * 最值得借鉴的地方是先对比it[1] vs left 、 it[0] vs right来判断是否和当前区间无交集。
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int left = newInterval[0];
        int right = newInterval[1];
        boolean placed = false; // newInterval是否已经插入
        List<int[]> ansList = new ArrayList<int[]>();
        for (int[] interval : intervals) {
            if (interval[1] < left) {
                // 在插入区间的左侧且无交集
                ansList.add(interval);
            } else if (interval[0] > right) {
                // 在插入区间的右侧且无交集
                if (!placed) {
                    ansList.add(new int[]{left, right});
                    placed = true;
                }
                ansList.add(interval);
            } else {
                // 与插入区间有交集，计算它们的并集
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }
        }
        if (!placed) {
            ansList.add(new int[]{left, right});
        }
        int[][] ans = new int[ansList.size()][2];
        for (int i = 0; i < ansList.size(); ++i) {
            ans[i] = ansList.get(i);
        }
        return ans;
    }
}
