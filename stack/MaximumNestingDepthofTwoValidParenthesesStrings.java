package stack;

import java.util.Stack;

/**
 * A string is a valid parentheses string (denoted VPS) if and only if it consists of "(" and ")" characters only, and:
 * <p>
 * It is the empty string, or
 * It can be written as AB (A concatenated with B), where A and B are VPS's, or
 * It can be written as (A), where A is a VPS.
 * We can similarly define the nesting depth depth(S) of any VPS S as follows:
 * <p>
 * depth("") = 0
 * depth(A + B) = max(depth(A), depth(B)), where A and B are VPS's
 * depth("(" + A + ")") = 1 + depth(A), where A is a VPS.
 * For example,  "", "()()", and "()(()())" are VPS's (with nesting depths 0, 1, and 2), and ")(" and "(()" are not VPS's.
 * Given a VPS seq, split it into two disjoint subsequences A and B, such that A and B are VPS's (and A.length + B.length = seq.length).
 * Now choose any such A and B such that max(depth(A), depth(B)) is the minimum possible value.
 * Return an answer array (of length seq.length) that encodes such a choice of A and B:  answer[i] = 0 if seq[i] is part of A, else answer[i] = 1.  Note that even though multiple answers may exist, you may return any of them.
 * Example 1:
 * Input: seq = "(()())"
 * Output: [0,1,1,1,1,0]
 * Example 2:
 * <p>
 * Input: seq = "()(())()"
 * Output: [0,0,0,1,1,0,1,1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= seq.size <= 10000
 * 20190707
 */
public class MaximumNestingDepthofTwoValidParenthesesStrings {
    //()( () ) ()
    //000 11 0 11
    //000 11 0 11

    /**
     * 把string分成两个不连贯的子序列，使得他们的深度之和最小。
     * 看了这个老弟(https://www.bilibili.com/video/av58174871)的讲解写出来的。
     * 思路是，把深度为odd和深度为even的数组分到两个子集里。这样最后的和一定是(seq深度 + 1)/2
     */
    public int[] maxDepthAfterSplit(String seq) {
        int[] res = new int[seq.length()];
        int flag = 1;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < seq.length(); i++) {
            if (seq.charAt(i) == '(') {
                if (stack.isEmpty() || stack.peek() == '(') {
                    flag = (flag + 1) % 2;
                }
                stack.push('(');
                res[i] = flag;
            } else {
                stack.pop();
                res[i] = flag;
                flag = (flag + 1) % 2;
            }
        }
        return res;
    }
}
