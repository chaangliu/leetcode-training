package stack;

import java.util.Stack;

/**
 * Implement a basic calculator to evaluate a simple expression string.
 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
 * Example 1:
 * Input: "1 + 1"
 * Output: 2
 * Example 2:
 * Input: " 2-1 + 2 "
 * Output: 3
 * Example 3:
 * Input: "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 * Note:
 * You may assume that the given expression is always valid.
 * Do not use the eval built-in library function.
 * 20191219
 */
public class BasicCalculator {
    /**
     * 题意：给你一个包含非负整数、+-()的合法字符串，求运算结果。
     * 这题TAG是Stack,我在想是不是要递归地去求括号里面的数，但是看到答案发现，人家直接把括号展开了。
     * 比较巧妙的是用了个技巧，Stack里面存放的是+-1，表示[当前层括号外面]的sign，比如1-(2+3+4)，在遇到左括号的时候，应该把-1push到stack中，遇到右括号的时候再pop出来。
     * <p>
     * Start from +1 sign and scan s from left to right;
     * if c == digit: This number = Last digit * 10 + This digit;
     * if c == '+': Add num to result before this sign; This sign = Last context sign * 1; clear num;
     * if c == '-': Add num to result before this sign; This sign = Last context sign * -1; clear num;
     * if c == '(': Push this context sign to stack;
     * if c == ')': Pop this context and we come back to last context;
     * Add the last num. This is because we only add number after '+' / '-'.
     */
    public int calculate(String s) {
        if (s == null) return 0;
        int num = 0, sign = 1, res = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(sign);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else if (c == '+' || c == '-') {
                res += sign * num;
                sign = stack.peek() * (c == '+' ? 1 : -1);
                num = 0;
            } else if (c == '(') {
                stack.push(sign);
            } else if (c == ')') {
                stack.pop();
            }
        }
        return res + sign * num;
    }
}
