package array;

/**
 * Given a palindromic string palindrome, replace exactly one character by any lowercase English letter so that the string becomes the lexicographically smallest possible string that isn't a palindrome.
 * After doing so, return the final string.  If there is no way to do so, return the empty string.
 * Example 1:
 * Input: palindrome = "abccba"
 * Output: "aaccba"
 * Example 2:
 * Input: palindrome = "a"
 * Output: ""
 * Constraints:
 * 1 <= palindrome.length <= 1000
 * palindrome consists of only lowercase English letters.
 * 20200126
 */
public class BreakaPalindrome {
    /**
     * 题意：给你一个palindrome，让你replace一个character让它变成非palindrome。
     * 解法：这题就靠观察，就是有些corner case难想到。
     */
    public String breakPalindrome(String p) {
        int n = p.length();
        if (n == 1) return "";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            if (p.charAt(i) == 'a') {
                sb.append('a');
            } else {
                sb.append('a');
                if (!(n % 2 == 1 && i == n / 2)) return sb.toString() + p.substring(i + 1);//aaa
            }
        }
        return p.substring(0, n - 1) + 'b';
    }

    /**
     * lee的答案
     */
    public String breakPalindrome_(String palindrome) {
        char[] s = palindrome.toCharArray();
        int n = s.length;

        for (int i = 0; i < n / 2; i++) {
            if (s[i] != 'a') {
                s[i] = 'a';
                return String.valueOf(s);
            }
        }
        s[n - 1] = 'b'; //if all 'a'
        return n < 2 ? "" : String.valueOf(s);
    }
}
