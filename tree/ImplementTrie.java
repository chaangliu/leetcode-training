package tree;

/**
 * Created by DrunkPiano on 2017/4/12.
 */

public class ImplementTrie {

    class TrieNode {
        public char val;
        public boolean isWord;
        public TrieNode[] children = new TrieNode[26];

		TrieNode() {
        }

        TrieNode(char c) {
            TrieNode node = new TrieNode();
            node.val = c;
        }
    }

    public class Trie {
        private TrieNode root;

        /**
         * Initialize your data structure here.
         */
        public Trie() {
            root = new TrieNode();
            root.val = ' ';
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                if (node.children[word.charAt(i) - 'a'] == null) {
                    node.children[word.charAt(i) - 'a'] = new TrieNode(word.charAt(i));
                }
                node = node.children[word.charAt(i) - 'a'];
            }
            node.isWord = true;
        }
        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            TrieNode node = root;
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
            TrieNode node = root;
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

    public static void main(String args[]){
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
