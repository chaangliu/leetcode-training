package dfs;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given an array of strings arr. String s is a concatenation of a sub-sequence of arr which have unique characters.
 * <p>
 * Return the maximum possible length of s.
 * Example 1:
 * <p>
 * Input: arr = ["un","iq","ue"]
 * Output: 4
 * Explanation: All possible concatenations are "","un","iq","ue","uniq" and "ique".
 * Maximum length is 4.
 * Example 2:
 * <p>
 * Input: arr = ["cha","r","act","ers"]
 * Output: 6
 * Explanation: Possible solutions are "chaers" and "acters".
 * Example 3:
 * <p>
 * Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
 * Output: 26
 * Constraints:
 * <p>
 * 1 <= arr.length <= 16
 * 1 <= arr[i].length <= 26
 * arr[i] contains only lower case English letters.
 * 20191027
 */
public class MaximumLengthofaConcatenatedStringwithUniqueCharacters {
    /**
     * 题意：给你一个数组里面有很多字符串，问你如果把这些字符串任意拼接，最长的不含有重复字符的character有多长。
     * 这是周赛第三题，所以我以为是DFS+MEMO或者Bottom up dp才能过，不然肯定会TLE，所以我没有搞brute force，
     * 但事实上这题数组长度只有16,最多只有2^16<10^6种组合，brute force是能过的。
     * 启示是，对数据范围的判断不够敏感，不要怕TLE，想不出好的解法就要试一下普通方法能不能过。
     * 这题的TAG里提示用MASK和DP，我觉得是有更好的方法的，过段时间再来看看DISCUSS吧。
     * 我考虑的时候一直在想，如果有两个item组合起来有duplicate了，如何避免从头再check一次？就想不出。
     * 下面我的写法就是模仿combinations那题，特征是dfs的参数有个start。
     * 但是我写的时候一开始写成i = start + 1了，那样就不能把""作为开头放进去了。
     */
    public int maxLength(List<String> arr) {
        dfs(arr, 0, "");
        return res;
    }

    int res = 0;

    private void dfs(List<String> arr, int start, String cur) {
        if (!checkValid(cur)) return;
        res = Math.max(res, cur.length());
        for (int i = start; i < arr.size(); i++) {
            dfs(arr, i + 1, cur + arr.get(i));
        }
    }

    private boolean checkValid(String s) {
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (!set.add(c)) return false;
        }
        return true;
    }
}
