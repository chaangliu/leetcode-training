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
 * 20190314
 * 20200118 review
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

    /**
     * 题意：给你一个字符串s，让你找到最短的包含t中所有字母的substring。
     * 解法：sliding window，不满足时就expand, 满足时(set.size() == 0)就contract。
     * 做的过程中发现这题有个要点在于，不能用set，而要用map，即便题目没有要求子串要包含t中字母出现的次数也要用map，
     * 如果用了map，在左边contract的时候你无法判断这时候substring里是否还有其他即将出窗的那个字母。
     * 20200523review: 今天又写了一下，用了两个map，但是有个很长的CASE过不了，遂放弃。我觉得用两个map很容易出错，变量太多了。
     * 还是用一个map好，map中记录与每个字符与目标字符串相差的字符个数。
     **/
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) map.put(c, map.getOrDefault(c, 0) + 1);
        int l = 0, r = 0, n = s.length(), min = n, match = 0;
        String res = "";
        while (r < n) {
            char c = s.charAt(r);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1);
                if (map.get(c) == 0) match++;
            }

            while (match == map.size()) {
                if (r - l < min) {
                    res = s.substring(l, r + 1);
                    min = r - l;
                }
                char lChar = s.charAt(l);
                if (map.containsKey(lChar)) {
                    map.put(lChar, map.getOrDefault(lChar, 0) + 1);
                    if (map.get(lChar) > 0) {// 缺少t中的lChar了
                        match--;
                    }
                }
                l++; //已犯错误：忘记l++
            }
            r++;
        }
        return res;
    }

    public String minWindow_(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0) return "";
        String res = "";
        Map<Character, Integer> map = new HashMap<>();
        //统计t中的字母出现频次
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int left = 0, right = 0, count = map.size();
        while (right < s.length()) {
            //expand
            char rChar = s.charAt(right);
            if (map.containsKey(rChar)) {
                map.put(rChar, map.get(rChar) - 1);//map的key只包含t中有的字母。value是s中对应字母个数减去t中对应字母个数的差值，可以为负数。
                if (map.get(rChar) == 0) count--;//count表示T中的字母在S中没被cover住的个数。大于等于零。等于0的时候意思是S的[left,right]包含了T中的所有字母。
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
