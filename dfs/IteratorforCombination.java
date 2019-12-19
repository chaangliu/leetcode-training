package dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * Design an Iterator class, which has:
 * <p>
 * A constructor that takes a string characters of sorted distinct lowercase English letters and a number combinationLength as arguments.
 * A function next() that returns the next combination of length combinationLength in lexicographical order.
 * A function hasNext() that returns True if and only if there exists a next combination.
 * Example:
 * CombinationIterator iterator = new CombinationIterator("abc", 2); // creates the iterator.
 * iterator.next(); // returns "ab"
 * iterator.hasNext(); // returns true
 * iterator.next(); // returns "ac"
 * iterator.hasNext(); // returns true
 * iterator.next(); // returns "bc"
 * iterator.hasNext(); // returns false
 * Constraints:
 * 1 <= combinationLength <= characters.length <= 15
 * There will be at most 10^4 function calls per test.
 * It's guaranteed that all calls of the function next are valid.
 * 20191215
 */
public class IteratorforCombination {
    /**
     * 题意：设计一个类，能够按照字典序输出string s的长度为k的combination。
     * 双周赛第三题。直接用模仿了combinations那题的代码。
     */
    class CombinationIterator {
        List<String> res = new ArrayList<>();
        int i = 0;

        public CombinationIterator(String characters, int len) {
            dfs(0, characters, len, res, "");
        }

        public String next() {
            return hasNext() ? res.get(i++) : "";
        }

        public boolean hasNext() {
            return i < res.size();
        }

        private void dfs(int start, String s, int len, List<String> res, String cell) {
            if (cell.length() == len) {
                res.add(cell);
                return;
            }
            for (int i = start; i < s.length(); i++) {
                dfs(i + 1, s, len, res, cell + s.charAt(i));
            }
        }
    }
}
