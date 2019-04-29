package dp;

/**
 * We write the integers of A and B (in the order they are given) on two separate horizontal lines.
 * <p>
 * Now, we may draw a straight line connecting two numbers A[i] and B[j] as long as A[i] == B[j], and the line we draw does not intersect any other connecting (non-horizontal) line.
 * <p>
 * Return the maximum number of connecting lines we can draw in this way.
 * Example 1:
 * <p>
 * <p>
 * Input: A = [1,4,2], B = [1,2,4]
 * Output: 2
 * Explanation: We can draw 2 uncrossed lines as in the diagram.
 * We cannot draw 3 uncrossed lines, because the line from A[1]=4 to B[2]=4 will intersect the line from A[2]=2 to B[1]=2.
 * Example 2:
 * <p>
 * Input: A = [2,5,1,2,5], B = [10,5,2,1,5,2]
 * Output: 3
 * Example 3:
 * <p>
 * Input: A = [1,3,7,1,7,5], B = [1,9,2,5,1]
 * Output: 2
 * <p>
 * <p>
 * 20190429
 */
public class UncrossedLines {
    /**
     * 这题其实就是求最终公共子序列(我没看出来)，
     * 我看了它的relative questions是edit distance，就模仿它的思路写了下面的代码，能A，但是思路并没有很清楚，有几处操作多余
     */
    public int maxUncrossedLines(int[] A, int[] B) {
        if (A.length == 0 || B.length == 0) return 0;

        int[][] dp = new int[A.length + 1][B.length + 1];
        for (int i = 1; i < A.length + 1; i++)
            for (int j = 1; j < B.length + 1; j++) {
                if (A[i - 1] == B[j - 1]) {
                    // 这里的比较是多余的，因为dp[i][j]已经是当前最优值了
                    dp[i][j] = Math.max(dp[i - 1][j], Math.max(dp[i - 1][j - 1] + 1, dp[i][j - 1]));
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], Math.max(dp[i - 1][j - 1], dp[i][j - 1]));
                }
            }
        return dp[A.length][B.length];
    }

    /**
     * LCS的转移方程是这样的：
     * dp[i][j] = 0, if(i == 0) or (j == 0)
     * dp[i][j] = dp[i-1][j-1] + 1, if(s[i] == t[j])
     * dp[i][j] = max{dp[i][j-1] , dp[i-1][j] } , if(s[i] != t[j])
     */

    public int maxUncrossedLines__LCS(int[] A, int[] B) {
        int m = A.length, n = B.length, dp[][] = new int[m + 1][n + 1];
        for (int i = 1; i <= m; ++i)
            for (int j = 1; j <= n; ++j)
                if (A[i - 1] == B[j - 1])
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
        return dp[m][n];
    }
}
