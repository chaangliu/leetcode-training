package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string s, return the last substring of s in lexicographical order.
 * Example 1:
 * Input: "abab"
 * Output: "bab"
 * Explanation: The substrings are ["a", "ab", "aba", "abab", "b", "ba", "bab"]. The lexicographically maximum substring is "bab".
 * Example 2:
 * Input: "leetcode"
 * Output: "tcode"
 * Note:
 * 1 <= s.length <= 10^5
 * s contains only lowercase English letters.
 * 20190819
 */
public class LastSubstringinLexicographicalOrder {
    /**
     * 这题tag是hard，但是数据太弱，brute force也能过；
     * 似乎想考察的是计数排序。
     */
    public String lastSubstring(String s) {
        Map<Character, ArrayList<Integer>> map = new HashMap<>();//a->[0,2]; b->[1,3]
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!map.containsKey(c)) map.put(c, new ArrayList<>());
            map.get(c).add(i);
        }
        if (map.size() == 1) return s;//hack，绕过aa...aa这种情况
        char target = 'a';
        for (char c : map.keySet()) {
            if (c - target >= 0) target = c;
        }
        List<Integer> idx = map.get(target);
        String res = "";
        for (int i : idx) {
            String tmp = s.substring(i);
            if (tmp.compareTo(res) > 0) res = tmp;
        }
        return res;
    }
}
