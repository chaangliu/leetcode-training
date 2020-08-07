package cc150;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 给定一组单词words，编写一个程序，找出其中的最长单词，且该单词由这组单词中的其他单词组合而成。若有多个长度相同的结果，返回其中字典序最小的一项，若没有符合要求的单词则返回空字符串。
 *
 * 示例：
 *
 * 输入： ["cat","banana","dog","nana","walk","walker","dogwalker"]
 * 输出： "dogwalker"
 * 解释： "dogwalker"可由"dog"和"walker"组成。
 * 提示：
 *
 * 0 <= len(words) <= 200
 * 1 <= len(words[i]) <= 100
 */
public class LongestWord {
    class Solution {
        // WA!
        public String longestWord(String[] words) {
            HashSet<String> set = new HashSet<>();
            for (String i : words) set.add(i);
            for (String word : words) {
                canForm(word, set, new HashMap<>());
            }
            return res;
        }

        String res = "";

        private boolean canForm(String word, HashSet<String> set, HashMap<String, Boolean> memo) {
            if (set.equals(word)) return true;
            if (memo.containsKey(word)) return memo.get(word);
            boolean can = false;
            for (int i = 1; i < word.length(); i++) {
                if (set.contains(word.substring(0, i)) && canForm(word.substring(i), set, memo)) {
                    if (word.length() > res.length()) res = word;
                    can = true;
                }
            }
            memo.put(word, can);
            return can;
        }
    }
}
