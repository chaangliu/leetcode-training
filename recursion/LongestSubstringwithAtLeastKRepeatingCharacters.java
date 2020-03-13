package recursion;

import java.util.HashMap;

/**
 * Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every character in T appears no less than k times.
 * Example 1:
 * Input:
 * s = "aaabb", k = 3
 * Output:
 * 3
 * The longest substring is "aaa", as 'a' is repeated 3 times.
 * Example 2:
 * Input:
 * s = "ababbc", k = 2
 * Output:
 * 5
 * The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 * 20200313
 */
public class LongestSubstringwithAtLeastKRepeatingCharacters {
    /**
     * 题意：给你一个字符串和一个k，求一个最长子串，这个子串里某个字母出现次数【至少】是k。
     * 如果是至多是k，那就是滑动窗口法；这题求至少，可以从两端开始分治、剪枝。
     */
    public int longestSubstring(String s, int k) {
        return maxLen(s, 0, s.length() - 1, k);
    }

    private int maxLen(String s, int l, int r, int k) {
        if (r - l + 1 < k) return 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = l; i <= r; i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        while (r - l + 1 >= k && map.get(s.charAt(l)) < k) {// 剪枝
            l++;
        }
        while (r - l + 1 >= k && map.get(s.charAt(r)) < k) {// 剪枝
            r--;
        }
        // if (r - l + 1 < k) return 0;
        for (int i = l; i <= r; i++) {
            char c = s.charAt(i);
            if (map.get(c) < k) {
                return Math.max(maxLen(s, l, i - 1, k), maxLen(s, i + 1, r, k));
            }
        }
        return r - l + 1;
    }
}
