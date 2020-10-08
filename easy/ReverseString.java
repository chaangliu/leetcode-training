package easy;

import java.util.Stack;

/**
 * Created by DrunkPiano on 11/06/2017.
 */

public class ReverseString {
    /**
     * 题意：翻转字符串。
     * 解法：two pointers, left < right
     */
    public void reverseString(char[] s) {
        int n = s.length;
        for (int left = 0, right = n - 1; left < right; ++left, --right) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
        }
    }

    // 如果不要求inplace可以借助stack
    public String reverse(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            stack.push(s.charAt(i));
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    /**
     * 20190304 recursion
     * <p>
     * printReverse(str[1...n-1]): print the substring str[1...n-1] in reverse order.
     * print(str[0]): print the first character in the string.
     */
    public String reverse__RECURSION(String s) {
        char[] arr = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        helper(0, arr, sb);
        return sb.toString();
    }

    private void helper(int index, char[] arr, StringBuilder sb) {
        if (index >= arr.length) return;
        helper(index + 1, arr, sb);
        System.out.print(arr[index]);
        sb.append(arr[index]);
    }


    /**
     * ----------------------------------------------------------------------------------------------------------------
     */
    public static void main(String args[]) {
        new ReverseString().reverse__RECURSION("aware");
    }
}
