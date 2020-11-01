package dp.dfswithmemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.
 * <p>
 * Note:
 * <p>
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * Example 1:
 * <p>
 * Input:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * Output:
 * [
 * "cats and dog",
 * "cat sand dog"
 * ]
 * Example 2:
 * <p>
 * Input:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * Output:
 * [
 * "pine apple pen apple",
 * "pineapple pen apple",
 * "pine applepen apple"
 * ]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 * Example 3:
 * <p>
 * Input:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output:
 * []
 * <p>
 * 20190518
 */
public class WordBreakII {
    /**
     * 题意：给你个string和一个wordDict，让你用字典中的单词去给string分词，输出所有分词的可能。
     * 一道非常棒的backtrack with memo题。
     * 解法1，枚举word。
     * top down, 针对wordDict来DFS
     * 跟第一题的写法不一样的地方是，这里直接把remain穿进下一层dfs来找剩余的解集，与已有解集组合即可，而不需要一个start index。
     */
    public List<String> wordBreak_(String s, List<String> wordDict) {
        // 不需要传一个res进去
        return dfs(s, wordDict, new HashMap<String, List<String>>());
    }

    // DFS function returns an array including all substrings derived from s.
    private List<String> dfs(String s, List<String> wordDict, Map<String, List<String>> memo) {
        if (memo.containsKey(s)) return memo.get(s);
        List<String> res = new ArrayList<>();
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                String remain = s.substring(word.length());
                if (remain.length() == 0) {
                    res.add(word);//这里不能return，不然会漏解。当然也可以把这个判断拿到for循环外面。
                } else {
                    //把remain能组成的所有组合加进去
                    for (String sub : dfs(remain, wordDict, memo)) {
                        res.add(word + " " + sub);
                    }
                }
            }
        }
        memo.put(s, res);
        return res;
    }

    /**
     * 解法2，枚举index。
     * 为防止wordDict过大，可以像word break1一样，枚举index。
     * 从后往前对s进行拆分。巧妙利用原函数签名（借助一个全局的memo）。
     * 返回s能被wordDict中分词的所有可能句子。
     **/
    HashMap<String, List<String>> map = new HashMap<String, List<String>>();

    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        if (s.length() == 0) return res;
        if (map.containsKey(s)) return map.get(s);
        if (wordDict.contains(s)) {// 关键
            res.add(s);
        }
        for (int i = 0; i < s.length(); i++) {
            String end = s.substring(i);
            if (wordDict.contains(end)) {
                List<String> list = wordBreak(s.substring(0, i), wordDict);
                for (String item : list) {
                    res.add(item + " " + end);
                }
            }
        }
        map.put(s, res);
        return res;
    }

    /**
     * 我的代码：WA，感觉思路没问题，不知道怎么改..希望以后再回过头来解决
     * failed case:
     * "pineapplepenapple"
     * ["apple","pen","applepen","pine","pineapple"]
     */
    public List<String> wordBreak__FAIL(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>(wordDict);
        List<String> res = new ArrayList<>();
        dfs(s, set, 0, "", res, new HashSet<Integer>());
        return res;
    }

    private boolean dfs(String s, HashSet<String> set, int start, String cur, List<String> res, HashSet<Integer> visited) {
        if (start == s.length()) {
            res.add(cur);
            return true;
        }
        if (visited.contains(start)) {
            return false;
        }
        boolean possible = false;
        for (int i = start + 1; i <= s.length(); i++) {
            String sub = s.substring(start, i);
            if (cur.equals("pineapple")) System.out.println(sub);
            if (set.contains(sub)) {
                if (start != 0) sub = ' ' + sub;
                if (dfs(s, set, i, cur + sub, res, visited)) possible = true;
            }
        }
        if (!possible)
            visited.add(start);
        return false;
    }
}
