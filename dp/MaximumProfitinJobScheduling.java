package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].
 * <p>
 * You're given the startTime , endTime and profit arrays, you need to output the maximum profit you can take such that there are no 2 jobs in the subset with overlapping time range.
 * <p>
 * If you choose a job that ends at time X you will be able to start another job that starts at time X.
 * Example 1:
 * Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
 * Output: 120
 * Explanation: The subset chosen is the first and fourth job.
 * Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
 * Example 2:
 * Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
 * Output: 150
 * Explanation: The subset chosen is the first, fourth and fifth job.
 * Profit obtained 150 = 20 + 70 + 60.
 * Example 3:
 * Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
 * Output: 6
 * Constraints:
 * 1 <= startTime.length == endTime.length == profit.length <= 5 * 10^4
 * 1 <= startTime[i] < endTime[i] <= 10^9
 * 1 <= profit[i] <= 10^4
 * 20191024
 */
public class MaximumProfitinJobScheduling {
    /**
     * 题意：给你一些job的开始时间、结束时间和收益，问做这些工作的最大收益是多少。
     * 思路，DP+二分，二分的意义在于快速寻找当前job的s之前的最大收益。先sort by end_time，因为我们的dp以end_time为基准，类似646. Maximum Length of Pair Chain的贪心做法。
     * dpProfit存储[0, end_time]区间内能获取的最大收益，其中end_time存放在dpEndTime数组的相同index上
     * 转移方程：dp[end_time] = max(dp[prev_end] + cur, dp[cur_end])，也就是判断是否做当前工作
     */
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int[][] items = new int[startTime.length][3];// sort by endTime
        for (int i = 0; i < startTime.length; i++) {
            items[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(items, (a1, a2) -> a1[1] - a2[1]);
        List<Integer> dpEndTime = new ArrayList<>();
        List<Integer> dpProfit = new ArrayList<>();//dpProfit存储[0, end_time]区间内能获取的最大收益，其中end_time存放在dpEndTime数组的相同index上
        dpEndTime.add(0);// init value to avoid IndexOutBoundExp
        dpProfit.add(0);
        for (int[] item : items) {
            int s = item[0], e = item[1], p = item[2];
            int prevIdx = Collections.binarySearch(dpEndTime, s);
            if (prevIdx < 0) {
                prevIdx = -prevIdx - 1;//-(-insertion_point - 1) - 1 = insertion_point
                prevIdx--;//如果没找到恰好当前s == 上一个e的前一个job，返回insertion_point - 1的位置就是当前job开始之前的最晚截止时间
            }
            int currProfit = dpProfit.get(prevIdx) + p;//选择当前的job
            int maxProfit = dpProfit.get(dpProfit.size() - 1);//不选择当前的job
            if (currProfit > maxProfit) {
                dpProfit.add(currProfit);
                dpEndTime.add(e);
            }
        }
        return dpProfit.get(dpProfit.size() - 1);
    }
}
