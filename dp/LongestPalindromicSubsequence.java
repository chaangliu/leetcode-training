package dp;

/**
 * Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.
 * <p>
 * Example 1:
 * Input:
 * <p>
 * "bbbab"
 * Output:
 * 4
 * One possible longest palindromic subsequence is "bbbb".
 * Example 2:
 * Input:
 * <p>
 * "cbbd"
 * Output:
 * 2
 * <p>
 * 20190405
 */
public class LongestPalindromicSubsequence {

    /**
     * 这题有点像微软2019校招笔试第二题，不过据说那题要用区间dp来做，那题想了很久完全想不出来怎么区间合并，放弃
     * <p>
     * dp[i][j]: the longest palindromic subsequence's length of substring(i, j), here i, j represent left, right indexes in the string
     * State transition:
     * dp[i][j] = dp[i+1][j-1] + 2 if s.charAt(i) == s.charAt(j)
     * otherwise, dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1])
     * <p>
     * 这边outer loop是从end到start循环的，因为 dp[i][j]需要dp[i + 1][j - 1]的值。
     */
    public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];

        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < s.length(); j++) {
                System.out.println(i + ", " + j);
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][s.length() - 1];
    }

    /**
     * outer loop正向循环的话，可以使用一个步长的概念，类似区间dp。保证[i + 1][j - 1]已经赋值过
     */
    public int longestPalindromeSubseq__(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++)
            dp[i][i] = 1;
        for (int d = 1; d < len; d++) {
            for (int i = 0; i < len - d; i++) {
                int j = i + d;
                if (s.charAt(i) == s.charAt(j))
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
            }
        }
        return dp[0][len - 1];
    }

    /**
     * bottom recursion
     */
    public int longestPalindromeSubseq__BottomRecursion(String s) {
        return helper(s, 0, s.length() - 1, new Integer[s.length()][s.length()]);
    }

    private int helper(String s, int i, int j, Integer[][] memo) {
        if (memo[i][j] != null) {
            return memo[i][j];
        }
        if (i > j) return 0;// dp[i][j] = 0 , if i > j
        if (i == j) return 1;//dp[i][j] = 1 , if j == j

        if (s.charAt(i) == s.charAt(j)) {
            memo[i][j] = helper(s, i + 1, j - 1, memo) + 2;
        } else {
            memo[i][j] = Math.max(helper(s, i + 1, j, memo), helper(s, i, j - 1, memo));
        }
        return memo[i][j];
    }

    public static void main(String args[]) {
        new LongestPalindromicSubsequence().longestPalindromeSubseq("bbbab");
    }
}
