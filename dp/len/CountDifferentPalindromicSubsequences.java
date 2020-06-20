package dp.len;

/**
 * 给定一个字符串 S，找出 S 中不同的非空回文子序列个数，并返回该数字与 10^9 + 7 的模。
 * 通过从 S 中删除 0 个或多个字符来获得子序列。
 * 如果一个字符序列与它反转后的字符序列一致，那么它是回文字符序列。
 * 如果对于某个  i，A_i != B_i，那么 A_1, A_2, ... 和 B_1, B_2, ... 这两个字符序列是不同的。
 * 示例 1：
 * 输入：
 * S = 'bccb'
 * 输出：6
 * 解释：
 * 6 个不同的非空回文子字符序列分别为：'b', 'c', 'bb', 'cc', 'bcb', 'bccb'。
 * 注意：'bcb' 虽然出现两次但仅计数一次。
 * 20200619
 */
public class CountDifferentPalindromicSubsequences {
    /**
     * 题意：求给定字符串s的不同的非空回文子序列个数。
     * 解法：区间DP。我觉得它的转移还是比较难以想象到的。。尤其是A[i]==A[j]，也就是两侧字母相等的情况，需要3种情况讨论。
     * 建议看这个人的解释，非常清晰：https://leetcode-cn.com/problems/count-different-palindromic-subsequences/solution/qu-jian-dp-by-375c-2/
     * dp[i][j]表示第i个字符和第j个字符之间包含的回文子串个数，主要i>j的dp元素需要置为0。
     * dp[i][j] = {
     * 1. s[i] != s[j]时, dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1]
     * 2. s[i] == s[j]时, dp[i + 1][j - 1] * 2, 在所有的回文子串前后都加上s[i]。如果s[i..j]之间的子串有字符与s[i]相同，则会产生重复的回文子串，所以此时需要分情况进行调整
     * a. 如果没有重复与s[i]重复的字符，则会多产生两个回文子串，s[i]与s[ij]，如‘a’和‘aa’
     * b. 如果只有一个重复的字符，，则只会多产生一个回文子串，s[ij]，即'aa'
     * c. 如果存在多个，需要分别从起止点查找，找到第一个前后重复的字符l和r，分别用末尾字符替换后，会产生s[l][r]个重复的回文子串，需要减掉
     * <p>
     * 另外可参考这个人的解释，我代码模仿他的https://leetcode-cn.com/problems/count-different-palindromic-subsequences/solution/dong-tai-gui-hua-dui-qu-jian-dpfen-lei-tao-lun-by-/
     */
    public int countPalindromicSubsequences(String s) {
        int n = s.length();
        int[][] dp = new int[n][n]; // dp[i][j]表示[i,j]下标范围内的回文子序列数量
        char[] ch = s.toCharArray();
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;   // Consider the test case "a", "b" "c"...
        }
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;
                if (ch[i] != ch[j]) {
                    dp[i][j] = dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1];
                } else {
                    dp[i][j] = dp[i + 1][j - 1] * 2; // 小区间一份，外面套的大区间又有一份 所以*2
                    int l = i + 1, r = j - 1;
                    while (l <= r && ch[l] != ch[i]) l += 1;
                    while (l <= r && ch[r] != ch[i]) r -= 1;
                    if (l > r) dp[i][j] += 2; // "bcab"
                    else if (l == r) dp[i][j] += 1; // "bcbcb"
                    else dp[i][j] -= dp[l + 1][r - 1]; // l < r , "bbcabb"
                }
                dp[i][j] = dp[i][j] < 0 ? dp[i][j] + 1000000007 : dp[i][j] % 1000000007; // 注意这个技巧，<0 要加上mod。以前在哪儿也见过..
            }
        }
        return dp[0][n - 1];
    }
}
