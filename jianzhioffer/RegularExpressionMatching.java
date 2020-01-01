package jianzhioffer;

/**
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 * Note:
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters like . or *.
 * Example 1:
 * Input:
 * s = "aa"
 * p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * Example 2:
 * Input:
 * s = "aa"
 * p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 * Example 3:
 * Input:
 * s = "ab"
 * p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 * Example 4:
 * Input:
 * s = "aab"
 * p = "c*a*b"
 * Output: true
 * Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
 * Example 5:
 * Input:
 * s = "mississippi"
 * p = "mis*is*p*."
 * Output: false
 * 20200101 review
 */
public class RegularExpressionMatching {
    /**
     * 题意：给你一个s和一个pattern，.能匹配任意单字，*能匹配0~任意多个preceding字母。问p能否匹配s。
     */
    //这题可能是剑指offer上唯一一道hard？
    //这题剑指offer用递归。leetcode 10，可用dp。
    public boolean isMatch(String text, String pattern) {
        if (pattern.isEmpty()) return text.isEmpty();
        boolean firstMatch = !text.isEmpty() && (text.charAt(0) == pattern.charAt(0) || pattern.charAt(0) == '.');
        if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
            return isMatch(text, pattern.substring(2)) || firstMatch && isMatch(text.substring(1), pattern);
        } else {
            return firstMatch && isMatch(text.substring(1), pattern.substring(1));
        }
    }

    /**
     * Approach 2. dp, 也是大部分人采取的方法；
     * dp[i + 1][j + 1]表示s的前i个和p的前j个字母是否匹配。
     * p[j]如果是字母或者点都好办，难点是星号；
     * 另外一点就是dp的index和s,p的index要注意。
     * <p>
     * 1, If p.charAt(j) == s.charAt(i) :  dp[i][j] = dp[i-1][j-1];
     * 2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
     * 3, If p.charAt(j) == '*':
     * here are two sub conditions:
     * 1   if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2]  //in this case, a* only counts as empty
     * 2   if p.charAt(i-1) == s.charAt(i) or p.charAt(i-1) == '.':
     * dp[i][j] = dp[i-1][j]    //in this case, a* counts as multiple a
     * or dp[i][j] = dp[i][j-1]   // in this case, a* counts as single a
     * or dp[i][j] = dp[i][j-2]   // in this case, a* counts as empty
     */
    public boolean isMatch__DP(String s, String p) {
        if (s == null || p == null) return false;

        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*' && dp[0][i - 1]) {//这里dp[0][i-1]表示s=""和p的前i-2位是否匹配，因为如果成立，那么a*可以表示成empty
                dp[0][i + 1] = true;
            }
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                //如果i,j对应字母相同或者为'.'，那么取决于前一位
                if (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i)) {
                    dp[i + 1][j + 1] = dp[i][j];
                } else if (p.charAt(j) == '*') {//所以，这里j一定>=1，因为case中没有leading *，下面j-1也就不会overflow
                    //比如p[j]是*，j - 1位是a，而s[i]是b，那么a*只能代表a * 0，匹配0个a =》也就是dp[i + 1][j - 1]，相当于a*这个pattern没用到。
                    if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.') {
                        dp[i + 1][j + 1] = dp[i + 1][j - 1];
                    } else {
                        //a*如果满足 single a (a * 1) || multiple a (a * n) || empty a 即可。multi a这里这么理解：相当于s[i]忽略掉，*可以cover。
                        dp[i + 1][j + 1] = (dp[i + 1][j] || dp[i][j + 1] || dp[i + 1][j - 1]);
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}
