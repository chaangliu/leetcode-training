package dp;

/**
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 * <p>
 * Example:
 * <p>
 * Input: "babad"
 * <p>
 * Output: "bab"
 * <p>
 * Note: "aba" is also a valid answer.
 * Example:
 * <p>
 * Input: "cbbd"
 * <p>
 * Output: "bb"
 * Created by DrunkPiano on 2017/2/28.
 */

public class LongestPalindromicSubstring {
    /**
     * approach1. dp
     * 这题跟LongestPalindromicSubsequence那题的转移方程几乎一致，都要用到i + 1 j - 1，所以做法也是外层从后往前循环
     * dp[i][j] = s[i] == s[j] && (dp[i + 1][j - 1] ||  i + 1 == j) // i + 1 == j这个条件容易忽略，例如abbd的case
     * <p>
     * 20190406
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";
        boolean[][] dp = new boolean[s.length()][s.length()];
        int start = 0, end = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i][i] = true;
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j) && (dp[i + 1][j - 1] || i + 1 == j)) {
                    dp[i][j] = true;
                    if (j - i + 1 > end - start + 1) {
                        start = i;
                        end = j;
                    }
                }
            }
        }
        return s.substring(start, end + 1);
    }

    public String longestPalindrome__2017(String s) {
        if (s == null || s.length() <= 1)
            return s;
        int len = s.length();
        int maxLen = 1;
        boolean[][] dp = new boolean[len][len];
        String longest = null;
        for (int i = s.length() - 1; i >= 0; i--)
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;

                    if (j - i + 1 > maxLen) {
                        maxLen = j - i + 1;
                        longest = s.substring(i, j + 1);
                    }
                }
            }
        return longest;
    }

    /**
     * Approach 2: Expand Around Center，跟647. Palindromic Substrings一样思路
     * O(n2)，Constant Space，思路比较简单，但是其实空间占用还比DP小。
     * <p>
     * 代码如下。不够熟练，WA了一次。
     */
    String res = "";
    int maxLen = 0;

    public String longestPalindrome__(String s) {
        if (s == null || s.length() == 0) return "";
        for (int i = 0; i < s.length(); i++) {
            expand(i, i, s);
            if (i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1))
                expand(i, i + 1, s);
        }
        return res;
    }

    private void expand(int l, int r, String s) {
        while (l - 1 >= 0 && r + 1 < s.length() && s.charAt(l - 1) == s.charAt(r + 1)) {
            l--;
            r++;
        }
        if (r - l + 1 > maxLen) {
            maxLen = r - l + 1;
            res = s.substring(l, r + 1);
            System.out.println(res);
        }
    }


    public static void main(String args[]) {
        LongestPalindromicSubstring longestPalindromicSubstring = new LongestPalindromicSubstring();
        System.out.println(longestPalindromicSubstring.longestPalindrome("aaaa"));
    }
}
