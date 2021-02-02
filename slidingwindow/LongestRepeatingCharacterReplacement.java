package slidingwindow;

import java.util.HashMap;

/**
 * Given a string that consists of only uppercase English letters, you can replace any letter in the string with another letter at most k times. Find the length of a longest substring containing all repeating letters you can get after performing the above operations.
 * <p>
 * Note:
 * Both the string's length and k will not exceed 104.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * s = "ABAB", k = 2
 * <p>
 * Output:
 * 4
 * <p>
 * Explanation:
 * Replace the two 'A's with two 'B's or vice versa.
 * Example 2:
 * <p>
 * Input:
 * s = "AABABBA", k = 1
 * <p>
 * Output:
 * 4
 * <p>
 * Explanation:
 * Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 * The substring "BBBB" has the longest repeating letters, which is 4.
 * <p>
 * 20190327
 */
public class LongestRepeatingCharacterReplacement {
    /**
     * 题意：给你一个字符串s和k个额度，让你随意替换s中的char最多k次，问替换后最多能组成的全部重复字符的字符串长度是多少。
     * 这题我一开始被example误导，理解错了，以为只能替换同一个字母；实际可以替换多个不同字母。
     * 思路是：记录window中出现次数最多的数Target，把其他的数都替换成它；
     * while (right - left + 1 - targetCount > k) -> left ++; else right++;
     * 维护window中出现次数最多的数的出现次数maxCharCount，这样就不用在shrink或者expand的时候记录新的target了！因为shrink的时候maxCharCount不会变化
     */
    public int characterReplacement(String s, int k) {
        int res = 0, left = 0, right = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        if (s == null || s.length() == 0) return 0;
        int maxCharCount = 0;
        while (right < s.length()) {
            char rChar = s.charAt(right);
            map.put(rChar, map.getOrDefault(rChar, 0) + 1);
            if (map.get(rChar) > maxCharCount) {
                maxCharCount = map.get(rChar);
            }
            while (left < right && right - left + 1 - maxCharCount > k) {
                map.put(s.charAt(left), map.get(s.charAt(left)) - 1); // shrink
                left++;
            }
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }

    /**
     * 如果不用res，可以直接用right - left，这样最后left到right之间的字符串虽然不是要找的，但是长度一样
     */
    public int characterReplacement_Official(String s, int k) {
        int[] num = new int[26];
        int n = s.length();
        int maxn = 0;
        int left = 0, right = 0;
        while (right < n) {
            num[s.charAt(right) - 'A']++;
            maxn = Math.max(maxn, num[s.charAt(right) - 'A']);
            if (right - left + 1 - maxn > k) {
                num[s.charAt(left) - 'A']--;
                left++;
            }
            right++;
        }
        return right - left;
    }

    /**
     * 如果维护出现次数最多的target，是需要寻找新的target的
     */
    public int characterReplacement_____(String s, int k) {
        int res = 0, left = 0, right = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        if (s == null || s.length() == 0) return 0;
        char target = s.charAt(0);
        while (right < s.length()) {
            char rChar = s.charAt(right);
            map.put(rChar, map.getOrDefault(rChar, 0) + 1);
            if (map.get(rChar) > map.get(target)) {
                target = rChar;
            }
            while (left < right && right - left + 1 - map.get(target) > k) {
                map.put(s.charAt(left), map.get(s.charAt(left)) - 1);
                for (int i = 0; i < 26; i++) {
                    char c = (char) ('A' + i);
                    if (map.get(target) < map.getOrDefault(c, 0)) {
                        target = c;
                    }
                }
                left++;
            }
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }
}
