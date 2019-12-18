package stack;

import java.util.Stack;

/**
 * Implement a basic calculator to evaluate a simple expression string.
 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.
 * Example 1:
 * Input: "3+2*2"
 * Output: 7
 * Example 2:
 * Input: " 3/2 "
 * Output: 1
 * Example 3:
 * Input: " 3+5 / 2 "
 * Output: 5
 * Note:
 * You may assume that the given expression is always valid.
 * Do not use the eval built-in library function.
 * 20191218
 */
public class BasicCalculatorII {
    /**
     * 题意：给你一个只有'+ - * / '和正整数组成的字符串，计算结果。
     * 解法：记录数字和前一个operator，每次遇到新的operator或者到了s.len - 1，就计算前面的operator两侧的值。
     */
    public int calculate(String s) {
        char prevOperator = '+';
        int num = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ' && i != s.length() - 1) continue;
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            }
            if (!Character.isDigit(s.charAt(i)) || i == s.length() - 1) {
                if (prevOperator == '+') {//注意，这里是prevOperator，而不是当前operator
                    stack.push(num);
                } else if (prevOperator == '-') {
                    stack.push(-num);
                } else if (prevOperator == '*') {
                    stack.push(stack.pop() * num);
                } else if (prevOperator == '/') {
                    stack.push(stack.pop() / num);
                }
                prevOperator = s.charAt(i);
                num = 0;
            }
        }
        int res = 0;
        while (!stack.empty()) {
            res += stack.pop();
        }
        return res;
    }

    /**
     * O(1)space one pass, 思路一样但是不用stack保存
     */
    public int calculate__(String s) {
        int sum = 0;
        int tempSum = 0;
        int num = 0;
        char lastSign = '+';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) num = num * 10 + c - '0';
            if (i == s.length() - 1 || !Character.isDigit(c) && c != ' ') {
                switch (lastSign) {
                    case '+':
                        sum += tempSum;
                        tempSum = num;
                        break;
                    case '-':
                        sum += tempSum;
                        tempSum = -num;
                        break;
                    case '*':
                        tempSum *= num;
                        break;
                    case '/':
                        tempSum /= num;
                        break;
                }
                lastSign = c;
                num = 0;
            }
        }
        sum += tempSum;
        return sum;
    }
}
