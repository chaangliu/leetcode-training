package dp;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 * <p>
 * Example:
 * <p>
 * Input:
 * <p>
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * <p>
 * Output: 4
 * <p>
 * 20190120
 */
public class MaximalSquare {
    //approach 1. brute force
    //找到一个1，就向右和向下扩展1格，判断扩展的这一列一行是不是有效，如果是，再扩展一格。
    //Time complexity :O((mn)2)


    //approach 2. 2dimensional dp
    //动态转移方程：dp(i, j)=min(dp(i−1, j), dp(i−1, j−1), dp(i, j−1))+1.
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int rows = matrix.length, cols = matrix[0].length;
        int[][] dp = new int[rows + 1][cols + 1];
        int maxsqlen = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                //注意，这里是matrix，不是dp；[i-1][j-1]也就是matrix跟dp的同一个格子；但是dp矩阵老规矩又在左侧和上方增加了一列和一行0作为辅助
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;//注意，
                    maxsqlen = Math.max(maxsqlen, dp[i][j]);
                }
            }
        }
        return maxsqlen * maxsqlen;
    }
}
