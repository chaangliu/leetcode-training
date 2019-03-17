package slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 * <p>
 * Example:
 * <p>
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 * Note:
 * <p>
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 * <p>
 * <p>
 *
 * <p>
 * 20190314
 */


public class MinimumWindowSubstring {
    /**
     * sliding window标准套路。都按这个来就行了。
     * 类似的题目有：
     * * https://leetcode.com/problems/minimum-window-substring/
     * * https://leetcode.com/problems/longest-substring-without-repeating-characters/
     * * https://leetcode.com/problems/substring-with-concatenation-of-all-words/
     * * https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/
     * * https://leetcode.com/problems/find-all-anagrams-in-a-string/
     * 模板可参考：https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/92007/Sliding-Window-algorithm-template-to-solve-all-the-Leetcode-substring-search-problem.
     */
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0) return "";
        String res = "";
        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int left = 0, right = 0, count = map.size();
        while (right < s.length()) {
            //expand
            char rChar = s.charAt(right);
            if (map.containsKey(rChar)) {
                map.put(rChar, map.get(rChar) - 1);//map的key只包含t中有的字母。value是s中对应字母减去t中对应字母的差值，可以为负数。
                if (map.get(rChar) == 0) count--;//count表示T中unique的字母在S中数量一致的个数。大于等于零。
            }
            right++;

            //shrink
            while (count == 0) {
                if (res.length() == 0 || right - left < res.length()) res = s.substring(left, right);
                char lChar = s.charAt(left);
                if (map.containsKey(lChar)) {
                    map.put(lChar, map.get(lChar) + 1);
                    if (map.get(lChar) > 0) count++;
                }
                left++;
            }
        }
        return res;
    }
}
