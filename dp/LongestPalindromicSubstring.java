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
    //    public String longestPalindrome(String s) {
//        if (s.length() == 0) return null;
//        int maxLength = 1;
//        String longestPalindrome = s.substring(0, 1);
//        for (int i = 0; i < s.length(); i++) {
//            String temp = helper(s, i, i);
//            if (temp.length() > longestPalindrome.length()) {
//                longestPalindrome = temp;
//            }
//        }
//        for (int i = 0; i < s.length(); i++) {
//            String temp = helper(s, i, i + 1);
//            if (temp.length() > longestPalindrome.length()) {
//                longestPalindrome = temp;
//            }
//        }
//
//        return longestPalindrome;
//    }
//
//    private String helper(String s, int begin, int end) {
//        while (begin >= 0 && end <= s.length() - 1 && s.charAt(begin) == s.charAt(end)) {
//            begin--;
//            end++;
//        }
//        return s.substring(begin+1, end);
//    }
    public String longestPalindrome(String s) {
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

    public static void main(String args[]) {
        LongestPalindromicSubstring longestPalindromicSubstring = new LongestPalindromicSubstring();
//        System.out.println(longestPalindromicSubstring.longestPalindrome("babad"));
        System.out.println(longestPalindromicSubstring.longestPalindrome("aaaa"));
//        String s = "babad";
//        System.out.println(s.substring(0,2));
    }
}
