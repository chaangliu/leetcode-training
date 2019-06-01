package dp;

import java.util.Arrays;


/**
 * Given a string S, count the number of distinct, non-empty subsequences of S .
 * <p>
 * Since the result may be large, return the answer modulo 10^9 + 7.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: "abc"
 * Output: 7
 * Explanation: The 7 distinct subsequences are "a", "b", "c", "ab", "ac", "bc", and "abc".
 * Example 2:
 * <p>
 * Input: "aba"
 * Output: 6
 * Explanation: The 6 distinct subsequences are "a", "b", "ab", "ba", "aa" and "aba".
 * Example 3:
 * <p>
 * Input: "aaa"
 * Output: 3
 * Explanation: The 3 distinct subsequences are "a", "aa" and "aaa".
 * Note:
 * <p>
 * S contains only lowercase letters.
 * 1 <= S.length <= 2000
 * <p>
 * 20190601
 */
public class DistinctSubsequencesII {
    /**
     * 这题很难，看答案看了一个小时。
     * 这题两个难点，
     * 1.  dp[i + 1] = dp[i] * 2 要理解，因为i + 1可以在i的基础上都加上1位，所以*2
     * 2.  如何去重？ 记录上一次出现重复字母时候的位置idx，它前一位的子序列个数dp[idx - 1]就是重复的数量。用abab理解。
     */
    public int distinctSubseqII(String S) {
        int MOD = 1_000_000_007;
        int N = S.length();
        int[] dp = new int[N + 1];
        dp[0] = 1;
        int[] last = new int[26];
        Arrays.fill(last, -1);
        for (int i = 0; i < N; ++i) {
            int x = S.charAt(i) - 'a';
            dp[i + 1] = dp[i] * 2 % MOD;
            if (last[x] >= 0)
                dp[i + 1] -= dp[last[x]];
            dp[i + 1] %= MOD;
            //last[x]为下一次遇到相同字母的时候记录上一次出现这个字母的index，下一次如果重复出现这个字母，就把上一次出现这个字母时[前面一位]的substring的个数减掉
            //比如abab，第二次出现b的时候，要把第一次出现b时前面一位a的子序列个数(2个，""和"a")减掉
            last[x] = i;
        }
        dp[N]--;
        if (dp[N] < 0) dp[N] += MOD;
        return dp[N];
    }
}
