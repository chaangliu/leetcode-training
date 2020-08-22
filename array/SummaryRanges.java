package array;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个无重复元素的有序整数数组，返回数组区间范围的汇总。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [0,1,2,4,5,7]
 * 输出: ["0->2","4->5","7"]
 * 解释: 0,1,2 可组成一个连续的区间; 4,5 可组成一个连续的区间。
 * 示例 2:
 * <p>
 * 输入: [0,2,3,4,6,8,9]
 * 输出: ["0","2->4","6","8->9"]
 * 解释: 2,3,4 可组成一个连续的区间; 8,9 可组成一个连续的区间。
 */
public class SummaryRanges {
    /**
     * 题意：给你一个sorted integer array，让你把区间汇总成start -> end的样式。
     * 解法：模拟即可。corner case是最后一个区间不要忘掉处理。
     */
    public List<String> summaryRanges(int[] A) {
        List<String> res = new ArrayList<>();
        if (A.length == 0) return res;
        int start = 0, i = 1;
        for (; i < A.length; i++) {
            if (A[i] - A[i - 1] != 1) {
                String item = i - 1 == start ? "" + A[i - 1] : A[start] + "->" + A[i - 1];
                res.add(item);
                start = i;
            }
        }
        String item = i - 1 == start ? "" + A[i - 1] : A[start] + "->" + A[i - 1];
        res.add(item);
        return res;
    }
}
