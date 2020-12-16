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
    /**
     * 题意：判断s是否符合Pattern。例子如下：
     * pattern = "abba", str = "dog dog dog dog" => 不行
     * pattern = "aaaa", str = "dog cat cat dog" => 不行
     * 所以一定要严格映射，一个字母对应一个单词，同时要求一个单词对应一个字母，在集合论中，这种关系被称为「双射」。
     * 所以一定要同时检查对应关系，用两个map。
     */
    public boolean wordPattern(String pattern, String s) {
        String[] arr = s.split(" ");
        if (arr.length != pattern.length()) return false;
        HashMap<Character, String> map1 = new HashMap<>();
        HashMap<String, Character> map2 = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            String word = arr[i];
            char c = pattern.charAt(i);
            if (map1.containsKey(c) && !map1.get(c).equals(word)) return false;
            if (map2.containsKey(word) && map2.get(word) != c) return false;
            map1.put(c, word);
            map2.put(word, c);
        }
        return true;
    }
}
