package jianzhioffer;

import java.util.ArrayList;
import java.util.List;

/**
 * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 * 示例 1：
 * 输入：target = 9
 * 输出：[[2,3,4],[4,5]]
 * 示例 2：
 * 输入：target = 15
 * 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
 * 限制：
 * 1 <= target <= 10^5
 * 20200929
 */
public class FindContinuousSequence {
    /**
     * 题意：给你一个target，找出所有和为target的连续正整数序列。
     * 解法：sliding window（我一开始想用binarysearch其实不用），时间O(target)。另外还可以用求根公式，时间也是是O(target)
     */
    public int[][] findContinuousSequence(int target) {
        int l = 1, r = 2;
        List<List<Integer>> res = new ArrayList<>();
        while (r <= target) {
            int sum = (l + r) * (r - l + 1);
            // System.out.println("l, r, sum" + l + ", " + r + ", " + sum);
            if (sum == 2 * target) {
                List<Integer> item = new ArrayList<>();
                if (r - l > 0) {
                    for (int i = l; i <= r; i++) {
                        item.add(i);
                    }
                    res.add(item);
                }
                l++;
            } else if (sum < 2 * target) {
                r++;
            } else {
                l++;
            }
        }
//        System.out.println(res);
        int[][] ret = new int[res.size()][];
        int idx = 0;
        for (List<Integer> item : res) {
            int[] it = new int[item.size()];
            for (int i = 0; i < it.length; i++) it[i] = item.get(i);
            ret[idx++] = it;
        }
        return ret;
    }
}
