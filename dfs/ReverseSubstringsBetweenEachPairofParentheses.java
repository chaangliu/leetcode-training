package dfs;

import java.util.Stack;

/**
 * Given a string s that consists of lower case English letters and brackets.
 * <p>
 * Reverse the strings in each pair of matching parentheses, starting from the innermost one.
 * <p>
 * Your result should not contain any bracket.
 * Example 1:
 * <p>
 * Input: s = "(abcd)"
 * Output: "dcba"
 * Example 2:
 * <p>
 * Input: s = "(u(love)i)"
 * Output: "iloveu"
 * Example 3:
 * <p>
 * Input: s = "(ed(et(oc))el)"
 * Output: "leetcode"
 * Example 4:
 * <p>
 * Input: s = "a(bcdefghijkl(mno)p)q"
 * Output: "apmnolkjihgfedcbq"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= s.length <= 2000
 * s only contains lower case English characters and parentheses.
 * It's guaranteed that all parentheses are balanced.
 * 20190915
 */
public class ReverseSubstringsBetweenEachPairofParentheses {
    /**
     * 这题拿到之后就觉得应该是用递归来做了，参考了Decode String那题，搞了好久总算搞出来
     * Approach1 recursion
     */
    public String reverseParentheses(String s) {
        return helper(s.toCharArray());
    }

    int index = 0;

    private String helper(char[] array) {
        StringBuilder tmp = new StringBuilder();
        while (index < array.length) {
            if (array[index] != '(' && array[index] != ')') {
                tmp.append(array[index++]);//字母
            } else if (array[index] == '(') {
                index++;
                String sub = helper(array);
                index++;
                StringBuilder sb = new StringBuilder(sub);
                tmp.append(sb.reverse());
            } else {
                break;
            }
        }
        return tmp.toString();
    }

    /**
     * Approach2, stack
     * 用stack改写了一下，确实比较容易理解，不过容易漏掉一些case，比如
     * "a(bcdefghijkl(mno)p)q"
     * "co(de(fight)s)"
     */
    public String reverseParentheses__(String s) {
        Stack<Character> stack = new Stack<>();
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            StringBuilder tmp = new StringBuilder();
            if (c != ')') {
                stack.push(c);
            } else {
                while (!stack.empty() && stack.peek() != '(') {
                    tmp.append(stack.pop());
                }
                if (!stack.empty() && stack.peek() == '(') stack.pop();// pop '('
                for (int j = 0; j < tmp.length(); j++) {
                    stack.push(tmp.charAt(j));
                }
            }
            if (i == s.length() - 1) {
                StringBuilder res = new StringBuilder();
                while (!stack.empty()) res.append(stack.pop());
                return res.reverse().toString();
            }
            i++;
        }
        return null;
    }

    /**
     * 看到一个奇怪的recursion写法：https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/discuss/382358/Simple-Java-Sol(Recursion)
     * 重复扫描了开头和结尾
     */
    public String reverseParentheses_(String s) {
        int begin = 0;
        int end;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                begin = i;
            if (s.charAt(i) == ')') {
                end = i;
                String temp = s.substring(begin + 1, end);
                return reverseParentheses(s.substring(0, begin) + reverseString(temp) + s.substring(end + 1));
            }
        }
        return s;
    }

    String reverseString(String s) {
        char[] temp = s.toCharArray();
        StringBuilder r = new StringBuilder();
        for (int i = temp.length - 1; i >= 0; i--)
            r.append(temp[i]);

        return r.toString();
    }
}
