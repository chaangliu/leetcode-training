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
 */

public class PalindromePartitioning {
    /**
     * 题意：给你一个字符串，把这个字符串partition成substring，要求每个substring都是palindrome。返回所有可能的组合。
     * 解法：显然可以用「start型递归」我发明的词，就像combinations那题一样，只需要维护一个指针作为下一层递归的start。
     */
    public List<List<String>> partition(String s) {
        List<List<String>> list = new ArrayList<>();
        backtrack(list, new ArrayList<String>(), s, 0);
        return list;
    }

    public void backtrack(List<List<String>> list, List<String> tempList, String s, int start) {
        if (start == s.length()) {
            list.add(new ArrayList<String>(tempList));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (isPalindrome(s, start, i)) {
                tempList.add(s.substring(start, i + 1));
                backtrack(list, tempList, s, i + 1);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s, int low, int high) {
        while (low <= high) {
            if (s.charAt(low++) != s.charAt(high--)) return false;
        }
        return true;
    }


    public static void main(String args[]) {
        System.out.println(new PalindromePartitioning().partition("aab"));
    }
}
