package easy;

import java.util.Stack;

/**
 * Created by DrunkPiano on 11/06/2017.
 */

public class ReverseString {
    //	三种方法
//	1. two pointers, i < j
//	2. j >= 0 ; j --
//	3. Stack
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
