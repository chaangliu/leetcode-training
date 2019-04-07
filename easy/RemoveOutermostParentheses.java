package easy;

/**
 * A valid parentheses string is either empty (""), "(" + A + ")", or A + B, where A and B are valid parentheses strings, and + represents string concatenation.  For example, "", "()", "(())()", and "(()(()))" are all valid parentheses strings.
 * <p>
 * A valid parentheses string S is primitive if it is nonempty, and there does not exist a way to split it into S = A+B, with A and B nonempty valid parentheses strings.
 * <p>
 * Given a valid parentheses string S, consider its primitive decomposition: S = P_1 + P_2 + ... + P_k, where P_i are primitive valid parentheses strings.
 * <p>
 * Return S after removing the outermost parentheses of every primitive string in the primitive decomposition of S.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: "(()())(())"
 * Output: "()()()"
 * Explanation:
 * The input string is "(()())(())", with primitive decomposition "(()())" + "(())".
 * After removing outer parentheses of each part, this is "()()" + "()" = "()()()".
 * Example 2:
 * <p>
 * Input: "(()())(())(()(()))"
 * Output: "()()()()(())"
 * Explanation:
 * The input string is "(()())(())(()(()))", with primitive decomposition "(()())" + "(())" + "(()(()))".
 * After removing outer parentheses of each part, this is "()()" + "()" + "()(())" = "()()()()(())".
 * Example 3:
 * <p>
 * Input: "()()"
 * Output: ""
 * Explanation:
 * The input string is "()()", with primitive decomposition "()" + "()".
 * After removing outer parentheses of each part, this is "" + "" = "".
 * <p>
 * 20190407
 */
public class RemoveOutermostParentheses {
    /**
     * weekly contest131的签到题。一开始想用Stack后来发现不用。想清楚就好
     */
    public String removeOuterParentheses(String S) {
        StringBuilder res = new StringBuilder();
        int lCount = 0;
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == '(') {
                if (i == 0 || lCount == 0) {
                    lCount++;
                    continue;
                }
                lCount++;
            } else {
                if (--lCount == 0) continue;
            }
            res.append(S.charAt(i));
        }
        return res.toString();
    }
}
