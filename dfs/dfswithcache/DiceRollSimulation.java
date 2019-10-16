package dfs.dfswithcache;

import java.util.Arrays;

/**
 * 1223. Dice Roll Simulation
 * Medium
 * <p>
 * 29
 * <p>
 * 24
 * <p>
 * Favorite
 * <p>
 * Share
 * A die simulator generates a random number from 1 to 6 for each roll. You introduced a constraint to the generator such that it cannot roll the number i more than rollMax[i] (1-indexed) consecutive times.
 * <p>
 * Given an array of integers rollMax and an integer n, return the number of distinct sequences that can be obtained with exact n rolls.
 * <p>
 * Two sequences are considered different if at least one element differs from each other. Since the answer may be too large, return it modulo 10^9 + 7.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 2, rollMax = [1,1,2,2,2,3]
 * Output: 34
 * Explanation: There will be 2 rolls of die, if there are no constraints on the die, there are 6 * 6 = 36 possible combinations. In this case, looking at rollMax array, the numbers 1 and 2 appear at most once consecutively, therefore sequences (1,1) and (2,2) cannot occur, so the final answer is 36-2 = 34.
 * Example 2:
 * <p>
 * Input: n = 2, rollMax = [1,1,1,1,1,1]
 * Output: 30
 * Example 3:
 * <p>
 * Input: n = 3, rollMax = [1,1,1,2,2,3]
 * Output: 181
 * Constraints:
 * <p>
 * 1 <= n <= 5000
 * rollMax.length == 6
 * 1 <= rollMax[i] <= 15
 * <p>
 * 20191013
 */
public class DiceRollSimulation {
    /**
     * 题意：n个骰子组成一个n位数，一共有6^n种排列；限制是，数字中最多有连续rollMax[i]个i。
     * 这题周赛第三题，做出来的人很少，三维DP，看了tiankonguse的讲解很容易懂(但是让我自己想出来还是比较困难的)。
     * dp[i][count][remain]代表i已经出现了count次，再掷remain次会有多少种排列。
     * res = dp[1][1][n - 1] + .. + dp[1][6][n - 1]
     */
    public int dieSimulator(int n, int[] rollMax) {
        int[][][] memo = new int[6][16][5001];
        for (int[][] a : memo) {
            for (int[] b : a) {
                Arrays.fill(b, -1);
            }
        }
        return dfs(0, 0, n, rollMax, memo);
    }

    private int dfs(int i, int count, int remain, int[] rollMax, int[][][] memo) {
        if (remain == 0) return 1;
        if (memo[i][count][remain] != -1) return memo[i][count][remain];
        int res = 0, mod = 1000000007;
        for (int x = 0; x < 6; x++) {
            if (x != i) {
                res = (res + dfs(x, 1, remain - 1, rollMax, memo)) % mod;
            } else if (count < rollMax[x]) {
                res = (res + dfs(x, count + 1, remain - 1, rollMax, memo)) % mod;
            }
        }
        memo[i][count][remain] = res;
        return res;
    }

    /**
     * bottom up dp，属实抽象，比top down recursion难多了。。
     * dp[i][j] rolling the ith dice and got j
     */
    public int dieSimulator_bottom_up(int n, int[] rollMax) {
        int mod = (int) 1e9 + 7;
        //dp[i][j] represents the number of distinct sequences that can be obtained when rolling i times and ending with j
        //The one more row represents the total sequences when rolling i times
        int[][] dp = new int[n + 1][7];
        for (int i = 0; i < 6; i++) {
            dp[1][i] = 1;
        }
        dp[1][6] = 6;
        for (int i = 2; i <= n; i++) {
            int total = 0;
            for (int j = 0; j < 6; j++) {
                //if without constraints
                dp[i][j] = dp[i - 1][6];
                //if for the ith dice, constraint for j is i - 1, meaning that at most i - 1 consecutive dices can have same number, that is to say all previous numbers can be the same, except current number. so we minus one.
                if (i - 1 == rollMax[j]) {
                    dp[i][j]--;
                }
                //if for the ith dice, constraint for j is < i - 1, meaning that the `i - rollMax[j] - 1`th number can be anything but j(which we have already deducted in the previous step)
                if (i - 1 > rollMax[j]) {//if for the ith dice, constraint for j is < i - 1
                    int reduction = dp[i - rollMax[j] - 1][6] - dp[i - rollMax[j] - 1][j];
                    //must add one more mod because subtraction may introduce negative numbers
                    dp[i][j] = ((dp[i][j] - reduction) % mod + mod) % mod;
                }
                total = (total + dp[i][j]) % mod;
            }
            dp[i][6] = total;
        }
        return dp[n][6];
    }
}
