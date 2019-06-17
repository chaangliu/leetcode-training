package dp;

import java.util.Arrays;

/**
 * Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences.  If multiple answers exist, you may return any of them.
 * <p>
 * (A string S is a subsequence of string T if deleting some number of characters from T (possibly 0, and the characters are chosen anywhere from T) results in the string S.)
 * Example 1:
 * <p>
 * Input: str1 = "abac", str2 = "cab"
 * Output: "cabac"
 * Explanation:
 * str1 = "abac" is a substring of "cabac" because we can delete the first "c".
 * str2 = "cab" is a substring of "cabac" because we can delete the last "ac".
 * The answer provided is the shortest such string that satisfies these properties.
 * Note:
 * <p>
 * 1 <= str1.length, str2.length <= 1000
 * str1 and str2 consist of lowercase English letters.
 * <p>
 * 20190617
 */
public class ShortestCommonSupersequence {
    /**
     * 这题的做法是先求出LCS，然后利用三个指针从头开始遍历，把共同属于LCS的部分单独加到结果里。因为只求一个满足的就行，所以这么做可行。
     */
    public String shortestCommonSupersequence(String str1, String str2) {
        String lcs = lcs(str1, str2);
        int i = 0, j = 0, k = 0;
        StringBuilder sb = new StringBuilder();
        while (k < lcs.length()) {
            while (str1.charAt(i) != lcs.charAt(k)) {
                sb.append(str1.charAt(i++));
            }
            while (str2.charAt(j) != lcs.charAt(k)) {
                sb.append(str2.charAt(j++));
            }
            sb.append(lcs.charAt(k++));
            i++;
            j++;
        }
        sb.append(str1.substring(i));
        sb.append(str2.substring(j));
        return sb.toString();
    }

    private String lcs(String str1, String str2) {
        String[][] dp = new String[str1.length() + 1][str2.length() + 1];
        for (int i = 0; i < dp.length; i++) Arrays.fill(dp[i], "");//把二维数组的所有cell都初始化成空字符串""
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + str1.charAt(i - 1);
                } else {
                    dp[i][j] = dp[i - 1][j].length() > dp[i][j - 1].length() ? dp[i - 1][j] : dp[i][j - 1];
                }
            }
        }
        return dp[str1.length()][str2.length()];
    }
}
