package dp;

/**
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 示例 1:
 * 输入: [3,3,5,0,0,3,1,4]
 * 输出: 6
 * 解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 *      随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 * 20200613
 */
public class BestTimetoBuyandSellStockIII {
    /**
     * 题意：跟前两题一样，但是这题有个要求，只能买卖两次。
     * 解法：模拟买卖。来自https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/solution/mai-gu-piao-de-zui-jia-shi-ji-iii-yi-kan-jiu-dong-/
     */
    public int maxProfit(int[] prices) {
        int minPrice1 = Integer.MAX_VALUE;
        int maxProfit1 = 0;
        int maxProfitAfterBuy = Integer.MIN_VALUE;
        int maxProfit2 = 0;
        for (int p : prices) {
            // 第一次购买之前的最低价格
            minPrice1 = Math.min(minPrice1, p);
            // 第一次卖出的最大收益
            maxProfit1 = Math.max(maxProfit1, p - minPrice1);
            // 第二次购买之后剩余的最大收益
            maxProfitAfterBuy = Math.max(maxProfitAfterBuy, maxProfit1 - p);
            // 第二次卖出之后的最大收益
            maxProfit2 = Math.max(maxProfit2, maxProfitAfterBuy + p);
        }
        return maxProfit2;
    }
}
