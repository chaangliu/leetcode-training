package dp;

/**
 * Given a string s. In one step you can insert any character at any index of the string.
 * Return the minimum number of steps to make s palindrome.
 * A Palindrome String is one that reads the same backward as well as forward.
 * Example 1:
 * Input: s = "zzazz"
 * Output: 0
 * Explanation: The string "zzazz" is already palindrome we don't need any insertions.
 * Example 2:
 * Input: s = "mbadm"
 * Output: 2
 * Explanation: String can be "mbdadbm" or "mdbabdm".
 * Example 3:
 * Input: s = "leetcode"
 * Output: 5
 * Explanation: Inserting 5 characters the string becomes "leetcodocteel".
 * Example 4:
 * Input: s = "g"
 * Output: 0
 * Example 5:
 * Input: s = "no"
 * Output: 1
 * Constraints:
 * 1 <= s.length <= 500
 * All characters of s are lower case English letters.
 * 20200106
 */
public class MinimumInsertionStepstoMakeaStringPalindrome {
    /**
     * 题意：给你一个字符串，你可以在任意位置插入字符，求最少需要插入多少字符可以让这个字符串成为palindrome。
     * 一开始没思路，看了hint发现思路是：先求出最长padlindrome子序列x，然后n-x就是最短需要插入的数量。
     * 最长palindrome subsequence转移方程很经典, dp[i][j] = A[i] == A[j] ? dp[i+1][j-1] + 2 : max(dp[i+1][j],dp[i][j-1])
     * 最直接的方法，把lps那题代码贴过来，return s.length() - longestPalindromeSubseq(s);
     * 权当复习一下，写了个区间dp那样用len来按步长计算的，结果一开始在外面多写了个for。记住，区间dp的len一定是最外层。
     **/
    public int minInsertions(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) dp[i][i] = 1;
        for (int len = 1; len < n; len++) {
            for (int i = 0; i + len < n; i++) {
                int j = i + len;
                dp[i][j] = s.charAt(i) == s.charAt(j) ? (dp[i + 1][j - 1] + 2) : Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }
        return n - dp[0][n - 1];
    }
}
