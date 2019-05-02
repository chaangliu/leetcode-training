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
     * 这题我立刻想到了二维DP，然后发现跟LCS是同一题。不过DP初始条件的设置卡了我很久，后来才发现多申请一个长度就迎刃而解；下次记住这种需要对比i - 1或者j - 1的题目常常多申请一行一列dp
     * dp[i][j] = a[i] == b[j] ? a[i - 1][j - 1] + 1 : min(dp[i - 1][j], dp[i][j - 1])
     */
    public int minDistance(String word1, String word2) {
        if (word1.length() == 0) return word2.length();
        if (word2.length() == 0) return word1.length();
        int n1 = word1.length() + 1, n2 = word2.length() + 1;
        int dp[][] = new int[n1][n2];
        for (int i = 1; i < n1; i++) {
            for (int j = 1; j < n2; j++) {
                dp[i][j] = word1.charAt(i - 1) == word2.charAt(j - 1) ? dp[i - 1][j - 1] + 1 : Math.max(dp[i][j - 1], dp[i - 1][j]);
            }
        }
        return word1.length() + word2.length() - 2 * dp[n1 - 1][n2 - 1];
    }


    /**
     * 下面是在Solutions里看到的dfs with memo的方式，可以看到，dp经常跟dfs + memo类比起来。Time complexity : O(m*n)
     */

    public int minDistance__RECURSION(String s1, String s2) {
        int[][] memo = new int[s1.length() + 1][s2.length() + 1];
        return s1.length() + s2.length() - 2 * lcs(s1, s2, s1.length(), s2.length(), memo);
    }

    public int lcs(String s1, String s2, int m, int n, int[][] memo) {
        if (m == 0 || n == 0)
            return 0;
        if (memo[m][n] > 0)
            return memo[m][n];
        if (s1.charAt(m - 1) == s2.charAt(n - 1))
            memo[m][n] = 1 + lcs(s1, s2, m - 1, n - 1, memo);
        else
            memo[m][n] = Math.max(lcs(s1, s2, m, n - 1, memo), lcs(s1, s2, m - 1, n, memo));
        return memo[m][n];
    }
}
