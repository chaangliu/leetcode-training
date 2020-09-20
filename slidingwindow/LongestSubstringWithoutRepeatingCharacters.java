package slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 * <p>
 * Example 1:
 * <p>
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 * <p>
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 * <p>
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * Created by DrunkPiano on 2017/3/5.
 */

public class LongestSubstringWithoutRepeatingCharacters {

    /**
     * 题意：找出没有重复字母的最长substring的长度
     * 解法1. slidingwindow + set.
     * 着重看解法2的优化
     */
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> set = new HashSet<>();
        int l = 0, r = 0, n = s.length(), res = 0;
        while (r < n) {
            if (set.add(s.charAt(r))) {
                res = Math.max(res, r - l + 1);
                r++;
            } else {
                set.remove(s.charAt(l));
                l++;
            }
        }
        return res;
    }

    /**
     * 解法2. slidingwindow + map，map记录当前字母的最晚出现的index + 1，如果有重复的，就直接走到index右侧位置，不用一步步走了
     */
    public int lengthOfLongestSubstring_(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);//如果有重复的，就直接走到上一个重复的位置的右侧位置，不用一步步走了
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

    /**
     * 又写了一遍，其实在set contains的时候不需要第二个while，仍用外层while就好
     */
    public int lengthOfLongestSubstring_2020(String s) {
        int l = 0, r = 0, res = 0;
        HashSet<Character> set = new HashSet<>();
        while (r < s.length()) {
            char c = s.charAt(r);
            if (!set.contains(c)) {
                set.add(c);
                // System.out.println("adding: " + c);
                res = Math.max(res, set.size());
                r++;
            } else {
                while (l < r && s.charAt(l) != c) {
                    // System.out.println("removing: " + s.charAt(l));
                    set.remove(s.charAt(l));
                    l++;
                }
                set.remove(s.charAt(l));
                // System.out.println("removing: " + s.charAt(l));
                l++;
            }
        }
        return res;
    }


    //20190124review hashMap
    public int lengthOfLongestSubstring___(String s) {
        if (s.length() == 0) return 0;
        int res = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int runner = 0, walker = 0; runner < s.length(); runner++) {
            if (map.containsKey(s.charAt(runner))) {
                walker = Math.max(walker, map.get(s.charAt(runner)));//这里要取walker和已有数字的index里面大的那个，因为walker可能在已有数字index后面了(比如abba)，而index~walker之间已经有重复了
            }
            res = Math.max(runner - walker + 1, res);
            map.put(s.charAt(runner), runner + 1);
        }
        return res;
    }

    public static void main(String args[]) {
        LongestSubstringWithoutRepeatingCharacters lis = new LongestSubstringWithoutRepeatingCharacters();
        System.out.println(lis.lengthOfLongestSubstring("abba"));
    }
}
