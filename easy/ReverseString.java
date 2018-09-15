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

	public static void main(String args[]){
		new ReverseString().reverse("aware");
	}
}
