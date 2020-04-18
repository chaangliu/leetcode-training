package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.
 * A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.
 * <p>
 * Example:
 * Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 * <p>
 * Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
 * <p>
 * Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
 * "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
 * "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
 * Note:
 * The number of elements of the given array will not exceed 10,000
 * The length sum of elements in the given array will not exceed 600,000.
 * All the input string will only include lower case letters.
 * The returned elements order does not matter.
 * 20200415
 */
public class ConcatenatedWords {
    /**
     * 解法：给你一个字符串数组，问其中哪些字符串可以由其他字符串组合而成。
     * 解法：DFS
     * 我的答案就不贴了，从后往前找的，思路很不清晰。这题需要再回顾。
     * 这题也可以用word break的做法来做。https://leetcode.com/problems/concatenated-words/discuss/348972/Java-Common-template-Word-Break-I-Word-Break-II-Concatenated-Words
     */
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> list = new ArrayList<String>();
        Set<String> set = new HashSet(Arrays.asList(words));

        for (String word : words) {
            set.remove(word);
            if (dfs(word, set, "")) list.add(word);
            set.add(word);
        }
        return list;
    }

    private boolean dfs(String word, Set<String> set, String previous) {
        if (previous.length() != 0)
            set.add(previous); // 关键，一旦找到匹配的substring，就加入到set里
        if (set.contains(word)) return true;
        for (int i = 1; i < word.length(); i++) {
            String prefix = word.substring(0, i);
            if (set.contains(prefix) && dfs(word.substring(i), set, previous + prefix)) return true;
        }
        return false;
    }
}
