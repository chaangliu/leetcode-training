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
public class BestTimetoBuyandSellStockIV {
    /**
     * 题意：跟前几题一样，但是这题有个要求，只能买卖k次。
     * 解法：dp[i][k][h]表示第i天，【迄今至多进行了k次交易】，h = 0：「不持有」股票的最大收益 h = 1：持有股票的最大收益。
     * k应该<= n/2，因为买入和卖出至少分两天才有意义；如果k > n/2，那就相当于可以无限次买入卖出，参考第二题。
     * dp[i][k][j] =
     * 不持有: max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i])
     * 持 有: max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i])
     */
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (k > n / 2) { // k应该<= n/2，因为买入和卖出至少分两天才有意义；如果k > n/2，那就相当于可以无限次买入卖出，参考第二题
            return maxProfit_infinity(prices);
        }
        int[][][] dp = new int[n][k + 1][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) { // 枚举k
                if (i == 0) {
                    dp[0][j][0] = 0;
                    dp[0][j][1] = -prices[0];
                } else if (j == 0) {
                    dp[i][0][0] = 0;
                    // dp[i][0][1] = -prices[0]; // 不存在这种情形
                } else {
                    dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                    dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
                }
            }
        }
        return dp[n - 1][k][0];
    }

    /**
     * 参考第二题，无限次买入卖出
     */
    public int maxProfit_infinity(int[] prices) {
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1];
        }
        return maxprofit;
    }
}
