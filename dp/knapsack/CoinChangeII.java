package dp.knapsack;

/**
 * You are given coins of different denominations and a total amount of money. Write a function to compute the number of combinations that make up that amount. You may assume that you have infinite number of each kind of coin.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: amount = 5, coins = [1, 2, 5]
 * Output: 4
 * Explanation: there are four ways to make up the amount:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * Example 2:
 * <p>
 * Input: amount = 3, coins = [2]
 * Output: 0
 * Explanation: the amount of 3 cannot be made up just with coins of 2.
 * Example 3:
 * <p>
 * Input: amount = 10, coins = [10]
 * Output: 1
 * <p>
 * 20190129
 * 20191031
 */
public class CoinChangeII {
    /**
     * 题意：给你一些面值的硬币和一个总额，问有多少种组成总额的方式。每种硬币可以使用无限次。
     * 【推荐解法】：一维DP（不是降维）
     * 这题是cc150的08.11，leetcode英文版的coin changeii，lc高赞的答案的第一种解法并不好（占用memory太多），还是推荐用以下这种。
     * 可以理解为爬台阶，对于每个coin和每个总金额i，从i - coin那级可以再走coin级台阶就可以来到当前台阶
     * if(amount - coin >= 0) dp[amount] += dp[amount - coin]
     */
    public int change____1D(int amount, int[] coins) {
        if (amount == 0 && coins.length == 0) return 1;
        if (amount < 0 || coins == null || coins.length == 0) return 0;
        int[] dp = new int[amount + 1];
        dp[0] = 1;//如果有amount = 1, coin = 1，那dp[1] = dp[1 - 1]
        for (int coin : coins) { // 枚举面值
            for (int i = 1; i <= amount; i++) { //枚举金额
                if (i - coin >= 0) {// 如果当前的钱可以用当前硬币来找钱
                    dp[i] += dp[i - coin];// not dp[i - 1]。这里可以理解为爬台阶，从i - coin那级可以再走coin级台阶就可以来到当前台阶
                }
            }
        }
        return dp[dp.length - 1];
    }


    /**
     * 相应的dfs with memo
     */
    public int change__(int amount, int[] coins) {
        if (amount == 0 && coins.length == 0) return 1;
        if (amount < 0 || coins == null || coins.length == 0) return 0;
        Integer[][] ways = new Integer[coins.length][amount + 1];
        return dfs(amount, coins, 0, ways);
    }

    /**
     * memo[i][j]代表使用从cur开始的硬币组成amount有多少种方法
     */
    private int dfs(int amount, int[] coins, int cur, Integer[][] memo) {
        if (amount == 0) return 1;
        if (amount < 0) return 0;
        int res = 0;
        if (memo[cur][amount] != null) return memo[cur][amount];
        for (int i = cur; i < coins.length; i++) {
            res += dfs(amount - coins[i], coins, i, memo);
        }
        memo[cur][amount] = res;
        return res;
    }

    /**
     * 经典背包问题, knapsack problem，DP
     * dp[i][j] : the number of combinations to make up amount j by using the first i types of coins
     */
    public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length + 1][amount + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= coins.length; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= amount; j++) {
                //dp[i - 1][j]: 完全不用当前硬币组成j有多少种组合
                //dp[i][j - coins[i - 1]]: 使用至少一个当前硬币（与上面一条是互斥事件）组成组成j有多少组合
                dp[i][j] = dp[i - 1][j] + (j >= coins[i - 1] ? dp[i][j - coins[i - 1]] : 0);
            }
        }
        return dp[coins.length][amount];
    }

    /**
     * dfs with memo
     */
    public int change_(int amount, int[] coins) {
        Integer[][] ways = new Integer[coins.length][amount + 1];
        return changeWays(amount, coins, coins.length - 1, ways);
    }

    /**
     * memo[i][j]代表使用前i种硬币组成amount有多少种方法
     */
    private int changeWays(int amount, int[] coins, int cur, Integer[][] memo) {
        if (amount == 0) return 1;
        if (cur < 0) return 0;
        if (memo[cur][amount] != null) return memo[cur][amount];
        int way1 = 0;
        if (amount >= coins[cur]) way1 = changeWays(amount - coins[cur], coins, cur, memo); // 使用至少一个当前硬币cur
        int way2 = changeWays(amount, coins, cur - 1, memo); // 完全不用当前硬币
        memo[cur][amount] = way1 + way2;
        return way1 + way2;
    }
}
