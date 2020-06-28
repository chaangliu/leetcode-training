package dp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 * <p>
 * Note:
 * <p>
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * Example 1:
 * <p>
 * Input: s = "leetcode", wordDict = ["leet", "code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * Example 2:
 * <p>
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * Note that you are allowed to reuse a dictionary word.
 * Example 3:
 * <p>
 * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: false
 * <p>
 * 20190517
 * 20200205 --review
 */
public class WordBreak {
    /**
     * 题意：给你一个字符串数组和一个字符串s，问s能否由数组中的字符串组成。
     * 这题我看了之后觉得可以用DFS，看了下TOPIC是DP，于是用DFS + MEMO写了一下就过了。
     * top down:
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>(wordDict);
        return dfs(s, set, new Boolean[s.length()], 0);
    }

    /**
     * dfs返回从s从start开始能否由set中的str组成
     */
    private boolean dfs(String s, HashSet<String> set, Boolean[] memo, int start) {
        if (start == s.length()) return true;// key
        if (memo[start] != null) return memo[start];
        for (int i = start + 1; i <= s.length(); i++) {
            String sub = s.substring(start, i);
            if (set.contains(sub)) {
                if (dfs(s, set, memo, i)) {
                    memo[start] = true;
                    return true;
                }
            }
        }
        memo[start] = false;
        return false;
    }

    /**
     * 20200625 看第一眼觉得是dfs，看了tag发现是dp
     */
    public boolean wordBreak__(String s, List<String> wordDict) {
        return dfs(new HashMap<Integer, Boolean>(), wordDict, s, 0);
    }

    private boolean dfs(HashMap<Integer, Boolean> memo, List<String> dict, String s, int i) {
        // if (memo.getOrDefault(i, false)) return true; // 20200625 竟然写错成这个了
        if (memo.containsKey(i)) return memo.get(i);
        if (i == s.length()) return true;
        for (String w : dict) {
            if (s.substring(i).startsWith(w)) {
                if (dfs(memo, dict, s, i + w.length())) {
                    memo.put(i, true);
                    return true;
                }
            }
        }
        memo.put(i, false);
        return false;
    }

    /**
     * bottom up:
     * dp[i]代表前i位是否可以用被分词
     * dp[i] = dp[j] && set.contains(substring(j,j))
     **/
    public boolean wordBreak_(String s, List<String> wordDict) {
        boolean dp[] = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = 0; j < i; j++) {
                //dp[j]的j实际上映射的是string里的j-1位，j = 0时代表""
                if (dp[j] && wordDict.contains(s.substring(j, i))) dp[i] = true;
            }
        }
        return dp[dp.length - 1];
    }
}
