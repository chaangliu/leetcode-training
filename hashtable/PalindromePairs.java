package hashtable;

import java.util.ArrayList;
import java.util.Arrays;
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
     * 题意：给你一个字符串数组，在里面找出所有能组成palindrome的两个字符串，用index pair的形式输出。
     * 暴力的话，肯定是O(n^2 * k)，怎么优化呢？
     * 解法1.
     * 把每个字符串在每个位置(包括0位置和length的位置)分割成str1和str2，如果str1是回文，那么把str2翻转一下，看看给定数组中是否有翻转后的str2。
     * 例如lls,在ll处分割，ll是回文，后半段s翻转后存在于给定数组中，记为r。那么说明ll可以做中间的部分，首尾分别由r-str2和r组成。
     * 同样地，我们也要考虑后半段是回文的情况，也就是s做中间值的情况。
     * 再举个例子，
     * aba de, 我们去map中找ed，如果有，说明可以组成ed aba de
     * ab cdc, 我们去map中找ba，如果有，说明可以组成ab cdc ba
     * 有个特殊情况，abcd，dcba这种前后位置可以颠倒的pair要防止重复，因为如果我们把abcd分为""和"abcd"以及"abcd"和""，当前"abcd"分别会添加到pair的0和1的位置。
     * 复杂度:O(n * k^2)
     **/
    public List<List<Integer>> palindromePairs_(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length() + 1; j++) {
                if (j == words[i].length()) {
                    System.out.print("");
                }
                String str1 = words[i].substring(0, j);
                String str2 = words[i].substring(j);
                if (isPalindrome(str1)) {
                    String reversedStr2 = new StringBuilder(str2).reverse().toString();
                    if (map.containsKey(reversedStr2) && map.get(reversedStr2) != i) {//abcd, dcba
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


    /**
     * Approach2. Trie
     * 跟1是类似的思路，都是把每个单词的前后两部分做split然后看其中一部分能否作为palindrome的中间。从word.length - 1到0倒着建trie。
     * 拿aba de和ed考虑，建成一个edaba和de的路径。那么我们分割到ed aba的时候，可以直接去找de，发现de isWord，那么就valid。
     * 复杂度也是O(n * k^2).
     */
    private static class TrieNode {
        TrieNode[] next;
        int index;
        List<Integer> list;

        TrieNode() {
            next = new TrieNode[26];
            index = -1;
            list = new ArrayList<>();
        }
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        TrieNode root = new TrieNode();
        for (int i = 0; i < words.length; i++) {
            addWord(root, words[i], i);
        }
        for (int i = 0; i < words.length; i++) {
            search(words, i, root, res);
        }
        return res;
    }

    private void addWord(TrieNode root, String word, int index) {
        for (int i = word.length() - 1; i >= 0; i--) {
            int j = word.charAt(i) - 'a';

            if (root.next[j] == null) {
                root.next[j] = new TrieNode();
            }

            if (isPalindrome(word, 0, i)) {
                root.list.add(index);
            }

            root = root.next[j];
        }

        root.list.add(index);
        root.index = index;
    }

    private void search(String[] words, int i, TrieNode root, List<List<Integer>> res) {
        for (int j = 0; j < words[i].length(); j++) {
            if (root.index >= 0 && root.index != i && isPalindrome(words[i], j, words[i].length() - 1)) {
                res.add(Arrays.asList(i, root.index));
            }

            root = root.next[words[i].charAt(j) - 'a'];
            if (root == null) return;
        }

        for (int j : root.list) {
            if (i == j) continue;
            res.add(Arrays.asList(i, j));
        }
    }

    private boolean isPalindrome(String word, int i, int j) {
        while (i < j) {
            if (word.charAt(i++) != word.charAt(j--)) return false;
        }

        return true;
    }

    public static void main(String args[]) {
        new PalindromePairs().palindromePairs(new String[]{"abcd", "dcba", "lls", "s", "sssll"});
    }
}
