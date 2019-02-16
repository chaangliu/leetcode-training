package stack;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * <p>
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 */

public class ValidParatheses {
    /**
     * 20190216review
     */
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) return true;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[') {
                stack.push(s.charAt(i));
            } else if (s.charAt(i) == ')') {
                if (stack.empty() || stack.pop() != '(') return false;
            } else if (s.charAt(i) == '}') {
                if (stack.empty() || stack.pop() != '{') return false;
            } else if (s.charAt(i) == ']') {
                if (stack.empty() || stack.pop() != '[') return false;
            }
        }
        return stack.empty();//不能直接return true，不然'['过不了
    }


    /**
     * initial post
     */
    public boolean isValid__INITIAL_POST(String s) {
        int len = s.length();

        LinkedList<String> stack = new LinkedList<>();

        for (int i = 0; i < len; i++) {
            switch (String.valueOf(s.charAt(i))) {
                case "(":
                    stack.push("(");
                    break;
                case "{":
                    stack.push("{");
                    break;
                case "[":
                    stack.push("[");
                    break;
                case ")":
                    if (stack.isEmpty() || !stack.pop().equals("(")) return false;
                    break;
                case "}":
                    if (stack.isEmpty() || !stack.pop().equals("{")) return false;
                    break;
                case "]":
                    if (stack.isEmpty() || !stack.pop().equals("[")) return false;
                    break;
            }
        }

        return stack.isEmpty();
    }

    public static void main(String args[]) {
        ValidParatheses validParatheses = new ValidParatheses();
        System.out.println(validParatheses.isValid("){"));
    }
}