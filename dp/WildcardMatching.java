package dp;

/**
 * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
 * Note:
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters like ? or *.
 * Example 1:
 * Input:
 * s = "aa"
 * p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * Example 2:
 * Input:
 * s = "aa"
 * p = "*"
 * Output: true
 * Explanation: '*' matches any sequence.
 * Example 3:
 * Input:
 * s = "cb"
 * p = "?a"
 * Output: false
 * Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
 * Example 4:
 * Input:
 * s = "adceb"
 * p = "*a*b"
 * Output: true
 * Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
 * Example 5:
 * Input:
 * s = "acdcb"
 * p = "a*c?b"
 * Output: false
 * Created by DrunkPiano on 2017/4/19.
 * 20200109 --review
 */

public class WildcardMatching {
    public boolean isMatch(String s, String p) {
        if (s == null && p == null) return true;
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i < p.length() + 1; i++) {
            if (p.charAt(i - 1) == '*') {
                //必须是p的前i-1个char都是*，s[0](空字符串)才能跟它匹配
                dp[0][i] = dp[0][i - 1];
            } else break;
        }
        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = 1; j < p.length() + 1; j++) {
                if (p.charAt(j - 1) != '*') {
                    dp[i][j] = dp[i - 1][j - 1] && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?');
                } else {
                    // dp[i][j-1]容易理解，就是*代表空字符串；
                    // dp[i-1][j]举个例子，s="aa" p="*"，计算dp[2][1]，相当于可以比较"a""*"，如果匹配那么"aa""*"必然匹配
                    // 另外我又想到，为什么不用判断s[0,k](k属于0~i-1) 的部分和p[0,j]是否匹配呢?因为p[j]是*，所以无序判断(充要条件)。如果"xxa" "x*"匹配，那么"xx" "x*"必然匹配，反之亦然
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}
