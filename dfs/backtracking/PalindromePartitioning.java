package dfs.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 * Example:
 * Input: "aab"
 * Output:
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 * Created by DrunkPiano on 2017/4/27.
 * 20200203 --review
 */

public class PalindromePartitioning {
    /**
     * 题意：给你一个字符串，把这个字符串partition成substring，要求每个substring都是palindrome。返回所有可能的组合。
     * 解法：显然可以用「start型递归」我发明的词，就像combinations那题一样，只需要维护一个指针作为下一层递归的start。
     */
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), s, 0);
        return res;
    }

    private void dfs(List<List<String>> res, List<String> cell, String s, int start) {
        if (start >= s.length()) {
            res.add(new ArrayList<>(cell));
            return;
        }
        for (int i = start + 1; i <= s.length(); i++) {
            String sub = s.substring(start, i);
            if (isPalindrome(sub)) {
                cell.add(sub);
                dfs(res, cell, s, i);
                cell.remove(cell.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) return false;
        }
        return true;
    }
}
