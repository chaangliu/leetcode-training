package slidingwindow;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s and an integer k.
 * Return the maximum number of vowel letters in any substring of s with length k.
 * Vowel letters in English are (a, e, i, o, u).
 * Example 1:
 * Input: s = "abciiidef", k = 3
 * Output: 3
 * Explanation: The substring "iii" contains 3 vowel letters.
 * Example 2:
 * <p>
 * Input: s = "aeiou", k = 2
 * Output: 2
 * Explanation: Any substring of length 2 contains 2 vowels.
 * Example 3:
 * <p>
 * Input: s = "leetcode", k = 3
 * Output: 2
 * Explanation: "lee", "eet" and "ode" contain 2 vowels.
 * Example 4:
 * <p>
 * Input: s = "rhythms", k = 4
 * Output: 0
 * Explanation: We can see that s doesn't have any vowel letters.
 * Example 5:
 * <p>
 * Input: s = "tryhard", k = 4
 * Output: 1
 * Constraints:
 * 1 <= s.length <= 10^5
 * s consists of lowercase English letters.
 * 1 <= k <= s.length
 * 20200525
 */
public class MaximumNumberofVowelsinaSubstringofGivenLength {
    /**
     * 题意：给你一个string，问最长的含有元音字母的子串的长度是多少。
     * 解法：sliding window（固定长度窗口）
     */
    public int maxVowels(String s, int k) {
        HashSet<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        int res, cnt = 0, n = s.length();
        for (int i = 0; i < k; i++) {
            if (set.contains(s.charAt(i))) {
                cnt++;
            }
        }
        res = cnt;
        for (int i = k; i < n; i++) {
            if (set.contains(s.charAt(i - k))) cnt--;
            if (set.contains(s.charAt(i))) cnt++;
            res = Math.max(cnt, res);
        }
        return res;
    }

    /**
     * rock的写法
     */
    public int maxVowels_(String s, int k) {
        int ans = 0;
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        //        var vowels = Set.of('a', 'e', 'i', 'o', 'u'); // Java 11 Collection factory method, credit to @Sithis
        for (int i = 0, winCnt = 0; i < s.length(); ++i) {
            if (vowels.contains(s.charAt(i))) {
                ++winCnt;
            }
            if (i >= k && vowels.contains(s.charAt(i - k))) {
                --winCnt;
            }
            ans = Math.max(winCnt, ans);
        }
        return ans;
    }
}
