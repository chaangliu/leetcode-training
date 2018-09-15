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
    public int minPathSum(int[][] grid) {
        int row = grid.length;
        if (row == 0) return 0;
        int col = grid[0].length;
        int dp[][] = new int[row][col];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < col; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }

        for (int i = 1; i < row; i++)
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        return dp[row - 1][col - 1];
    }

    public static void main(String args[]) {
        int[][] grid = {{1, 2, 5}, {3, 2, 1}};
        MinimumPathSum minimumPathSum = new MinimumPathSum();
        System.out.println(minimumPathSum.minPathSum(grid));
    }
}
