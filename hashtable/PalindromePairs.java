package hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
 * <p>
 * Example 1:
 * <p>
 * Input: ["abcd","dcba","lls","s","sssll"]
 * Output: [[0,1],[1,0],[3,2],[2,4]]
 * Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
 * Example 2:
 * <p>
 * Input: ["bat","tab","cat"]
 * Output: [[0,1],[1,0]]
 * Explanation: The palindromes are ["battab","tabbat"]
 * <p>
 * 20190508
 */
public class PalindromePairs {
    /**
     * Approach1:用Map保存每个单词的前缀，复杂度O(n)
     * 用到这个特性：
     * aba de -- ed
     * ab cdc -- ba
     */
    public List<List<Integer>> palindromePairs(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length() + 1; j++) {
                String str1 = words[i].substring(0, j);
                String str2 = words[i].substring(j);
                if (isPalindrome(str1)) {
                    String reversedStr2 = new StringBuilder(str2).reverse().toString();
                    if (map.containsKey(reversedStr2) && map.get(reversedStr2) != i) {
                        List<Integer> list = new ArrayList<>();
                        list.add(map.get(reversedStr2));//加到前面
                        list.add(i);
                        res.add(list);
                    }
                }
                if (isPalindrome(str2)) {
                    String reversedStr1 = new StringBuilder(str1).reverse().toString();
                    if (map.containsKey(reversedStr1) && map.get(reversedStr1) != i && str2.length() != 0) {//注意：这里要额外加一个str2.length()!= 0，防止重复
                        List<Integer> list = new ArrayList<>();
                        list.add(i);
                        list.add(map.get(reversedStr1));//加到后面
                        res.add(list);
                    }
                }
            }
        }
        return res;
    }

    private boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;
        while (left <= right) {
            if (str.charAt(left++) != str.charAt(right--)) return false;
        }
        return true;
    }
}
