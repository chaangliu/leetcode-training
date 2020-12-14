package tree.trie;

/**
 * Implement a trie with insert, search, and startsWith methods.
 * Example:
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // returns true
 * trie.search("app");     // returns false
 * trie.startsWith("app"); // returns true
 * trie.insert("app");
 * trie.search("app");     // returns true
 * Note:
 * <p>
 * You may assume that all inputs are consist of lowercase letters a-z.
 * All inputs are guaranteed to be non-empty strings.
 * Created by DrunkPiano on 2017/4/12.
 */

public class ImplementTrie {
    /**
     * 题意：实现前缀树的insert、search、startWith接口。
     * 解法：用一个空节点以及26叉树节点buildTree。
     * 后来我发现，DFA算法用的就是类似思想。
     * DFA简介
     * 在实现文字过滤的算法中，DFA是唯一比较好的实现算法。DFA即Deterministic Finite Automaton，也就是确定有穷自动机
     */
    public class Trie {
        // public char val; // 不需要这个成员变量
        public boolean isWord;
        public Trie[] children = new Trie[26];

        /**
         * Initialize your data structure here.
         */
        public Trie() {
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                if (node.children[word.charAt(i) - 'a'] == null) {
                    node.children[word.charAt(i) - 'a'] = new Trie();
                }
                node = node.children[word.charAt(i) - 'a'];
            }
            node.isWord = true;
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                if (node.children[word.charAt(i) - 'a'] != null) {
                    //改变指向的方法 相当于二叉树的node.left ,right，只不过trie可以有26棵subtree
                    node = node.children[word.charAt(i) - 'a'];
                } else {
                    return false;
                }
            }
            return node.isWord;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            Trie node = this;
            for (int i = 0; i < prefix.length(); i++) {
                if (node.children[prefix.charAt(i) - 'a'] == null) {
                    return false;
                }
                //改变指向的方法 相当于二叉树的node.left ,right，只不过trie可以有26棵subtree
                node = node.children[prefix.charAt(i) - 'a'];
            }
            return true;
        }
    }

// 以下是最初lc英文版上的答案，事实上我发现对于只有26个字母的TrieNode是不需要val这个成员变量的，因为只要判断node的某个格子是不是null就能知道它下一位的值了，无论insert，search还是startsWith都用不到这个val。对于children的数据类型不规则的node，是需要的。

//    class TrieNode {
//        public char val;
//        public boolean isWord;
//        public TrieNode[] children = new TrieNode[26];
//
//        TrieNode() {
//        }
//
//        TrieNode(char c) {
//            TrieNode node = new TrieNode();
//            node.val = c;
//        }
//    }
//
//    public class Trie {
//        private TrieNode root;
//
//        /**
//         * Initialize your data structure here.
//         */
//        public Trie() {
//            root = new TrieNode();
//            root.val = ' ';
//        }
//
//        /**
//         * Inserts a word into the trie.
//         */
//        public void insert(String word) {
//            TrieNode node = root;
//            for (int i = 0; i < word.length(); i++) {
//                if (node.children[word.charAt(i) - 'a'] == null) {
//                    node.children[word.charAt(i) - 'a'] = new TrieNode(word.charAt(i));
//                }
//                node = node.children[word.charAt(i) - 'a'];
//            }
//            node.isWord = true;
//        }
//
//        /**
//         * Returns if the word is in the trie.
//         */
//        public boolean search(String word) {
//            TrieNode node = root;
//            for (int i = 0; i < word.length(); i++) {
//                if (node.children[word.charAt(i) - 'a'] != null) {
//                    //改变指向的方法 相当于二叉树的node.left ,right，只不过trie可以有26棵subtree
//                    node = node.children[word.charAt(i) - 'a'];
//                } else {
//                    return false;
//                }
//            }
//            return node.isWord;
//        }
//
//        /**
//         * Returns if there is any word in the trie that starts with the given prefix.
//         */
//        public boolean startsWith(String prefix) {
//            TrieNode node = root;
//            for (int i = 0; i < prefix.length(); i++) {
//                if (node.children[prefix.charAt(i) - 'a'] == null) {
//                    return false;
//                }
//                //改变指向的方法 相当于二叉树的node.left ,right，只不过trie可以有26棵subtree
//                node = node.children[prefix.charAt(i) - 'a'];
//            }
//            return true;
//        }
//    }

    public static void main(String args[]) {
        Trie trie = new ImplementTrie().new Trie();
        trie.insert("abc");
        trie.startsWith("ab");
    }

//    With my solution I took the simple approach of giving each TrieNode a 26 element array of each possible child node it may have.
//    I only gave 26 children nodes because we are only working with lowercase 'a' - 'z'.
//    If you are uncertain why I made the root of my Trie an empty character this is a standard/typical approach for building out a Trie it is somewhat arbitrary what the root node is.
//
//    For insert I used the following algorithm.
//    Loop through each character in the word being inserted check if the character is a child node of the current TrieNode
//    i.e. check if the array has a populated value in the index of this character.
//    If the current character ISN'T a child node of my current node add this character representation to the corresponding index location then set current node equal to the child that was added. However if the current character IS a child of the current node only set current node equal to the child. After evaluating the entire String the Node we left off on is marked as a word this allows our Trie to know which words exist in our "dictionary"
//
//    For search I simply navigate through the Trie if I discover the current character isn't in the Trie I return false.
//    After checking each Char in the String I check to see if the Node I left off on was marked as a word returning the result.
//
//    Starts with is identical to search except it doesn't matter if the Node I left off was marked as a word or not if entire string evaluated i always return true;
}
