package easy;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a pattern and a string str, find if str follows the same pattern.
 * <p>
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.
 * <p>
 * Example 1:
 * <p>
 * Input: pattern = "abba", str = "dog cat cat dog"
 * Output: true
 * Example 2:
 * <p>
 * Input:pattern = "abba", str = "dog cat cat fish"
 * Output: false
 * Example 3:
 * <p>
 * Input: pattern = "aaaa", str = "dog cat cat dog"
 * Output: false
 * Example 4:
 * <p>
 * Input: pattern = "abba", str = "dog dog dog dog"
 * Output: false
 * <p>
 * 20190329
 */
public class WordPattern {
    public boolean wordPattern(String pattern, String str) {
        String arr[] = str.split(" ");
        if (arr.length != pattern.length()) return false;
        Map<Character, String> map = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            if (!map.containsKey(pattern.charAt(i))) {
                if (map.containsValue(arr[i])) return false;//已犯错误，这种情况没考虑到不能少..test要做好
                map.put(pattern.charAt(i), arr[i]);
            } else {
                if (!map.get(pattern.charAt(i)).equals(arr[i])) return false;
            }
        }
        return true;
    }
}
