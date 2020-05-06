package dp;

import java.util.HashSet;
import java.util.Set;

/**
 * In a country popular for train travel, you have planned some train travelling one year in advance.  The days of the year that you will travel is given as an array days.  Each day is an integer from 1 to 365.
 * <p>
 * Train tickets are sold in 3 different ways:
 * <p>
 * a 1-day pass is sold for costs[0] dollars;
 * a 7-day pass is sold for costs[1] dollars;
 * a 30-day pass is sold for costs[2] dollars.
 * The passes allow that many days of consecutive travel.  For example, if we get a 7-day pass on day 2, then we can travel for 7 days: day 2, 3, 4, 5, 6, 7, and 8.
 * <p>
 * Return the minimum number of dollars you need to travel every day in the given list of days.
 * Example 1:
 * <p>
 * Input: days = [1,4,6,7,8,20], costs = [2,7,15]
 * Output: 11
 * Explanation:
 * For example, here is one way to buy passes that lets you travel your travel plan:
 * On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
 * On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
 * On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
 * In total you spent $11 and covered all the days of your travel.
 * Example 2:
 * <p>
 * Input: days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
 * Output: 17
 * Explanation:
 * For example, here is one way to buy passes that lets you travel your travel plan:
 * On day 1, you bought a 30-day pass for costs[2] = $15 which covered days 1, 2, ..., 30.
 * On day 31, you bought a 1-day pass for costs[0] = $2 which covered day 31.
 * In total you spent $17 and covered all the days of your travel.
 * Note:
 * 1 <= days.length <= 365
 * 1 <= days[i] <= 365
 * days is in strictly increasing order.
 * costs.length == 3
 * 1 <= costs[i] <= 1000
 * 20190620
 */
public class MinimumCostForTickets {
    /**
     * 题意：选择最划算的交通卡。
     * 解法：这种题显然是top down的DFS + MEMO比较好写；但是我想了一会儿没想出来，一开始想以日期为维度，但是发现构造不出days的递推关系；然后以index为维度，写了半天WA了。于是看了答案。
     * top down, solution 1，以index为维度，这里也是我自己写的，当时其实WA的是个小问题，就是>写成了>=
     */
    public int mincostTickets__(int[] days, int[] costs) {
        return dfs(days, costs, 0, new Integer[days.length]);
    }

    private int dfs(int[] days, int[] costs, int d, Integer[] memo) {
        if (d >= days.length) return 0; // >= length, not length - 1
        if (memo[d] != null) return memo[d];
        int res = Integer.MAX_VALUE;
        int x = d, y = d, z = d;
        for (int i = d + 1; i < days.length; i++) { // 对每种票价分别找到cover不到的那一天
            if (days[d] + 1 > days[i]) x = i;// 不要写成>=
            if (days[d] + 7 > days[i]) y = i;
            if (days[d] + 30 > days[i]) z = i;
            else break;
        }
        res = Math.min(res, costs[0] + dfs(days, costs, x + 1, memo));
        res = Math.min(res, costs[1] + dfs(days, costs, y + 1, memo));
        res = Math.min(res, costs[2] + dfs(days, costs, z + 1, memo));
        memo[d] = res;
        return res;
    }

    /**
     * * top down, solution 2, 以日期为维度；它的关键点在于一个贪心准则，就是我们应该在出行当天再买；如果没到那天，就等着。
     */
    public int mincostTickets(int[] days, int[] costs) {
        HashSet<Integer> set = new HashSet<>();
        for (int i : days) set.add(i);
        return dfs(set, costs, 1, new Integer[366]);
    }

    private int dfs(HashSet<Integer> days, int[] costs, int d, Integer[] memo) {
        if (d > 365) return 0;
        if (memo[d] != null) return memo[d];
        if (!days.contains(d)) return dfs(days, costs, d + 1, memo);
        int res = Math.min(Math.min(costs[0] + dfs(days, costs, d + 1, memo), costs[1] + dfs(days, costs, d + 7, memo)), costs[2] + dfs(days, costs, d + 30, memo));
        memo[d] = res;
        return res;
    }


    /**
     * bottom up dp: 以日历的日期为维度，假设今天交通卡到期，那计算上次买了1天/7天/30天的话最少需要钱
     * 这种方案的优化可以用滚动数组，只保留30天前的最小花销
     */
    public int mincostTickets_bottom_up(int[] days, int[] costs) {
        int dp[] = new int[366];//dp[i]代表截至第i天的min cost；optimization：可用长度30的滚动数组
        Set<Integer> set = new HashSet<>();
        for (int i : days) set.add(i);
        for (int i = 1; i < days.length; i++) {
            if (!set.contains(i)) dp[i] = dp[i - 1];
            else //dp[Math.max(0, i - 7)] + costs[1])代表i - 7天时预付了以后7天的费用，所以截至第i天需要花这么多钱
                dp[i] = Math.min(Math.min(dp[i - 1] + costs[0], dp[Math.max(0, i - 7)] + costs[1]),
                        dp[Math.max(0, i - 30)] + costs[2]);
        }
        return dp[365];
    }
}
