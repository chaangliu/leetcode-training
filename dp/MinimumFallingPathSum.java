package dp;

import java.util.Arrays;

/**
 * Given a square array of integers A, we want the minimum sum of a falling path through A.
 * <p>
 * A falling path starts at any element in the first row, and chooses one element from each row.  The next row's choice must be in a column that is different from the previous row's column by at most one.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [[1,2,3],[4,5,6],[7,8,9]]
 * Output: 12
 * Explanation:
 * The possible falling paths are:
 * [1,4,7], [1,4,8], [1,5,7], [1,5,8], [1,5,9]
 * [2,4,7], [2,4,8], [2,5,7], [2,5,8], [2,5,9], [2,6,8], [2,6,9]
 * [3,5,7], [3,5,8], [3,5,9], [3,6,8], [3,6,9]
 * The falling path with the smallest sum is [1,4,7], so the answer is 12.
 * Note:
 * <p>
 * 1 <= A.length == A[0].length <= 100
 * -100 <= A[i][j] <= 100
 * 20191108
 */
public class MinimumFallingPathSum {
    /**
     * 123
     * 456
     * 789
     * 如果暴力的话，显然时间是O(3^n),发现了重复子过程
     * dp[i][j]代表经过A[i][j]的时候的min falling path sum
     * dp[i][j] = min(dp[i - 1][j - 1], dp[i - 1][j], dp[i - 1][j + 1]) + A[i][j]
     * [
     * [-84,-36,2],
     * [87, -79,10],
     * [42, 10, 63]]
     **/
    public int minFallingPathSum(int[][] A) {
        int n = A.length;
        if (n == 1) return A[0][0];
        int[][] dp = new int[1][n];
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) dp[0][i] = A[0][i];
        for (int i = 1; i < n; i++) {
            int[][] tmp = new int[1][n];
            for (int j = 0; j < n; j++) {
                int m1 = Integer.MAX_VALUE, m2 = Integer.MAX_VALUE;
                if (j > 0) {
                    m1 = Math.min(dp[0][j - 1], dp[0][j]) + A[i][j];//注意边界条件处理
                }
                if (j + 1 < n) {
                    m2 = Math.min(dp[0][j + 1], dp[0][j]) + A[i][j];
                }
                tmp[0][j] = Math.min(m1, m2);
                if (i == n - 1) {
                    res = Math.min(res, tmp[0][j]);
                }
            }
            dp = tmp.clone();
        }
        return res;
    }

    /**
     * discuss里的，修改原来的数组
     */
    public int minFallingPathSum__(int[][] A) {
        for (int i = 1; i < A.length; ++i)
            for (int j = 0; j < A.length; ++j)
                A[i][j] += Math.min(A[i - 1][j], Math.min(A[i - 1][Math.max(0, j - 1)], A[i - 1][Math.min(A.length - 1, j + 1)]));
        return Arrays.stream(A[A.length - 1]).min().getAsInt();
    }
}
