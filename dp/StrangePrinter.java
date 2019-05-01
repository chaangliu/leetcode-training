package dp;

/**
 * There is a strange printer with the following two special requirements:
 * <p>
 * The printer can only print a sequence of the same character each time.
 * At each turn, the printer can print new characters starting from and ending at any places, and will cover the original existing characters.
 * Given a string consists of lower English letters only, your job is to count the minimum number of turns the printer needed in order to print it.
 * <p>
 * Example 1:
 * Input: "aaabbb"
 * Output: 2
 * Explanation: Print "aaa" first and then print "bbb".
 * Example 2:
 * Input: "aba"
 * Output: 2
 * Explanation: Print "aaa" first and then print "b" from the second place of the string, which will cover the existing character 'a'.
 * Hint: Length of the given string will not exceed 100.
 * <p>
 * 20190501
 */
public class StrangePrinter {

    /**
     * O(n3) 二维dp
     *
     * dp[i][j]代表[i, j]范围最少打印次数
     * if(s[k] == s[j]) dp[i][j] = min(dp[i][j], dp[i][k] + dp[k + 1][j] - 1)
     * <p>
     * DP还是很难..让人体会到集中注意力是一件困难+宝贵的事
     */
    public int strangePrinter(String s) {
        if (s == null || s.length() == 0) return 0;
        int len = s.length();
        int[][] dp = new int[len][len];
        for (int j = 0; j < len; j++) {
            for (int i = j; i >= 0; i--) {
                dp[i][j] = j - i + 1;//最大的步数。当i == j，代表打印一个字母需要1步
                for (int k = i; k < j; k++) {
                    if (s.charAt(k) == s.charAt(j)) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] - 1);
                    } else {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]);
                    }
                }
            }
        }
        return dp[0][len - 1];
    }
}
