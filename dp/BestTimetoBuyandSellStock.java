package dp;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * <p>
 * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 * Example 1:
 * Input: [7, 1, 5, 3, 6, 4]
 * Output: 5
 * <p>
 * max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
 * Example 2:
 * Input: [7, 6, 4, 3, 1]
 * Output: 0
 * <p>
 * In this case, no transaction is done, i.e. max profit = 0.
 * <p>
 * Created by DrunkPiano on 2017/4/8.
 * 20200130 review
 */

public class BestTimetoBuyandSellStock {
    /**
     * 题意：给你一串股票价格，问你买入一次和卖出一次的最大收益。
     * 解法：假设当天一定要卖，维护当前最大收益和全局最大收益。
     * 类似题目：maximum subarray
     */
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE, res = 0;
        for (int i : prices) {
            min = Math.min(min, i);
            res = Math.max(res, i - min);
        }
        return res;
    }
}
