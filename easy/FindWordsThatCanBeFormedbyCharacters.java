package easy;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given an array of strings words and a string chars.
 * <p>
 * A string is good if it can be formed by characters from chars (each character can only be used once).
 * <p>
 * Return the sum of lengths of all good strings in words.
 * Example 1:
 * <p>
 * Input: words = ["cat","bt","hat","tree"], chars = "atach"
 * Output: 6
 * Explanation:
 * The strings that can be formed are "cat" and "hat" so the answer is 3 + 3 = 6.
 * Example 2:
 * <p>
 * Input: words = ["hello","world","leetcode"], chars = "welldonehoneyr"
 * Output: 10
 * Explanation:
 * The strings that can be formed are "hello" and "world" so the answer is 5 + 5 = 10.
 * Note:
 * 1 <= words.length <= 1000
 * 1 <= words[i].length, chars.length <= 100
 * All strings contain lowercase English letters only.
 * 20190818
 */
public class FindWordsThatCanBeFormedbyCharacters {
    /**
     * contest签到题
     */
    public int countCharacters(String[] words, String chars) {
        Map<Character, Integer> map = new HashMap<>();
        int res = 0;
        for (char c : chars.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (String word : words) {
            boolean flag = true;
            Map<Character, Integer> t = new HashMap<>(map);
            for (char c : word.toCharArray()) {
                if (!t.containsKey(c)) {
                    flag = false;
                    break;
                }
                t.put(c, t.get(c) - 1);
                if (t.get(c) == 0)
                    t.remove(c);
            }
            if (flag)
                res += word.length();
        }
        return res;
    }
}
