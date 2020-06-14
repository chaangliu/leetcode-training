package dp;

/**
 * Given two strings text1 and text2, return the length of their longest common subsequence.
 * A subsequence of a string is a new string generated from the original string with some characters(can be none) deleted without changing the relative order of the remaining characters. (eg, "ace" is a subsequence of "abcde" while "aec" is not). A common subsequence of two strings is a subsequence that is common to both strings.
 * If there is no common subsequence, return 0.
 * Example 1:
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 * Example 2:
 * Input: text1 = "abc", text2 = "abc"
 * Output: 3
 * Explanation: The longest common subsequence is "abc" and its length is 3.
 * Example 3:
 * Input: text1 = "abc", text2 = "def"
 * Output: 0
 * Explanation: There is no such common subsequence, so the result is 0.
 * Constraints:
 * 1 <= text1.length <= 1000
 * 1 <= text2.length <= 1000
 * The input strings consist of lowercase English characters only.
 * 20191030
 */
public class LongestCommonSubsequence {
    /**
     * 题目：求最长公共子序列。经典题。
     * dp[i][j]代表text1的前i个字符和text2的前j个字符的LCS长度
     * 转移方程：dp[i][j] = A[i] == B[j] ? dp[i - 1][j - 1] + 1: max(dp[i - 1][j], dp[i][j - 1]);
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length(), len2 = text2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                dp[i][j] = text1.charAt(i - 1) == text2.charAt(j - 1)
                        ? dp[i - 1][j - 1] + 1 : Math.max(dp[i - 1][j], dp[i][j - 1]);//注意text的charAt是i - 1
            }
        }
        return dp[text1.length()][text2.length()];
    }

    /**
     * top down, recursive with memo
     */
    public int longestCommonSubsequence_(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }
        return longestCommonSubsequence(text1, text2, 0, 0, dp);
    }

    int longestCommonSubsequence(String s1, String s2, int i, int j, int[][] dp) {
        if (i == s1.length() || j == s2.length()) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        if (s1.charAt(i) == s2.charAt(j)) {
            return dp[i][j] = 1 + longestCommonSubsequence(s1, s2, i + 1, j + 1, dp);
        } else {
            return dp[i][j] = Math.max(longestCommonSubsequence(s1, s2, i + 1, j, dp),
                    longestCommonSubsequence(s1, s2, i, j + 1, dp));
        }
    }
}
