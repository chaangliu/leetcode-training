package array;

/**
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 * For example,
 * Given:
 * s1 = "aabcc",
 * s2 = "dbbca",
 * When s3 = "aadbbcbcac", return true.
 * When s3 = "aadbbbaccc", return false.
 * <p>
 * Created by DrunkPiano on 2017/2/26.
 */

public class InterleavingString {

    /**
     * 题意：判断s3能否由s1和s2交错组成。
     * approach1. dp
     * dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1) || dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
     * 20190505review
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        //dp[i][j]表示，s1中前i个元素和s2的前j个元素可以组成s3中的前i+j个元素。
        if (s1 == null) return s2.equals(s3);
        if (s2 == null) return s1.equals(s3);
        if (s1.length() + s2.length() != s3.length()) return false;
        boolean dp[][] = new boolean[s1.length() + 1][s2.length() + 1];
        ///注意，这些初始化都可以放到两层的for循环当中去做，这里为了清晰先不放进去
        dp[0][0] = true;
        for (int i = 1; i <= s1.length(); i++) {
            //dp[i][j] = true的条件：如果s2是空字符串，那么s3的前i位要和s1的前i位相同
            dp[i][0] = dp[i - 1][0] && s3.charAt(i - 1) == s1.charAt(i - 1);
        }
        for (int j = 1; j <= s2.length(); j++) {
            dp[0][j] = dp[0][j - 1] && s3.charAt(j - 1) == s2.charAt(j - 1);
        }
        for (int i = 1; i <= s1.length(); i++)
            for (int j = 1; j <= s2.length(); j++) {
                //分别判断把s1[i]、s2[j]加进来是否满足interleaving条件，满足其一即可
                dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)
                        || dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
            }
        return dp[s1.length()][s2.length()];
    }

    boolean interleave = false;
    char[] chars1, chars2, chars3;
    int len1, len2, len3;
    boolean[][] visited;

    /**
     * dfs with memo
     */
    public boolean isInterleave_(String s1, String s2, String s3) {
        len1 = s1.length();
        len2 = s2.length();
        len3 = s3.length();
        if (len1 + len2 != len3) return false;
        visited = new boolean[len1 + 1][len2 + 1];
        chars1 = s1.toCharArray();
        chars2 = s2.toCharArray();
        chars3 = s3.toCharArray();
        dfs(0, 0, 0);
        return interleave;
    }

    private void dfs(int i, int j, int k) {
        if (k == len3) interleave = true;
        if (interleave || visited[i][j]) return;
        if (i < len1 && chars1[i] == chars3[k]) dfs(i + 1, j, k + 1);
        if (j < len2 && chars2[j] == chars3[k]) dfs(i, j + 1, k + 1);
        visited[i][j] = true;
    }
}
