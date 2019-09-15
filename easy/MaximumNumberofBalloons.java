package easy;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string text, you want to use the characters of text to form as many instances of the word "balloon" as possible.
 * <p>
 * You can use each character in text at most once. Return the maximum number of instances that can be formed.
 * Example 1:
 * Input: text = "nlaebolko"
 * Output: 1
 * Example 2:
 * Input: text = "loonbalxballpoon"
 * Output: 2
 * Example 3:
 * <p>
 * Input: text = "leetcode"
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= text.length <= 10^4
 * text consists of lower case English letters only.
 * 20190915
 */
public class MaximumNumberofBalloons {
    /**
     * 虽然是easy题，但是我犯了两个错误，1是我直接扫描了，这样会错过之前没匹配到的数 2改进之后忘了考虑l和n出现两次的情况
     */
    public int maxNumberOfBalloons(String text) {
        HashMap<Character, Integer> map = new HashMap<>();
        fill(map);
        for (char c : text.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            }
        }
        int res = Integer.MAX_VALUE;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getKey() == 'l' || entry.getKey() == 'o') {
                res = Math.min(res, entry.getValue() / 2);
            } else {
                res = Math.min(res, entry.getValue());
            }
        }
        return res;
    }

    private void fill(HashMap<Character, Integer> map) {
        map.put('b', 0);
        map.put('a', 0);
        map.put('l', 0);
        map.put('o', 0);
        map.put('n', 0);
    }
}
