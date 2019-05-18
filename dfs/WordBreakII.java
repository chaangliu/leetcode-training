package dfs;

import java.util.ArrayList;
import java.util.HashMap;
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
     * DFS
     * 这题有点像升级版的permutations，写法有点新颖。另外要用一个memo不然TLE。
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        //不需要传一个res进去
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
}
