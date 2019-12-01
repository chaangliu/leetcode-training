package dp;

/**
 * Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.
 * Example 1:
 * Input: matrix =
 * [
 * [0,1,1,1],
 * [1,1,1,1],
 * [0,1,1,1]
 * ]
 * Output: 15
 * Explanation:
 * There are 10 squares of side 1.
 * There are 4 squares of side 2.
 * There is  1 square of side 3.
 * Total number of squares = 10 + 4 + 1 = 15.
 * Example 2:
 * Input: matrix =
 * [
 * [1,0,1],
 * [1,1,0],
 * [1,1,0]
 * ]
 * Output: 7
 * Explanation:
 * There are 6 squares of side 1.
 * There is 1 square of side 2.
 * Total number of squares = 6 + 1 = 7.
 * Constraints:
 * 1 <= arr.length <= 300
 * 1 <= arr[0].length <= 300
 * 0 <= arr[i][j] <= 1
 * 20191201
 */
public class CountSquareSubmatriceswithAllOnes {
    /**
     * 题意：给你一个matrix，找出有多少个1组成的正方形。
     * 经过师弟提醒，这题跟Matrix Square一样
     */
    public int countSquares(int[][] matrix) {
        int res = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int rows = matrix.length, cols = matrix[0].length;
        int[][] dp = new int[rows + 1][cols + 1];
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (matrix[i - 1][j - 1] == 1) {
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                    res += dp[i][j];//多了一个边长为3的正方形，那么一定多了3个正方形(1~3)
                }
            }
        }
        return res;
    }

    /**
     * lee的代码, in place，
     * 乍一看好像漏掉了检查A[i - 1][j - 1]，但仔细看发现用了个操作：A[i][j] = A[i - l][j - l] > 0 ? l + 1 : l; 精妙，相当于检查A[i - 1][j - 1] >= l
     */
    public int countSquares__(int[][] A) {
        int res = 0;
        for (int i = 0; i < A.length; ++i) {
            for (int j = 0; j < A[0].length; ++j) {
                if (A[i][j] > 0 && i > 0 && j > 0) {
                    int l = Math.min(A[i - 1][j], A[i][j - 1]);
                    A[i][j] = A[i - l][j - l] > 0 ? l + 1 : l;
                }
                res += A[i][j];
            }
        }
        return res;
    }
}
