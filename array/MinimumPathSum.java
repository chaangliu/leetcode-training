package array;

/**
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 * <p>
 * Note: You can only move either down or right at any point in time.
 * <p>
 * Created by DrunkPiano on 2017/1/27.
 * <p>
 * *****WRONG EQUATION******  MinSum[m][n] = MinSum[m-1][n-1] + Min(grid[m][n-1], grid[m-1][n])
 * <p>
 * CORRECT EQUATION: MinSum[i][j] = grid[i][j] + Math.Min(MinSum[i][j-1],MinSum[i-1][j])
 */

public class MinimumPathSum {
    /**
     * 题意：只能往右或者往下走的二维数组里，找出左上到右下的最小路径的和。
     * 解法：DP
     */
    public int minPathSum(int[][] grid) {
        int[][] dp = grid.clone();
        int m = dp.length, n = dp[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;
                int top = i == 0 ? Integer.MAX_VALUE : dp[i - 1][j];
                int left = j == 0 ? Integer.MAX_VALUE : dp[i][j - 1];
                dp[i][j] = Math.min(top, left) + dp[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }
}
