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

    /**
     * 20190503review
     * 我感觉这题可以看做求LCS的个数，但是真正做的时候发现套用LCS的转移方程是行不通的，因为无法统计LCS正好匹配到之前有多少种重复情况满足。
     */
    public int numDistinct(String s, String t) {
        if (s.length() == 0 || t.length() == 0) return 0;
        int dp[][] = new int[s.length() + 1][t.length() + 1];
        //如果t.length() == 0，那么对于s的前i位结果都是1
        for (int i = 0; i < s.length() + 1; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < s.length() + 1; i++)
            for (int j = 1; j < t.length() + 1; j++) {
                if (s.charAt(i - 1) != t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    //dp[i - 1][j - 1]的意思是，既然s[i]和t[j]相等，那么在同时加上这两个数之前的结果都可以算进去
                    //dp[i - 1][j]的意思是，不考虑加上s[i]，但是加入t[j]，满足条件的个数。
                    //至于为什么不计算dp[i][j - 1]，因为是求s中有多少个子序列可以组成t，t是固定的。
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                }
            }
        return dp[s.length()][t.length()];
    }

    public static void main(String args[]) {
        DistinctSubsequences distinctSubsequences = new DistinctSubsequences();
        System.out.println(distinctSubsequences.numDistinct("rabbbit", "rabbit"));
    }
}
