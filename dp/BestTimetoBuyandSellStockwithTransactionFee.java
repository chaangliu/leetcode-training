package dp;

/**
 * Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i; and a non-negative integer fee representing a transaction fee.
 * <p>
 * You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction. You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)
 * <p>
 * Return the maximum profit you can make.
 * <p>
 * Example 1:
 * Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
 * Output: 8
 * Explanation: The maximum profit can be achieved by:
 * Buying at prices[0] = 1
 * Selling at prices[3] = 8
 * Buying at prices[4] = 4
 * Selling at prices[5] = 9
 * The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 * Note:
 * <p>
 * 0 < prices.length <= 50000.
 * 0 < prices[i] < 50000.
 * 0 <= fee < 50000.
 * 20190831
 */
public class BestTimetoBuyandSellStockwithTransactionFee {
    /**
     * 参考股票问题的通用解法写出来的(https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/solution/yi-ge-fang-fa-tuan-mie-6-dao-gu-piao-wen-ti-by-lab/)
     * 那个模板真不错，我感觉没有模板很难归纳出来，遇到新的就歇逼，或者当天看懂了过几天就歇逼
     * 这题代码实现上和BestTimetoBuyandSellStockII的唯一区别就是需要在卖出去的时候减去一个fee。
     * 0代表当天结束不持有股票，1代表持有股票，转移方程：
     * dp[i][0] = max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee)
     * dp[i][1] = max(dp[i - 1][1], dp[i - 1][0] - prices[i])
     * 上面链接里的通用转移方程确实很不错，但是他对于可以进行无限次交易的时候k为什么可以省略解释得很confusing，
     * 说因为infinity的情况下k == k - 1；我觉得这么理解，因为与k无关，所以无需k这个维度，当然这也是naive的思维，真正的证明可能还是跟折线图有关
     */
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0];
    }

    // hold[i] = max(notHold[i - 1] - A[i], hold[i - 1]) // 注意这里不要-fee(买入不需要手续费)
    // notHold[i] = max(notHold[i - 1], hold[i - 1] + A[i] - fee)
    public int maxProfit__(int[] A, int fee) {
        int res = 0;
        int[] hold = new int[A.length], notHold = new int[A.length];
        hold[0] = -A[0];
        notHold[0] = 0;
        for (int i = 1; i < A.length; i++) {
            hold[i] = Math.max(notHold[i - 1] - A[i], hold[i - 1]);
            notHold[i] = Math.max(notHold[i - 1], hold[i - 1] + A[i] - fee);
            res = Math.max(res, notHold[i]);
        }
        return res;
    }

    /**
     * ==>滚动数组
     */
    public int maxProfit_(int[] prices, int fee) {
        int n = prices.length;
        if (n == 0) return 0;
        int notHold = 0, hold = -prices[0];
        for (int i = 1; i < n; i++) {
            int tmp = notHold;
            notHold = Math.max(tmp, hold + prices[i] - fee);
            hold = Math.max(hold, tmp - prices[i]);
        }
        return notHold;
    }

    /**
     【附上模板】
     * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     max(   选择 rest  ,           选择 sell      )

     解释：今天我没有持有股票，有两种可能：
     要么是我昨天就没有持有，然后今天选择 rest，所以我今天还是没有持有；
     要么是我昨天持有股票，但是今天我 sell 了，所以我今天没有持有股票了。

     dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     max(   选择 rest  ,           选择 buy         )

     解释：今天我持有着股票，有两种可能：
     要么我昨天就持有着股票，然后今天选择 rest，所以我今天还持有着股票；
     要么我昨天本没有持有，但今天我选择 buy，所以今天我就持有股票了。

     作者：labuladong
     链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/solution/yi-ge-fang-fa-tuan-mie-6-dao-gu-piao-wen-ti-by-lab/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
