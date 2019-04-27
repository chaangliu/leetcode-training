package dp;

/**
 * Given a string, your task is to count how many palindromic substrings in this string.
 * <p>
 * The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.
 * <p>
 * Example 1:
 * <p>
 * Input: "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 * <p>
 * <p>
 * Example 2:
 * <p>
 * Input: "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 * <p>
 * <p>
 * Note:
 * <p>
 * The input string length won't exceed 1000.
 * <p>
 * 20190427
 */
public class PalindromicSubstrings {


    /**
     * 这题跟Longest Palindromic Substring的几种approach一模一样，都是Expand From Center、DP和Manacher
     * 这题用 Expand From center 思路最简单，写法上要注意，需要从i和i+1分别向左右expand，这样可以兼并奇偶数长度的substring。
     * <p>
     * 下面写一下DP方法。
     */
    public int countSubstrings(String s) {
        int len = s.length();
        int res = 0;
        boolean dp[][] = new boolean[len][len];
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {// j从i开始，方便res++操作
                dp[i][j] = s.charAt(i) == s.charAt(j) && (i + 1 < len && j - 1 >= 0 && dp[i + 1][j - 1] || i == j || i + 1 == j || i + 2 == j);//可以把||放&&前面，这样就不用担心越界
                if (dp[i][j]) res++;
            }
        }
        return res;
    }
}
