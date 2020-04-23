package dp.dfswithmemo;

import java.util.Arrays;

/**
 * You are given coins of different denominations and a total amount of money amount.
 * Write a function to compute the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 * You may assume that you have an infinite number of each kind of coin.
 * Example 1:
 * Input: coins = [1, 2, 5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * Example 2:
 * Input: coins = [2], amount = 3
 * Output: -1
 * 20191031
 */
public class CoinChange {

    /**
     * 题意：给你一些不同硬币的面值(每种面值可以用无数次)，一个总数，问你最少用多少个硬币可以达到总数。
     * 方法1，dfs with memo
     */
    public int coinChange_(int[] coins, int amount) {
        int res = dfs(coins, amount, new Integer[amount + 1]);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    /**
     * 返回组成amount数量最少需要多少硬币；有些情况需要特别注意，比如[2] 3这样的case
     **/
    private int dfs(int[] coins, int amount, Integer[] memo) {
        if (amount == 0) return 0;
        if (memo[amount] != null) return memo[amount];
        int res = Integer.MAX_VALUE;
        for (int c : coins) {
            if (amount - c < 0) continue;
            int r = dfs(coins, amount - c, memo);
            if (r != -1) res = Math.min(res, 1 + r);
        }
        memo[amount] = res == Integer.MAX_VALUE ? -1 : res; // 如果不能刚好用完amount，返回-1
        return memo[amount];
    }

    /**
     * 方法2. dp, bottom up
     * dp[i]表示金额i时的最小硬币数。
     * dp[i] = min(dp[i], dp[i - coins[j]] + 1);
     */
    public int coinChange___(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount < 0) return -1;
        if (amount == 0) return 0;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE - 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++)
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] >= 0)
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);//这里是滚动数组，二维转一维；另外这里隐含了一个条件，如果已经计算出dp[i - coins[j]]的话才加以利用
            }
        return dp[amount] == Integer.MAX_VALUE - 1 ? -1 : dp[amount];
    }


    /**
     * * 方法3. dfs + pruning， AC
     * * 逆序排列，然后从大额硬币开始用，暴力搜。
     */
    int res = Integer.MAX_VALUE;

    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount < 0) return -1;
        //一定要先逆序排列，不然TLE。Java的Arrays.sort()不支持int[]传入comparator,所以手动翻转。
        Arrays.sort(coins);
        for (int i = 0, j = coins.length - 1; i < j; i++, j--) {
            int tmp = coins[j];
            coins[j] = coins[i];
            coins[i] = tmp;
        }
        helper(0, coins, 0, amount);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private void helper(int index, int[] coins, int curCount, int remain) {
        if (index == coins.length - 1) {
            if (remain % coins[index] == 0) {//这个%有两种功能，1代表remain可能已经是0了，2代表remain是最后一枚硬币的倍数
                res = Math.min(res, remain / coins[index] + curCount);
            }
        } else {
            for (int k = remain / coins[index]; k >= 0 && k + curCount < res; k--) {//从大的硬币开始用，这样可以把res尽快变小，以便pruning
                helper(index + 1, coins, curCount + k, remain - k * coins[index]);
            }
        }
    }
}
