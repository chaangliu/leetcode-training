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
 */

public class BestTimetoBuyandSellStock {

//    public int maxProfit(int[] prices) {
//        int res = 0;
//        for (int i = 1; i < prices.length; i++)
//            for (int j = 0; j < i; j++) {
//                res = prices[i] - prices[j] > res ? prices[i] - prices[j] : res;
//            }
//        return res ;
//    }

//    public int maxProfit(int[] prices) {
//        if (prices.length < 2) return 0;
//        if (prices.length == 2) return prices[1] - prices[0] > 0 ? prices[1] - prices[0]:0 ;
//        int dp[] = new int[prices.length];
//        // dp[i+1] = dp[i] +  prices[i+1] - prices[i]
//        dp[0] = 0;
//        int maxProfit = prices[1] - prices[0] > 0 ? prices[1] - prices[0] : 0;
//        int bestDaysPrice = prices[1];
//        for (int i = 2; i < prices.length; i++) {
//            //如果当天的price比取得最大收益那天的price还要高
//            if (prices[i] > bestDaysPrice) {
//                maxProfit = maxProfit + prices[i] - bestDaysPrice;
//                bestDaysPrice = prices[i];
//            }
//        }
//        return maxProfit;
//    }


    public int maxProfit(int[] prices) {
        if (prices.length < 2) return 0;
        //当天卖出的最大profit
        int local = 0;
        int global = 0;
        for (int i = 1; i < prices.length; i++) {
            //今天必须要卖呀
            local = Math.max(local + prices[i] - prices[i - 1], 0);
            global = Math.max(global, local);
        }
        return global;
    }


    public static void main(String args[]) {
        BestTimetoBuyandSellStock instance = new BestTimetoBuyandSellStock();
//        int[] nums = {7, 1, 5, 3, 6, 4};
        int[] nums = {1, 2, 4};
//        int[] nums = {7, 6, 4, 3, 1};
        System.out.println(instance.maxProfit(nums));
    }
}
