package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Given two equal-size strings s and t. In one step you can choose any character of t and replace it with another character.
 * Return the minimum number of steps to make t an anagram of s.
 * An Anagram of a string is a string that contains the same characters with a different (or the same) ordering.
 * Example 1:
 * <p>
 * Input: s = "bab", t = "aba"
 * Output: 1
 * Explanation: Replace the first 'a' in t with b, t = "bba" which is anagram of s.
 * Example 2:
 * <p>
 * Input: s = "leetcode", t = "practice"
 * Output: 5
 * Explanation: Replace 'p', 'r', 'a', 'i' and 'c' from t with proper characters to make t anagram of s.
 * Example 3:
 * <p>
 * Input: s = "anagram", t = "mangaar"
 * Output: 0
 * Explanation: "anagram" and "mangaar" are anagrams.
 * Example 4:
 * <p>
 * Input: s = "xxyyzz", t = "xxyyzz"
 * Output: 0
 * Example 5:
 * <p>
 * Input: s = "friend", t = "family"
 * Output: 4
 * Constraints:
 * 1 <= s.length <= 50000
 * s.length == t.length
 * s and t contain lower-case English letters only.
 * 20200109
 */
public class MinimumNumberofStepstoMakeTwoStringsAnagram {
    /**
     * 题意：给你两个长度相等地string，s和t，问最少需要更换t中的多少个字母，才能让两个string变成anagram。
     * 解法：我一开始没思路，就想是不是要模仿edit distance。想了一会儿，基于这道题只是周赛第二题不可能是dp，
     * 于是想了另一种方案，只要统计两个string里面字母出现的频次，然后s中亏空的个数之和就是答案。
     */
    public int minSteps(String s, String t) {
        int res = 0;
        HashMap<Character, Integer> map1 = new HashMap<>();
        HashMap<Character, Integer> map2 = new HashMap<>();
        for (char c : s.toCharArray()) {
            map1.put(c, map1.getOrDefault(c, 0) + 1);
        }
        for (char c : t.toCharArray()) {
            map2.put(c, map2.getOrDefault(c, 0) + 1);
        }
        for (char c : map1.keySet()) {
            int shorts = map1.get(c) - map2.getOrDefault(c, 0);
            res += shorts > 0 ? shorts : 0;
        }
        return res;
    }

    /**
     * rock的写法，把少的和多的的绝对值都加起来，然后除以二
     */
    public int minSteps_(String s, String t) {
        int[] count = new int[26];
        for (char c : s.toCharArray())
            ++count[c - 'a'];
        for (char c : t.toCharArray())
            --count[c - 'a'];
        return Arrays.stream(count).map(Math::abs).sum() / 2;
    }
}
