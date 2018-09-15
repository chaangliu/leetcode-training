package dp;

import java.util.LinkedList;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * <p>
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 */

public class ValidParatheses {
    public boolean isValid(String s) {
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