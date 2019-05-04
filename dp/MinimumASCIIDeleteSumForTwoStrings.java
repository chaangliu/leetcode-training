package dp;

/**
 * Given two strings s1, s2, find the lowest ASCII sum of deleted characters to make two strings equal.
 * <p>
 * Example 1:
 * Input: s1 = "sea", s2 = "eat"
 * Output: 231
 * Explanation: Deleting "s" from "sea" adds the ASCII value of "s" (115) to the sum.
 * Deleting "t" from "eat" adds 116 to the sum.
 * At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum possible to achieve this.
 * Example 2:
 * Input: s1 = "delete", s2 = "leet"
 * Output: 403
 * Explanation: Deleting "dee" from "delete" to turn the string into "let",
 * adds 100[d]+101[e]+101[e] to the sum.  Deleting "e" from "leet" adds 101[e] to the sum.
 * At the end, both strings are equal to "let", and the answer is 100+101+101+101 = 403.
 * If instead we turned both strings into "lee" or "eet", we would get answers of 433 or 417, which are higher.
 * Note:
 * <p>
 * 0 < s1.length, s2.length <= 1000.
 * All elements of each string will have an ASCII value in [97, 122].
 * <p>
 * 20190504 15:41
 */
public class MinimumASCIIDeleteSumForTwoStrings {
    /**
     * 这题拿到就发现是要求LCS，就想着怎么转换为LCS问题；想了下发现就是求max ascii sum of common subsequence，数组存储的内容变一下就行了
     * dp[i][j] = a[i] == b[j] ? dp[i - 1][j - 1] + ascii(a[i]) : max(dp[i - 1][j], dp[i][j - 1])
     */
    public int minimumDeleteSum(String s1, String s2) {
        int dp[][] = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                dp[i][j] = s1.charAt(i - 1) == s2.charAt(j - 1) ? dp[i - 1][j - 1] + s1.charAt(i - 1) : Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        int total = getAscii(s1) + getAscii(s2);
        return total - dp[s1.length()][s2.length()] * 2;
    }

    private int getAscii(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++)
            sum += s.charAt(i);
        return sum;
    }


    /**
     * solutions里的解法，dp[i][j]直接代表想要求的结果（最小ascii deletion的和）
     */
    public int minimumDeleteSum__OFFICIAL(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = s1.length() - 1; i >= 0; i--) {
            dp[i][s2.length()] = dp[i + 1][s2.length()] + s1.codePointAt(i);//初始化，每删除一个字母代价都要变高。从后往前是因为dp[i][j]代表「前i(j)位」
        }
        for (int j = s2.length() - 1; j >= 0; j--) {
            dp[s1.length()][j] = dp[s1.length()][j + 1] + s2.codePointAt(j);
        }
        for (int i = s1.length() - 1; i >= 0; i--) {
            for (int j = s2.length() - 1; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    //相等的时候不用删除
                    dp[i][j] = dp[i + 1][j + 1];
                } else {
                    //不想等的时候删除代价小的那个，dp[i + 1][j] + s1.codePointAt(i)代表删除i之后的最小代价
                    dp[i][j] = Math.min(dp[i + 1][j] + s1.codePointAt(i), dp[i][j + 1] + s2.codePointAt(j));
                }
            }
        }
        return dp[0][0];
    }
}
