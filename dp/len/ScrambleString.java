package dp.len;

/**
 * 给定一个字符串 s1，我们可以把它递归地分割成两个非空子字符串，从而将其表示为二叉树。
 * 下图是字符串 s1 = "great" 的一种可能的表示形式。
 *
 *     great
 *    /    \
 *   gr    eat
 *  / \    /  \
 * g   r  e   at
 *            / \
 *           a   t
 * 在扰乱这个字符串的过程中，我们可以挑选任何一个非叶节点，然后交换它的两个子节点。
 * 例如，如果我们挑选非叶节点 "gr" ，交换它的两个子节点，将会产生扰乱字符串 "rgeat" 。
 *
 *     rgeat
 *    /    \
 *   rg    eat
 *  / \    /  \
 * r   g  e   at
 *            / \
 *           a   t
 * 我们将 "rgeat” 称作 "great" 的一个扰乱字符串。
 *
 * 同样地，如果我们继续交换节点 "eat" 和 "at" 的子节点，将会产生另一个新的扰乱字符串 "rgtae" 。
 *
 *     rgtae
 *    /    \
 *   rg    tae
 *  / \    /  \
 * r   g  ta  e
 *        / \
 *       t   a
 * 我们将 "rgtae” 称作 "great" 的一个扰乱字符串。
 *
 * 给出两个长度相等的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串。
 *
 * 示例 1:
 *
 * 输入: s1 = "great", s2 = "rgeat"
 * 输出: true
 * 示例 2:
 * 输入: s1 = "abcde", s2 = "caebd"
 * 输出: false
 * ----------------------------------------------------
 * 类似题目：
 * 5. 最长回文子串
 * 516. 最长回文子序列
 * 312. 戳气球
 * 1246. 删除回文子数组
 * ----------------------------------------------------
 * 20200526
 */
public class ScrambleString {
    /**
     * 题意：一个字符串，可以递归地把它分割成2部分，并且这两部分可以互换。现在给你两个这样的字符串，问一个可否由另一个变换而来。
     * 解法1：区间DP
     * dp[i][j][len]表示从s1的i开始，s2的j开始的len长度可以互换得来。枚举区间len，用k分割len，
     * dp[i][j][len] = dp[i][j][k] && dp[i+k][j+k][len - k] || dp[i][j + len - k][k] && dp[i + k][j][len - k]
     * 参考：https://leetcode-cn.com/problems/scramble-string/solution/miao-dong-de-qu-jian-xing-dpsi-lu-by-sha-yu-la-jia/
     **/
    public boolean isScramble(String s1, String s2) {
        int n1 = s1.length(), n2 = s2.length();
        if (n1 != n2) return false;
        boolean[][][] dp = new boolean[n1][n1][n1 + 1];
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n1; j++) {
                if (s1.charAt(i) == s2.charAt(j)) dp[i][j][1] = true;
            }
        }
        for (int len = 2; len <= n1; len++) { //<=
            for (int i = 0; i <= n1 - len; i++) { //<=
                for (int j = 0; j <= n2 - len; j++) {
                    for (int k = 1; k < len; k++) {
                        if (dp[i][j][k] && dp[i + k][j + k][len - k]) {
                            dp[i][j][len] = true;
                            break;
                        }
                        if (dp[i][j + len - k][k] && dp[i + k][j][len - k]) {
                            dp[i][j][len] = true;
                            break;
                        }
                    }
                }
            }
        }
        return dp[0][0][n1];
    }
}
