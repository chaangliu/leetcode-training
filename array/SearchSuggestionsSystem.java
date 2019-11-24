package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an array of strings products and a string searchWord. We want to design a system that suggests at most three product names from products after each character of searchWord is typed. Suggested products should have common prefix with the searchWord. If there are more than three products with a common prefix return the three lexicographically minimums products.
 * Return list of lists of the suggested products after each character of searchWord is typed.
 * Example 1:
 * Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
 * Output: [
 * ["mobile","moneypot","monitor"],
 * ["mobile","moneypot","monitor"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"]
 * ]
 * Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"]
 * After typing m and mo all products match and we show user ["mobile","moneypot","monitor"]
 * After typing mou, mous and mouse the system suggests ["mouse","mousepad"]
 * Example 2:
 * Input: products = ["havana"], searchWord = "havana"
 * Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
 * Example 3:
 * Input: products = ["bags","baggage","banner","box","cloths"], searchWord = "bags"
 * Output: [["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]
 * Example 4:
 * Input: products = ["havana"], searchWord = "tatiana"
 * Output: [[],[],[],[],[],[],[]]
 * Constraints:
 * 1 <= products.length <= 1000
 * 1 <= Σ products[i].length <= 2 * 10^4
 * All characters of products[i] are lower-case English letters.
 * 1 <= searchWord.length <= 1000
 * All characters of searchWord are lower-case English letters.
 * 20191124
 */
public class SearchSuggestionsSystem {
    /**
     * 题意：用户输入一个单词的过程中，每次输入1个字母就按字典序推荐3个单词。
     * 解法：一开始觉得完全跟trie那题一样，就是建trie然后bfs？但是比赛中肯定很麻烦。群主提示暴力能过我就暴力了一发果然过了；据说有重复的case，但我没遇到。
     */
    public List<List<String>> suggestedProducts(String[] products, String word) {
        Arrays.sort(products);
        List<List<String>> res = new ArrayList<>();
        for (int i = 1; i <= word.length(); i++) {
            List<String> item = new ArrayList<>();
            String str = word.substring(0, i);
            for (String p : products) {
                if (p.startsWith(str)) {
                    item.add(p);
                    if (item.size() == 3) break;
                }
            }
            res.add(item);
        }
        return res;
    }

    /**
     * Trie的解法，contest时也想到了，但是没敢写
     * 不知道mysql的like语句 是不是也是这么实现的，把所有suggestion预先存在字典树的每个节点上。应该不是吧，毕竟这样耗太多内存了。可能是跟我一样的BFS?
     */
    class Trie {
        Trie[] sub = new Trie[26];
        LinkedList<String> suggestion = new LinkedList<>();
    }

    public List<List<String>> suggestedProducts_(String[] products, String searchWord) {
        Trie root = new Trie();
        for (String p : products) { // build Trie.
            Trie t = root;
            for (char c : p.toCharArray()) { // insert current product into Trie.
                if (t.sub[c - 'a'] == null) t.sub[c - 'a'] = new Trie();
                t = t.sub[c - 'a'];
                t.suggestion.offer(p); // put products with same prefix into suggestion list.
                Collections.sort(t.suggestion); // sort products.
                if (t.suggestion.size() > 3) // maintain 3 lexicographically minimum strings.
                    t.suggestion.pollLast();
            }
        }
        List<List<String>> ans = new ArrayList<>();
        for (char c : searchWord.toCharArray()) { // search product.
            if (root != null) // if current Trie is NOT null.
                root = root.sub[c - 'a'];
            ans.add(root == null ? Arrays.asList() : root.suggestion); // add it if there exist products with current prefix.
        }
        return ans;
    }
}
