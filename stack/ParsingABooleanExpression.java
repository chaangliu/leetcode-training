package stack;

import java.util.HashSet;
import java.util.Stack;

/**
 * Return the result of evaluating a given boolean expression, represented as a string.
 * <p>
 * An expression can either be:
 * <p>
 * "t", evaluating to True;
 * "f", evaluating to False;
 * "!(expr)", evaluating to the logical NOT of the inner expression expr;
 * "&(expr1,expr2,...)", evaluating to the logical AND of 2 or more inner expressions expr1, expr2, ...;
 * "|(expr1,expr2,...)", evaluating to the logical OR of 2 or more inner expressions expr1, expr2, ...
 * Example 1:
 * Input: expression = "!(f)"
 * Output: true
 * Example 2:
 * <p>
 * Input: expression = "|(f,t)"
 * Output: true
 * Example 3:
 * <p>
 * Input: expression = "&(t,f)"
 * Output: false
 * Example 4:
 * <p>
 * Input: expression = "|(&(t,f,t),!(t))"
 * Output: false
 * Constraints:
 * 1 <= expression.length <= 20000
 * expression[i] consists of characters in {'(', ')', '&', '|', '!', 't', 'f', ','}.
 * expression is a valid expression representing a boolean, as given in the description.
 * 20190708
 * [STACK][RECURSION]
 */
public class ParsingABooleanExpression {
    /**
     * "|(&(t,f,t),!(t))"
     * 这题我感觉很像逆波兰表达式那种解法，但是写了下发现没那么容易写(我用了两个stack，其实用一个就行)
     * Approach1 stack
     */
    public boolean parseBoolExpr(String expression) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < expression.length(); ++i) {
            char c = expression.charAt(i);
            if (c == ')') {//从后往前
                HashSet<Character> set = new HashSet<>();
                while (stack.peek() != '(') {
                    set.add(stack.pop());
                }
                stack.pop();//弹出左括号
                char operator = stack.pop();//左括号左边一定是运算符
                if (operator == '&') {
                    stack.push(set.contains('f') ? 'f' : 't');
                } else if (operator == '|') {
                    stack.push(set.contains('t') ? 't' : 'f');
                } else { // ! expression.
                    stack.push(set.contains('t') ? 'f' : 't');
                }
            } else {
                stack.push(c);
            }
        }
        return stack.pop() == 't';
    }


    /**
     * Approach2 recursion
     * 这个解法没有stack直观，递归写法也有好几种
     */
    public boolean parseBoolExpr__RECURSION(String expression) {
        return parse(expression, 0, expression.length());
    }

    private boolean parse(String s, int lo, int hi) {
        char c = s.charAt(lo);
        if (hi - lo == 1) return c == 't'; // base case.
        boolean ans = c == '&'; // only when c is &, set ans to true; otherwise false.
        for (int i = lo + 2, start = i, level = 0; i < hi; ++i) {
            char d = s.charAt(i);
            if (level == 0 && (d == ',' || d == ')')) { // locate a valid sub-expression.
                boolean cur = parse(s, start, i); // recurse to sub-problem.
                start = i + 1; // next sub-expression start index.
                if (c == '&')
                    ans &= cur;
                else if (c == '|')
                    ans |= cur;
                else
                    ans = !cur; // c == '!'.
            }
            if (d == '(')
                ++level;
            if (d == ')')
                --level;
        }
        return ans;
    }
}
