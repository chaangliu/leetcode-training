package array;

/**
 * Given a string S and a string T, count the number of distinct subsequences of T in S.
 * <p>
 * A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
 * <p>
 * Here is an example:
 * S = "rabbbit", T = "rabbit"
 * <p>
 * Return 3.
 * Created by DrunkPiano on 2017/3/1.
 * <p>
 * <p>
 * <p>
 * <p>
 * dp[i][j]表示s的前i位和t的前j位的最长公共子序列长度。
 * <p>
 * 1. dp[i][j] = 0, if(i == 0) or (j == 0)
 * 2. dp[i][j] = dp[i-1][j-1] + 1, if(s[i] == t[j])
 * 3. dp[i][j] = max{dp[i][j-1] , dp[i-1][j] } , if(s[i] != t[j])
 */

public class DistinctSubsequences {
    int maxLen = 0;
    int count = 0;

    public int numDistinct(String s, String t) {
        if (s.length() == 0 || t.length() == 0) return 0;
        int dp[][] = new int[s.length() + 1][t.length() + 1];
        for (int i = 0; i < s.length() + 1; i++) {
            dp[i][0] = 1;

        }
        for (int i = 1; i < s.length() + 1; i++)
            for (int j = 1; j < t.length() + 1; j++) {
                if (s.charAt(i - 1) != t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                }
            }
        return dp[s.length()][t.length()];
    }



    public static void main(String args[]) {
        DistinctSubsequences distinctSubsequences = new DistinctSubsequences();
        System.out.println(distinctSubsequences.numDistinct("rabbbit", "rabbit"));
//        for(int i = 0 ; i < 7 ; i++)
//            System.out.println();
    }
}
