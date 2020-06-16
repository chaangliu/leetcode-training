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

public class BestTimetoBuyandSellStockII {

    /**
     * 题意：给你一串股价，你可以交易任意次，前提是买新的之前手上没有其他股票。问最大收益。
     * 官方题解III，也是solutions的高票解法。用[1, 7, 2, 3, 6, 7, 6, 7]可以看出道理。
     * 比如2-3的那天，3的那天我卖掉，就可以获利1，可以理解为3那天我卖掉了但是同时又买回来了
     * （因为根据上帝视角知道后一天是6, 也就是我们可以在发现当天比前一天贵的时候就模拟昨天买入今天卖出；当然现实生活不能如此，因为你不知道明天是否比今天贵）。
     */
    public int maxProfit(int[] prices) {
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1];
        }
        return maxprofit;
    }

    /**
     * 以下是解决股票问题模板那个回答里面提到的通用做法(https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/solution/yi-ge-fang-fa-tuan-mie-6-dao-gu-piao-wen-ti-by-lab/)；
     * 0代表当天结束不持有股票，1代表持有股票，转移方程：
     * dp[i][0] = max(dp[i - 1][0], dp[i - 1][1] + prices[i])//不持有==卖掉，所以+price[i]
     * dp[i][1] = max(dp[i - 1][1], dp[i - 1][0] - prices[i])//持有==买回，所以-price[i]
     * 也可以看看第IV题，任意k次的模板。多加了一个维度。
     */

    public int maxProfit__(int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0];
    }

    /**
     * 上面的2D由于i只依赖i-1状态，可以进而转1D滚动数组:
     */
    public int maxProfit_(int[] prices) {
        int T_ik0 = 0, T_ik1 = Integer.MIN_VALUE;

        for (int price : prices) {
            int T_ik0_old = T_ik0;
            T_ik0 = Math.max(T_ik0, T_ik1 + price);
            T_ik1 = Math.max(T_ik1, T_ik0_old - price);
        }

        return T_ik0;
    }

    public static void main(String args[]) {
        BestTimetoBuyandSellStockII instance = new BestTimetoBuyandSellStockII();
//        int[] nums = {7, 1, 5, 3, 6, 4};
        int[] nums = {1, 2, 4};
//        int[] nums = {7, 6, 4, 3, 1};
//        System.out.println(instance.maxProfit(nums));
    }
}
