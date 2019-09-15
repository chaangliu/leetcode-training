package dfs;

/**
 * Given a string s that consists of lower case English letters and brackets.
 * <p>
 * Reverse the strings in each pair of matching parentheses, starting from the innermost one.
 * <p>
 * Your result should not contain any bracket.
 * Example 1:
 * <p>
 * Input: s = "(abcd)"
 * Output: "dcba"
 * Example 2:
 * <p>
 * Input: s = "(u(love)i)"
 * Output: "iloveu"
 * Example 3:
 * <p>
 * Input: s = "(ed(et(oc))el)"
 * Output: "leetcode"
 * Example 4:
 * <p>
 * Input: s = "a(bcdefghijkl(mno)p)q"
 * Output: "apmnolkjihgfedcbq"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= s.length <= 2000
 * s only contains lower case English characters and parentheses.
 * It's guaranteed that all parentheses are balanced.
 * 20190915
 */
public class ReverseSubstringsBetweenEachPairofParentheses {
    /**
     * 这题拿到之后就觉得应该是用递归来做了，参考了Decode String那题，搞了好久总算搞出来
     * Approach1 recursion
     */
    public String reverseParentheses(String s) {
        return helper(s.toCharArray());
    }

    int index = 0;

    private String helper(char[] array) {
        StringBuilder tmp = new StringBuilder();
        while (index < array.length) {
            if (array[index] != '(' && array[index] != ')') {
                tmp.append(array[index++]);//字母
            } else if (array[index] == '(') {
                index++;
                String sub = helper(array);
                index++;
                StringBuilder sb = new StringBuilder(sub);
                tmp.append(sb.reverse());
            } else {
                break;
            }
        }
        return tmp.toString();
    }
}
