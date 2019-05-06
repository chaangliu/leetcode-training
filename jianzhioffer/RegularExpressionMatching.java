package jianzhioffer;

public class RegularExpressionMatching {
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
     * 2. dp
     * dp[i + 1][j + 1]表示p[i:]和s[j:]能否匹配
     */
    public boolean isMatch__DP(String s, String p) {
        if (s == null || p == null) return false;

        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*' && dp[0][i - 1]) {
                dp[0][i + 1] = true;
            }
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                //如果i,j对应字母相同或者为'.'，那么取决于前一位
                if (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i)) {
                    dp[i + 1][j + 1] = dp[i][j];
                } else if (p.charAt(j) == '*') {
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
