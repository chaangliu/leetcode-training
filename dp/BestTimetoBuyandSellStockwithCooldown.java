package dp;

/**
 * 在持有   buy[i] =  max(sell[i-2] - price[i], buy[i-1])
 * 不在持有 sell[i] =  max(buy[i-1] + price[i],sell[i-1])
 * Created by DrunkPiano on 08/06/2017.
 */

public class BestTimetoBuyandSellStockwithCooldown {
	public int maxProfit(int[] prices) {
		if (prices.length < 2) return 0;
		int[] buy = new int[prices.length];
		int[] sell = new int[prices.length];
		buy[0] = -prices[0];
		buy[1] = Math.max(-prices[0] , -prices[1]);
		sell[0] = 0;
		sell[1] = Math.max(prices[1] - prices[0] , 0 );
		for (int i = 2; i < prices.length; i++) {
			sell[i] = Math.max(buy[i - 1] + prices[i], sell[i - 1]);
			buy[i] = Math.max(sell[i - 2] - prices[i], buy[i - 1]);
		}
		return sell[sell.length - 1];
	}
}

//class Solution {
//	public :
//
//	int maxProfit(vector<int>&prices) {
//		if (prices.size() < 2) return 0;
//		int n = prices.size();
//		vector<int> sell (n);
//		vector<int> buy (n);
//		sell[0] = 0;
//		sell[-1] = 0;
//		buy[0] = -prices[0];//因为第0天买入，收益为负的
//		//
//		for (int i = 1; i < prices.size(); ++i) {
//			sell[i] = max(sell[i - 1], (buy[i - 1] + prices[i]));
//			buy[i] = max(buy[i - 1], (sell[i - 2] - prices[i]));
//		}
//		return sell[prices.size() - 1];
//	}
//};