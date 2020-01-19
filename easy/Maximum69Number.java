package easy;

import java.util.Stack;

/**
 * Given a positive integer num consisting only of digits 6 and 9.
 * Return the maximum number you can get by changing at most one digit (6 becomes 9, and 9 becomes 6).
 * Example 1:
 * Input: num = 9669
 * Output: 9969
 * Explanation:
 * Changing the first digit results in 6669.
 * Changing the second digit results in 9969.
 * Changing the third digit results in 9699.
 * Changing the fourth digit results in 9666.
 * The maximum number is 9969.
 * Example 2:
 * Input: num = 9996
 * Output: 9999
 * Explanation: Changing the last digit 6 to 9 results in the maximum number.
 * Example 3:
 * Input: num = 9999
 * Output: 9999
 * Explanation: It is better not to apply any change.
 * Constraints:
 * 1 <= num <= 10^4
 * 20200119
 */
public class Maximum69Number {
    /**
     * 题意：给你一个正整数，所有数字都是6或者9，让你修改其中一个数字，让这个数字最大。
     * 思路：那肯定就是从高位向低位找第一个不是9的数字换成9。我一开始想用string，觉得太鸡贼，就借助了个stack。
     */
    public int maximum69Number(int num) {
        int d = 0;
        Stack<Integer> stack = new Stack<>();
        while (num != 0) {
            d = num % 10;
            num = num / 10;
            stack.push(d);
        }
        boolean found = false;
        int res = 0;
        while (!stack.empty()) {
            d = stack.pop();
            if (!found && d != 9) {
                d = 9;
                found = true;
            }
            res = res * 10 + d;
        }
        return res;
    }

    /**
     * string做法
     */
    public int maximum69Number_(int num) {
        char[] chars = Integer.toString(num).toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '6') {
                chars[i] = '9';
                break;
            }
        }
        return Integer.parseInt(new String(chars));
    }
}



