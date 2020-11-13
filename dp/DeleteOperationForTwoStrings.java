package dp;

/**
 * Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same, where in each step you can delete one character in either string.
 * <p>
 * Example 1:
 * Input: "sea", "eat"
 * Output: 2
 * Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
 * Note:
 * The length of given words won't exceed 500.
 * Characters in given words can only be lower-case letters.
 * <p>
 * 20190502
 */
public class DeleteOperationForTwoStrings {
    /**
     * 题意：求两个字符串的编辑距离。
     * 解法：经典DP；写起来有两个地方注意，
     * 第一，字母相等时，转移方程的min里面不要漏了A[i - 1] == B[j - 1]这种情况，因为edit distance允许替换操作。
     * 第二，多申请一行一列
     * dp[i][j] = A[i - 1] == B[j - 1] ? dp[i - 1][j - 1] : Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]) + 1;
     */
    public int minDistance(String word1, String word2) {
        char[] A = word1.toCharArray(), B = word2.toCharArray();
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) dp[i][0] = i; // 初始化
        for (int j = 0; j <= n; j++) dp[0][j] = j;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 因为多申请了一行一列，所以这里注意用A[i - 1] == B[j - 1]来表示word1[i] word2[j]
                dp[i][j] = A[i - 1] == B[j - 1] ? dp[i - 1][j - 1] : Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]) + 1;
            }
        }
        return dp[m][n];
    }


    /**
     * 改写成dfs with memo的方式.
     */
    public int minDistance_DFS(String s1, String s2) {
        int[][] memo = new int[s1.length() + 1][s2.length() + 1];
        return lcs(s1, s2, s1.length(), s2.length(), memo);
    }

    public int lcs(String s1, String s2, int m, int n, int[][] memo) {
        if (m == 0) return n;
        if (n == 0) return m;
        if (memo[m][n] > 0)
            return memo[m][n];
        if (s1.charAt(m - 1) == s2.charAt(n - 1))
            memo[m][n] = lcs(s1, s2, m - 1, n - 1, memo);
        else
            memo[m][n] = 1 + Math.min(lcs(s1, s2, m, n - 1, memo), Math.min(lcs(s1, s2, m - 1, n, memo), lcs(s1, s2, m - 1, n - 1, memo)));
        return memo[m][n];
    }
}
