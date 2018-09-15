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

public class BestTimetoBuyandSellStockII {

//    public int maxProfit(int[] prices) {
//        if (prices.length < 2) return 0;
//        for (int i = 1 ; i < prices.length ; i ++){
//
//
//        }
//
//    }


    public static void main(String args[]) {
        BestTimetoBuyandSellStockII instance = new BestTimetoBuyandSellStockII();
//        int[] nums = {7, 1, 5, 3, 6, 4};
        int[] nums = {1, 2, 4};
//        int[] nums = {7, 6, 4, 3, 1};
//        System.out.println(instance.maxProfit(nums));
    }
}
