package greedy;

/**
 * Given a string s and an integer k. You should construct k non-empty palindrome strings using all the characters in s.
 * Return True if you can use all the characters in s to construct k palindrome strings or False otherwise.
 * Example 1:
 * Input: s = "annabelle", k = 2
 * Output: true
 * Explanation: You can construct two palindromes using all characters in s.
 * Some possible constructions "anna" + "elble", "anbna" + "elle", "anellena" + "b"
 * Example 2:
 *
 * Input: s = "leetcode", k = 3
 * Output: false
 * Explanation: It is impossible to construct 3 palindromes using all the characters of s.
 * Example 3:
 *
 * Input: s = "true", k = 4
 * Output: true
 * Explanation: The only possible solution is to put each character in a separate string.
 * Example 4:
 *
 * Input: s = "yzyzyzyzyzyzyzy", k = 2
 * Output: true
 * Explanation: Simply you can put all z's in one string and all y's in the other string. Both strings will be palindrome.
 * Example 5:
 *
 * Input: s = "cr", k = 7
 * Output: false
 * Explanation: We don't have enough characters in s to construct 7 palindromes.
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * All characters in s are lower-case English letters.
 * 1 <= k <= 10^5
 */
public class ConstructKPalindromeStrings {
    /**
     * 题意：给你一个只包含小写字母的string，用完里面的字母，问能不能构造k个palindromes？
     * 分析：
     * 1. 如果k > s.length(), 不能
     * 2. 如果s中出现单次的字母数量>k,不能
     * 3. 否则一定能构造成k个回文，只要每个回文串携带一个出现单次的字母即可
     **/
    public boolean canConstruct(String s, int k) {
        if (k > s.length()) return false;
        int[] map = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map[c - 'a']++;
        }
        int oddCnt = 0;
        for (int num : map) if ((num & 1) == 1) oddCnt++;
        return oddCnt <= k;
    }

    /**
     * lee的one pass做法，每次都更新odd数量
     */
    public boolean canConstruct_(String s, int k) {
        int odd = 0, n = s.length(), count[] = new int[26];
        for (int i = 0; i < n; ++i) {
            count[s.charAt(i) - 'a'] ^= 1;
            odd += count[s.charAt(i) - 'a'] > 0 ? 1 : -1;
        }
        return odd <= k && k <= n;
    }
}
