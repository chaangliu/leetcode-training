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
    public int minDistance(String word1, String word2) {
        if (word1.length() == 0) return word2.length();
        if (word2.length() == 0) return word1.length();

        //多申请了1行1列存放不加入word1或word2的情况(dp[i][0] = i)
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i < word1.length() + 1; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j < word2.length() + 1; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i < word1.length() + 1; i++)
            for (int j = 1; j < word2.length() + 1; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + 1);
                }
            }
        return dp[word1.length()][word2.length()];
    }
}
