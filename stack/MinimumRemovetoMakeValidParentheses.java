package stack;

import java.util.Stack;

/**
 * Given a string s of '(' , ')' and lowercase English characters.
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.
 * Formally, a parentheses string is valid if and only if:
 * It is the empty string, contains only lowercase characters, or
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 * Example 1:
 * Input: s = "lee(t(c)o)de)"
 * Output: "lee(t(c)o)de"
 * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 * Example 2:
 * Input: s = "a)b(c)d"
 * Output: "ab(c)d"
 * Example 3:
 * Input: s = "))(("
 * Output: ""
 * Explanation: An empty string is also valid.
 * Example 4:
 * Input: s = "(a(b(c)d)"
 * Output: "a(b(c)d)"
 * Constraints:
 * 1 <= s.length <= 10^5
 * s[i] is one of  '(' , ')' and lowercase English letters.
 * 20191103
 */
public class MinimumRemovetoMakeValidParentheses {
    /**
     * 题意：给你一个字符串，包含字母和左右括号，让你把字符串处理成任意一种合法的形式。
     * 周赛第三题。我感觉应该会比较难，就一直想着用递归/DP，但赛后发现很简单，只要用Stack，最后处理一下。时间O(n)。
     * 见到括号Parentheses题目就要想到是天然的stack题。
     */
    public String minRemoveToMakeValid(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == ')') {
                if (stack.isEmpty()) continue;
                sb.append(c);
                stack.pop();
            } else {
                if (c == '(') stack.push(c);
                sb.append(c);
            }
        }
        int size = stack.size();
        for (int j = sb.length() - 1; size > 0 && j >= 0; j--) {
            if (sb.charAt(j) == '(') {
                sb.deleteCharAt(j);
                size--;
            }
        }
        return sb.toString();
    }

    /**
     * 讨论区技巧，用特殊符号标记多余的右括号)
     */
    public String minRemoveToMakeValid__(String s) {
        StringBuilder sb = new StringBuilder(s);
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < sb.length(); ++i) {
            if (sb.charAt(i) == '(') st.add(i);
            if (sb.charAt(i) == ')') {
                if (!st.empty()) st.pop();
                else sb.setCharAt(i, '*');
            }
        }
        while (!st.empty()) {
            sb.setCharAt(st.peek(), '*');
            st.pop();
        }
        return sb.toString().replaceAll("\\*", "");
    }
}
