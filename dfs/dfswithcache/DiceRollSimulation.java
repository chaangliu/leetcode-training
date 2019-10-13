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
}
