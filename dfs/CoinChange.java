package dfs;

import java.util.Arrays;

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

    //1. brute force
    public int coinChange(int[] coins, int amount) {
        return coinChange(0, coins, amount);
    }

    private int coinChange(int idxCoin, int[] coins, int amount) {
        if (amount == 0)
            return 0;
        if (idxCoin < coins.length && amount > 0) {
            int maxVal = amount / coins[idxCoin];
            int minCost = Integer.MAX_VALUE;
            for (int x = 0; x <= maxVal; x++) {
                if (amount >= x * coins[idxCoin]) {
                    int res = coinChange(idxCoin + 1, coins, amount - x * coins[idxCoin]);
                    if (res != -1)
                        minCost = Math.min(minCost, res + x);
                }
            }
            return (minCost == Integer.MAX_VALUE) ? -1 : minCost;
        }
        return -1;
    }

    public static void main(String args[]) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.print(new CoinChange().coinChange(coins, amount));
    }
}
