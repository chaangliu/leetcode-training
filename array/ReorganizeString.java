package array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.
 * <p>
 * If possible, output any possible result.  If not possible, return the empty string.
 * <p>
 * Example 1:
 * <p>
 * Input: S = "aab"
 * Output: "aba"
 * Example 2:
 * <p>
 * Input: S = "aaab"
 * Output: ""
 * Note:
 * <p>
 * S will consist of lowercase letters and have length in range [1, 500].
 * <p>
 * https://leetcode.com/problems/reorganize-string/description/
 * https://www.jianshu.com/p/aff4f3db8572
 */
public class ReorganizeString {
    /**
     * 题意：给你一个字符串，让你把它重新排列，相同的字母不能相邻。
     * 解法：贪心，每次贪心地取出现次数最多的元素。一个容易错的地方是，个数相同的情况下需要严格按照alphabet排列，否则会WA（比如"abbabbaaab"会出现"abb..."）。
     */
    public String reorganizeString(String S) {
        PriorityQueue<Map.Entry<Character, Integer>> q = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue() != 0 ? b.getValue() - a.getValue() : a.getKey() - b.getKey());
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : S.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (Map.Entry<Character, Integer> e : map.entrySet()) {
            q.offer(e);
        }
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            Map.Entry<Character, Integer> e = q.poll();
            if (sb.length() > 0 && e.getKey() == sb.charAt(sb.length() - 1)) return "";
            sb.append(e.getKey());
            if (!q.isEmpty()) {
                Map.Entry<Character, Integer> e2 = q.poll();
                sb.append(e2.getKey());
                e2.setValue(e2.getValue() - 1);
                if (e2.getValue() > 0) q.offer(e2);
            }
            e.setValue(e.getValue() - 1);
            if (e.getValue() > 0) q.offer(e);
        }
        return sb.toString();
    }
}
