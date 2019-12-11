package hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.
 * Example 1:
 * Input:
 * s = "barfoothefoobarman",
 * words = ["foo","bar"]
 * Output: [0,9]
 * Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
 * The output order does not matter, returning [9,0] is fine too.
 * Example 2:
 * Input:
 * s = "wordgoodgoodgoodbestword",
 * words = ["word","good","best","word"]
 * Output: []
 * 20191211
 */
public class SubstringwithConcatenationofAllWords {
    /**
     * 题意：给你一个字符串和一个包含很多单词的数组，让你找出所有子串的起始index，这个子串由全部单词出现一次组成。
     * 解法：很容易想到双map（一开始想到set，但是发现有重复）；
     * 但是这题怎么处理for循环是比较麻烦的一点；比较好的思路就是拆分，每次先把words里面所有单词长度的substring抽取出来，再去判断是否合法。
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (words == null || words.length == 0) return res;
        HashMap<String, Integer> map = new HashMap<>();
        for (String w : words) {
            map.put(w, map.getOrDefault(w, 0) + 1);
        }
        int len = words[0].length(), num = words.length;
        for (int i = 0; i <= s.length() - len * num; i++) {
            String table = s.substring(i, i + len * num);
            System.out.println(table);
            if (valid(len, num, table, (HashMap<String, Integer>) map.clone())) {
                res.add(i);
            }
        }
        return res;
    }

    private boolean valid(int len, int num, String table, HashMap<String, Integer> map) {
        for (int i = 0; i < num; i++) {
            String key = table.substring(i * len, (i + 1) * len);
            if (!map.containsKey(key)) return false;
            map.put(key, map.get(key) - 1);
            if (map.get(key) == 0) map.remove(key);
        }
        return map.size() == 0;
    }
}
