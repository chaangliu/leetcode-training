package array;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * <p>
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * <p>
 * How many possible unique paths are there?
 * <p>
 * <p>
 * Above is a 3 x 7 grid. How many possible unique paths are there?
 * <p>
 * Note: m and n will be at most 100.
 * Created by DrunkPiano on 2017/1/23.
 * <p>
 * <p>
 * <p>
 * status transfer equation:
 * Paths[i][j] = Paths[i-1][j] + Paths[i][j-1]
 * 20200116 --review
 */

public class UniquePaths {
    /**
     * 题意：机器人从左上角走到右下角，问一共有多少种路径。
     * 解法：二维DP。跟斐波那契数列思路一样。
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) dp[i][j] = 1;
                else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 降维，我们只依赖当前行和上一行，所以可以使用2行滚动。
     */
    public int uniquePaths_(int cols, int rows) {
        int[] cur = new int[cols];
        int[] pre = new int[cols];

        for (int i = 0; i < cols; i++) {
            pre[i] = 1;
            cur[i] = 1;
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++)
                cur[j] = cur[j - 1] + pre[j];
            pre = cur;
        }
        return cur[cols - 1];
    }

    /**
     * 继续压缩，把pre那一行去掉
     */
    public int uniquePaths__(int cols, int rows) {
        int[] cur = new int[cols];
        for (int i = 0; i < cols; i++)
            cur[i] = 1;
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++)
                cur[j] = cur[j - 1] + cur[j];
        }
        return cur[cols - 1];
    }
}
