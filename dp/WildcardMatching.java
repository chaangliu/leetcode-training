package dp;

/**
 * 当p[j]等于‘?’或者s[i]等于p[j]时，则vvb[i][j]的值取决于vvb[i-1][j-1]，即为s的前一位置和p的前一位置是否匹配；
 * <p>
 * 当p[j]等于‘*’时，如果该‘*’可以匹配s中的0个或者1个字符，分别对应vvb[i][j-1]，即s的当前位置和p的前一位置是否匹配，以及vvb[i-1][j-1]，即s的前一位置和p的前一位置是否匹配。
 * Created by DrunkPiano on 2017/4/19.
 */

public class WildcardMatching {
    public boolean isMatch(String s, String p) {
        if (s == null && p == null) return true;
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;

        for (int i = 1; i < p.length() + 1; i++) {
            if (p.charAt(i - 1) == '*') {
                //必须是p的前i-1个char都是*，s才能跟它匹配
                dp[0][i] = dp[0][i - 1];
            }
        }
        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = 1; j < p.length() + 1; j++) {
                if (p.charAt(j - 1) != '*') {
                    if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                } else {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j - 1];
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}
