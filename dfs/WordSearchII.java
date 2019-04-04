package dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 * <p>
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 * <p>
 * <p>
 * <p>
 * Example:
 * <p>
 * Input:
 * board = [
 * ['o','a','a','n'],
 * ['e','t','a','e'],
 * ['i','h','k','r'],
 * ['i','f','l','v']
 * ]
 * words = ["oath","pea","eat","rain"]
 * <p>
 * Output: ["eat","oath"]
 * <p>
 * 20190404
 */
public class WordSearchII {
    /**
     * 这题我知道用普通dfs会很慢所以要借助Trie；
     * 试着写了一下，用Trie也有很多技巧，比如我的想法是使用Subset那种方式从board里没找到一个字符串就insert到trie里，然后对words数组里的每个词search，果然TLE；
     * 然后用一个set判断如果已经insert过就不再insert，仍然TLE；
     * 然后我就想是否有办法一边dfs一边插入，不过没有想通，就看了答案；答案是反过来，使用words数组来build Trie，然后在dfs的时候顺便检查node是否isWord，这样不需要实现trie的search方法
     */
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        if (board == null || board[0] == null || words == null) return res;
        TrieNode trieNode = new TrieNode(' ');
        buildTrie(words, trieNode);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(i, j, board, trieNode, new boolean[board.length][board[0].length], res, new StringBuilder());
            }
        }
        return res;
    }

    HashSet<String> set = new HashSet<>();

    private void dfs(int i, int j, char[][] board, TrieNode node, boolean[][] visited, List<String> res, StringBuilder sb) {
        ///注意，要在return之前判断是否要加入解集
        if (node != null && node.isWord && !set.contains(sb.toString())) {
            res.add(sb.toString());
            set.add(sb.toString());
        }
        if (node == null || i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j]) return;
        sb.append(board[i][j]);
        visited[i][j] = true;
        dfs(i + 1, j, board, node.children[board[i][j] - 'a'], visited, res, sb);
        dfs(i - 1, j, board, node.children[board[i][j] - 'a'], visited, res, sb);
        dfs(i, j + 1, board, node.children[board[i][j] - 'a'], visited, res, sb);
        dfs(i, j - 1, board, node.children[board[i][j] - 'a'], visited, res, sb);
        visited[i][j] = false;
        sb.deleteCharAt(sb.length() - 1);
    }

    private void buildTrie(String[] words, TrieNode node) {
        for (String word : words) insert(word, node);
    }

    public void insert(String word, TrieNode node) {
        for (int i = 0; i < word.length(); i++) {
            if (node.children[word.charAt(i) - 'a'] == null) {
                node.children[word.charAt(i) - 'a'] = new TrieNode(word.charAt(i));
            }
            node = node.children[word.charAt(i) - 'a'];
        }
        node.isWord = true;
    }

    class TrieNode {
        public char val;
        public boolean isWord;
        public TrieNode[] children = new TrieNode[26];

        TrieNode(char v) {
            val = v;
        }
    }

    /**
     * 【优化1】用"#"符号标记visited。可以总结一个routine：如果是backtracking（而非floodFill）匹配的题目，都可以不用visited[][]数组，因为会恢复，所以直接在上面操作就行
     * 【优化2】不用hashSet，直接利用Trie的isWord特性
     * ---------------------------------------------------------------------------------------------------------------------------------------------------------
     */

    public List<String> findWords__(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        if (board == null || board[0] == null || words == null) return res;
        TrieNode trieNode = new TrieNode(' ');
        buildTrie(words, trieNode);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(i, j, board, trieNode, res, new StringBuilder());
            }
        }
        return res;
    }

    private void dfs(int i, int j, char[][] board, TrieNode node, List<String> res, StringBuilder sb) {
        ///注意，要在return之前判断是否要加入解集
        if (node != null && node.isWord) {
            res.add(sb.toString());
            node.isWord = false;
        }
        if (node == null || i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == '#') return;
        sb.append(board[i][j]);
        char tmp = board[i][j];
        board[i][j] = '#';
        dfs(i + 1, j, board, node.children[tmp - 'a'], res, sb);
        dfs(i - 1, j, board, node.children[tmp - 'a'], res, sb);
        dfs(i, j + 1, board, node.children[tmp - 'a'], res, sb);
        dfs(i, j - 1, board, node.children[tmp - 'a'], res, sb);
        board[i][j] = tmp;
        sb.deleteCharAt(sb.length() - 1);
    }

}
