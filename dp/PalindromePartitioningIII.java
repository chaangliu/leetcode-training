package dp;

/**
 * You are given a string s containing lowercase letters and an integer k. You need to :
 * <p>
 * First, change some characters of s to other lowercase English letters.
 * Then divide s into k non-empty disjoint substrings such that each substring is palindrome.
 * Return the minimal number of characters that you need to change to divide the string.
 * Example 1:
 * Input: s = "abc", k = 2
 * Output: 1
 * Explanation: You can split the string into "ab" and "c", and change 1 character in "ab" to make it palindrome.
 * Example 2:
 * <p>
 * Input: s = "aabbc", k = 3
 * Output: 0
 * Explanation: You can split the string into "aa", "bb" and "c", all of them are palindrome.
 * Example 3:
 * <p>
 * Input: s = "leetcode", k = 8
 * Output: 0
 * Constraints:
 * 1 <= k <= s.length <= 100.
 * s only contains lowercase English letters.
 * 20191204
 */
public class PalindromePartitioningIII {
    /**
     * 题意：给你一个string，问你如果要达到把它分成k个都是palindrome的subarray，需要修改几个字母。
     * 我是模仿WNJXYK的视频写的，他思路还挺清晰的，但这种做法对我还说还是比较陌生。花花的视频介绍了这种题的套路，今天晚些可以看看。
     * 解法：dp，dp[i][j]表示前i个字母分成j个palindrome的最小代价(修改的字母数量)。
     **/
    public int palindromePartition(String s, int k) {
        int n = s.length();
        Integer[][] dp = new Integer[n + 1][k + 1];
        dp[0][0] = 0;//前0个字母分成0份
        for (int i = 0; i < n; i++) {//枚举前i个字母(实际上是从[1,n], 最后一个字母不用枚举)
            for (int j = 0; j < k; j++) {//枚举分成几个palindrome
                if (dp[i][j] == null) continue;
                for (int m = i + 1; m <= n; m++) {//枚举新增的一个part的长度
                    //计算把[i + 1, m]变成palindrome需要的花费
                    int cost = calc(s, i + 1, m);
                    if (dp[m][j + 1] == null || dp[i][j] + cost < dp[m][j + 1])
                        dp[m][j + 1] = dp[i][j] + cost;
                }
            }
        }
        return dp[n][k];
    }

    /**
     * 计算把[i,j]变成palindrome需要的cost
     **/
    private int calc(String s, int l, int r) {
        int res = 0;
        l -= 1;
        r -= 1;
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) res++;
        }
        return res;
    }
}
