package dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a list of words, each word consists of English lowercase letters.
 * <p>
 * Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make it equal to word2.  For example, "abc" is a predecessor of "abac".
 * <p>
 * A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of word_2, word_2 is a predecessor of word_3, and so on.
 * <p>
 * Return the longest possible length of a word chain with words chosen from the given list of words.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: ["a","b","ba","bca","bda","bdca"]
 * Output: 4
 * Explanation: one of the longest word chain is "a","ba","bda","bdca".
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 16
 * words[i] only consists of English lowercase letters.
 * <p>
 * 20190519 contest 137
 */
public class LongestStringChain {
    /**
     * 一开始觉得很像昨晚做的Word Break II，所以想用dfs + cache，用Map保存word和它能组成的List来做pruning，结果我不知道怎么在backtracking时恢复剪枝的visited..
     * 浪费很多时间，然后去掉cache，竟然没有TLE。
     */
    int maxLen = 0;

    public int longestStrChain(String[] words) {
        dfs(words, new boolean[words.length], new ArrayList<String>());
        return maxLen;
    }

    private void dfs(String[] words, boolean[] visited, List<String> list) {
        for (int i = 0; i < words.length; i++) {
            if (!visited[i] && (list.size() == 0 || isValid(list.get(list.size() - 1), words[i]))) {
                visited[i] = true;
                list.add(words[i]);
                dfs(words, visited, list);
                list.remove(words[i]);
                visited[i] = false;
            }
        }
        maxLen = Math.max(maxLen, list.size());
    }

    private boolean isValid(String s1, String s2) {
        if (s2.length() - s1.length() != 1) return false;
        int[] map = new int[26];//一开始用了set，错误，因为可能有重复字母
        int count = 0;
        for (int i = 0; i < s2.length(); i++) {
            map[s2.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s1.length(); i++) {
            map[s1.charAt(i) - 'a']--;
        }
        for (int i = 0; i < map.length; i++) {
            if (map[i] != 0 && map[i] != 1) return false;
            if (map[i] == 1) count++;
        }
        return count == 1;
    }


//    private void dfs(String[] words, Map<String, List<String>> memo, boolean[] visited, List<String> list) {
//        for (int i = 0; i < words.length; i++) {
//            if (!visited[i] && (list.size() == 0 || isValid(list.get(list.size() - 1), words[i]))) {
//                visited[i] = true;
//
//                if (memo.containsKey(words[i])) {
//                    list.addAll(memo.get(words[i]));//visited怎么memo?
//                    dfs(words, memo, visited, list);
//
//                } else {
//                    list.add(words[i]);
//                    dfs(words, memo, visited, list);
//                    list.remove(words[i]);
//                }
//                visited[i] = false;
//            }
//        }
//    }

}
