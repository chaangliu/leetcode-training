package slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s, return the maximum number of ocurrences of any substring under the following rules:
 * The number of unique characters in the substring must be less than or equal to maxLetters.
 * The substring size must be between minSize and maxSize inclusive.
 * Example 1:
 * Input: s = "aababcaab", maxLetters = 2, minSize = 3, maxSize = 4
 * Output: 2
 * Explanation: Substring "aab" has 2 ocurrences in the original string.
 * It satisfies the conditions, 2 unique letters and size 3 (between minSize and maxSize).
 * Example 2:
 * <p>
 * Input: s = "aaaa", maxLetters = 1, minSize = 3, maxSize = 3
 * Output: 2
 * Explanation: Substring "aaa" occur 2 times in the string. It can overlap.
 * Example 3:
 * <p>
 * Input: s = "aabcabcab", maxLetters = 2, minSize = 2, maxSize = 3
 * Output: 3
 * Example 4:
 * Input: s = "abcde", maxLetters = 2, minSize = 3, maxSize = 3
 * Output: 0
 * Constraints:
 * 1 <= s.length <= 10^5
 * 1 <= maxLetters <= 26
 * 1 <= minSize <= maxSize <= min(26, s.length)
 * s only contains lowercase English letters.
 * 20191222
 */
public class MaximumNumberofOccurrencesofaSubstring {
    /**
     * 题意：给你一个string s, 找出[minSize, maxSize]长度的最多包含maxLetters个不同字母的子串出现的最大次数。
     * 这题我一拿到就觉得是sliding window。另外有个隐含条件，长度长的substring满足的话，长度短的一定满足。比如aab满足，那么aa一定满足。
     * 所以我们可以从minSize开始找，找到满足条件的可以立刻。
     */
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        for (int sz = minSize; sz <= maxSize; sz++) {
            Map<String, Integer> map = new HashMap<>();//subStr->occurrence
            int l = 0, r = sz - 1, n = s.length();
            Map<Character, Integer> cnt = new HashMap<>();
            for (int i = l; i <= r; i++) {
                cnt.put(s.charAt(i), cnt.getOrDefault(s.charAt(i), 0) + 1);//这儿key粗心写成了i，检查了20分钟
            }
            String sub = s.substring(l, r + 1);
            if (cnt.size() <= maxLetters)
                map.put(sub, map.getOrDefault(sub, 0) + 1);
            while (r + 1 < n) {
                r++;
                l++;
                cnt.put(s.charAt(r), cnt.getOrDefault(s.charAt(r), 0) + 1);
                cnt.put(s.charAt(l - 1), cnt.getOrDefault(s.charAt(l - 1), 0) - 1);
                if (cnt.get(s.charAt(l - 1)) <= 0) cnt.remove(s.charAt(l - 1));
                sub = s.substring(l, r + 1);
                if (cnt.size() <= maxLetters) {
                    if (cnt.size() <= maxLetters) map.put(sub, map.getOrDefault(sub, 0) + 1);
                }
            }
            if (map.size() != 0) {
                int max = 0;
                for (String key : map.keySet()) {
                    max = Math.max(max, map.get(key));
                }
                return max;
            }
        }
        return 0;
    }
}
