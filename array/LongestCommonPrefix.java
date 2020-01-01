package array;

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 * If there is no common prefix, return an empty string "".
 * Example 1:
 * Input: ["flower","flow","flight"]
 * Output: "fl"
 * Example 2:
 * Input: ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 * Note:
 * All given inputs are in lowercase letters a-z.
 * Created by DrunkPiano on 17/05/2017.
 * 20200101
 */

public class LongestCommonPrefix {
    /**
     * 题意：求最长公共前缀
     * 解法：linear scan
     */
    public String longestCommonPrefix(String[] strs) {
        int res = 0;
        if (strs == null || strs.length == 0) return "";
        while (true) {
            if (res >= strs[0].length()) return strs[0].substring(0, res);
            char c = strs[0].charAt(res);
            for (int i = 1; i < strs.length; i++) {
                if (res >= strs[i].length() || strs[i].charAt(res) != c) {
                    return strs[i].substring(0, res);
                }
            }
            res++;
        }
    }
}
