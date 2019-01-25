package dp;

import java.util.Arrays;

/**
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 12
 * Output: 3
 * Explanation: 12 = 4 + 4 + 4.
 * Example 2:
 * <p>
 * Input: n = 13
 * Output: 2
 * Explanation: 13 = 4 + 9.
 * <p>
 * 20190125
 */
public class PerfectSquares {

    //1. dp
    //状态转移方程： //dp[i] = min(dp[i - j * j] + 1)
    public int numSquares(int n) {
        if (n <= 0) return 0;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    //另外，这题还可以用BFS和MATH解法
    //https://leetcode.com/problems/perfect-squares/discuss/71488/Summary-of-4-different-solutions-(BFS-DP-static-DP-and-mathematics)


}
