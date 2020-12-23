package dp;

/**
 * Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)
 * <p>
 * You have the following 3 operations permitted on a word:
 * <p>
 * a) Insert a character
 * b) Delete a character
 * c) Replace a character
 * <p>
 * Created by DrunkPiano on 2017/2/27.
 * <p>
 * dp[i][j]表示从word1的前i位到word2的前j位的min distance
 * word1[i] == word2[j]时，dp[i][j] = dp[i-1][j-1]
 * word1[i] != word2[j]时，if insert word2[j] for word1, dp[i][j] = dp[i-1][j] + 1
 * word1[i] != word2[j]时，if insert word1[i] for word2, dp[i][j] = dp[i][j-1] + 1
 */

public class EditDistance {
    /**
     * 题意：求编辑距离。
     * 解法：dp。
     * dp[i][j]代表word1的前i位和word2的前j位的距离。i,j为0时代表空字符串。注意要初始化第一行第一列。
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int [][] dp = new int [m + 1][n + 1]; // word1的前i位和word2的前j位的距离。i,j为0时代表空字符串。
        for (int i = 0; i <= m; i++) dp[i][0] = i;
        for (int i = 0; i <= n; i++) dp[0][i] = i;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
            }
        }
        return dp[m][n];
    }
}
