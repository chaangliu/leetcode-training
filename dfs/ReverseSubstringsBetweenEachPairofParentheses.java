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
     * Input: s = "(u(love)i)"
     * 策略是，左括号和字母都入栈，遇到右括号就出栈到下一个左括号弹出来，把弹出的字母保存一下，再次进栈。比如love,弹出来，成了evol，再装回去，栈中是(uevol,
     * 继续入栈，uevoli，出栈，直到stack为空
     */
    public String reverseParentheses_STA(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c != ')') {
                stack.push(c);
            } else {
                StringBuilder tmp = new StringBuilder();
                while (!stack.empty() && stack.peek() != '(') {
                    tmp.append(c);
                }
                if (!stack.empty() && stack.peek() == '(') stack.pop();
                if (stack.empty()) return tmp.toString();
                for (char t : tmp.toString().toCharArray()) {
                    stack.push(t);
                }
            }
        }
        return null;
    }
}
