package easy;

/**
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
 * <p>
 * Example 1:
 * Input: "aba"
 * Output: True
 * Example 2:
 * Input: "abca"
 * Output: True
 * Explanation: You could delete the character 'c'.
 * Note:
 * The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
 * 20191005
 */
public class ValidPalindromeII {
    /**
     * 题意：最多remove一个字符是否能成为palindrome。
     * 这题非常好，首先brute force会超时，于是想到2 pointers, 左边或者右边有个tolerance可以跳过一次
     * 但是我写的做法是不对的，不能仅仅因为l + 1 和 r上的char一样就这么执行下去；而是要两种情况都检测一下。比如：axb....xba
     * 首先，正确做法：
     */
    public boolean isPalindromeRange(String s, int i, int j) {
        for (int k = i; k <= i + (j - i) / 2; k++) {
            if (s.charAt(k) != s.charAt(j - k + i)) return false;
        }
        return true;
    }

    public boolean validPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                int j = s.length() - 1 - i;
                return (isPalindromeRange(s, i + 1, j) ||
                        isPalindromeRange(s, i, j - 1));
            }
        }
        return true;
    }


    /**
     * 以下是我的错误做法
     */
    public boolean validPalindrome__WA(String s) {
        int l = 0, r = s.length() - 1;
        boolean used = false;
        while (l < r) {
            if (s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            } else if (!used && l + 1 <= r && s.charAt(l + 1) == s.charAt(r)) {
                used = true;
                l += 2;
                r--;
            } else if (!used && l + 1 <= r && s.charAt(l) == s.charAt(r - 1)) {
                used = true;
                l++;
                r -= 2;
            } else return false;
        }
        return true;
    }
}
