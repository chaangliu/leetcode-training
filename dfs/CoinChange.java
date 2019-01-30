package dfs;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 * <p>
 * Example 1:
 * <p>
 * Input: coins = [1, 2, 5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * Example 2:
 * <p>
 * Input: coins = [2], amount = 3
 * Output: -1
 */
public class CoinChange {

    //我的解法，DFS，有些case通不过，比如[186,419,83,408]，6249；其实就是下方的brute force解法，但是我不太清楚自己哪里写错了为什么通不过这个case
//    int count = 0;
//    boolean found = false;
//
//    public int coinChange(int[] coins, int amount) {
//        if (coins == null || amount < 0) return -1;
//        if (amount == 0) return 0;
//        Arrays.sort(coins);
//        for (int i = coins.length - 1; i >= 0; i--) {
//            count = 0;
//            dfs(coins, i, amount);
//            if (found) return count;
//        }
//        return -1;
//    }
//
//    private void dfs(int[] coins, int index, int remaining) {
//        if (index < 0) return;
//        if (remaining - coins[index] > 0) {
//            count++;
//            dfs(coins, index, remaining - coins[index]);
//        } else if (remaining - coins[index] < 0) {
//            dfs(coins, index - 1, remaining);
//        } else {
//            count++;
//            found = true;
//        }
//    }

//    //1. brute force (dfs)
//    public int coinChange(int[] coins, int amount) {
//        return coinChange(0, coins, amount);
//    }
//
//    private int coinChange(int idxCoin, int[] coins, int amount) {
//        if (amount == 0)
//            return 0;
//        if (idxCoin < coins.length && amount > 0) {
//            int maxVal = amount / coins[idxCoin];
//            int minCost = Integer.MAX_VALUE;
//            for (int x = 0; x <= maxVal; x++) {
//                if (amount >= x * coins[idxCoin]) {
//                    int res = coinChange(idxCoin + 1, coins, amount - x * coins[idxCoin]);
//                    if (res != -1)
//                        minCost = Math.min(minCost, res + x);
//                }
//            }
//            return (minCost == Integer.MAX_VALUE) ? -1 : minCost;
//        }
//        return -1;
//    }

    //1.5. backtracking(dfs) + pruning， AC
    int res = Integer.MAX_VALUE;

    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount < 0) return -1;
        //一定要先逆序排列，不然TLE。这里我实在不知道怎么用java来逆序array..所以先正序然后手动反转
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
        //无需
        //        if (remain == 0) {
        //            res = Math.min(res, curCount);
        //            return;
        //        }
        //这个backtracking跟permutations，subsets, combination sum之类的一样，都是先判断是否找到解集，找到了就backtrack就完事了
        if (index == coins.length - 1) {
            if (remain % coins[index] == 0) {//这个%有两种功能，1代表remain可能已经是0了，2代表remain是最后一枚硬币的倍数
                res = Math.min(res, remain / coins[index] + curCount);
            }
        } else {
            for (int k = remain / coins[index]; k >= 0 && k + curCount < res; k--) {
                //pruning
                //if (k + curCount >= res) continue;
                //dfs
                helper(index + 1, coins, curCount + k, remain - k * coins[index]);
            }
        }
    }

    //2. dfs with cache, top down, code from leetcode solutions
//    public class Solution {
//
//        public int coinChange(int[] coins, int amount) {
//            if (amount < 1) return 0;
//            return coinChange(coins, amount, new int[amount]);
//        }
//
//        private int coinChange(int[] coins, int rem, int[] count) {
//            if (rem < 0) return -1;
//            if (rem == 0) return 0;
//            if (count[rem - 1] != 0) return count[rem - 1];
//            int min = Integer.MAX_VALUE;
//            for (int coin : coins) {
//                int res = coinChange(coins, rem - coin, count);
//                if (res >= 0 && res < min)
//                    min = 1 + res;
//            }
//            count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
//            return count[rem - 1];
//        }
//    }

    //4. dp, bottom up
//    public int coinChange(int[] coins, int amount) {
//        if (coins == null || coins.length == 0 || amount < 0) return -1;
//        if (amount == 0) return 0;
//        int[] dp = new int[amount + 1];
//        Arrays.fill(dp, Integer.MAX_VALUE - 1);
//        dp[0] = 0;
//        for (int i = 1; i <= amount; i++)
//            for (int j = 0; j < coins.length; j++) {
//                if (i - coins[j] >= 0)
//                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);//这里是滚动数组，二维转一维；另外这里隐含了一个条件，如果已经计算出dp[i - coins[j]]的话才加以利用
//            }
//        return dp[amount] == Integer.MAX_VALUE - 1 ? -1 : dp[amount];
//    }

    public static void main(String args[]) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.print(new CoinChange().coinChange(coins, amount));
    }
}
