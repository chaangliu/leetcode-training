package dp;

/**
 * 给你一个大小为 rows x cols 的矩阵 grid 。最初，你位于左上角 (0, 0) ，每一步，你可以在矩阵中 向右 或 向下 移动。
 * 在从左上角 (0, 0) 开始到右下角 (rows - 1, cols - 1) 结束的所有路径中，找出具有 最大非负积 的路径。路径的积是沿路径访问的单元格中所有整数的乘积。
 * 返回 最大非负积 对 109 + 7 取余 的结果。如果最大积为负数，则返回 -1 。
 * 注意，取余是在得到最大积之后执行的。
 * 20200922
 */
public class MaximumNonNegativeProductinaMatrix {
    /**
     * 题意：从左上角到右下角找一个path，这个path数字乘积的非负最大值是多少。如果是负数，返回-1。
     * 解法: 这题跟MaximumProductSubarray思路一样，注意不要判断g[i][j]的正负。
     * 代码参考：https://leetcode.com/problems/maximum-non-negative-product-in-a-matrix/discuss/855105/Java-Simple-DP-beat-100
     */
    public int maxProductPath(int[][] g) {
        int m = g.length, n = g[0].length, mod = 1_000_000_007;
        long dp[][][] = new long[m][n][2]; // dp[i][j][0]表示达到g[i][j]时候的最大值，dp[i][j][1]表示达到g[i][j]时候的最小值
        dp[0][0] = new long[]{g[0][0], g[0][0]}; //
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;
                long a = 0, b = 0;
                if (i == 0) {
                    dp[i][j][0] = dp[i][j][1] = g[i][j] * dp[i][j - 1][0]; // 对于第一行的初始化，dp[i][j - 1][0]是等于dp[i][j - 1][1]的，取其中一个就行
                } else if (j == 0) {
                    dp[i][j][0] = dp[i][j][1] = g[i][j] * dp[i - 1][j][0];
                } else {
                    a = g[i][j] * Math.max(dp[i][j - 1][0], dp[i - 1][j][0]); // a是最大或最小，b也是最大或最小，巧妙
                    b = g[i][j] * Math.min(dp[i][j - 1][1], dp[i - 1][j][1]);
                    dp[i][j][0] = Math.max(a, b);
                    dp[i][j][1] = Math.min(a, b);
                }
            }
        }
        if (dp[m - 1][n - 1][0] < 0) return -1;
        return (int) ((dp[m - 1][n - 1][0]) % mod);
    }
}
