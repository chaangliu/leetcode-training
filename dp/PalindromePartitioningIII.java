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
    public int palindromePartition__DP(String s, int k) {
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
     * (这个也能用dp来预处理，dp[i + 1][j - 1]可以得到dp[i][j]，见initCost()函数)
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


    /**
     * DFS with memo,跟largest sum of averages很像，但是超时了
     */
    public int palindromePartition(String s, int k) {
        int[][] memo = new int[s.length()][k + 1];
        int[][] c = initCost(s);
        for (int i = 0; i < memo.length; i++)
            for (int j = 0; j < memo[0].length; j++) memo[i][j] = Integer.MAX_VALUE / 2;
        for (int i = 0; i < s.length(); i++) {
            //memo[i][1] = calc(s, 0 + 1, i + 1);
            memo[i][1] = c[0][i];
        }
        return dfs(s, s.length() - 1, k, memo, c);
    }

    /**
     * dfs返回s的前n个字符分成K个palindrome需要的最小代价
     */
    private int dfs(String s, int n, int K, int[][] memo, int[][] c) {
        if (memo[n][K] != Integer.MAX_VALUE / 2) return memo[n][K];
        for (int i = n; i > 0; i--) {
            //[i, n]之间做成一个palindrome需要的cost
            int cost = c[i][n];
            memo[n][K] = Math.min(memo[n][K], dfs(s, i - 1, K - 1, memo, c) + cost);
        }
        return memo[n][K];
    }

    private int[][] initCost(String s) {
        int n = s.length();
        int[][] cost = new int[n][n];
        for (int len = 2; len <= n; len++) {
            for (int i = 0, j = len - 1; j < n; ++i, ++j) {
                cost[i][j] = (s.charAt(i) != s.charAt(j) ? 1 : 0) + cost[i + 1][j - 1];
            }
        }
        return cost;
    }

    public static void main(String args[]) {
        System.out.println(new PalindromePartitioningIII().palindromePartition("aabbc", 3));
    }
}
