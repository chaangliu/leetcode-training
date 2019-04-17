package dfs;

/**
 * Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this string is valid. We define the validity of a string by these rules:
 * <p>
 * Any left parenthesis '(' must have a corresponding right parenthesis ')'.
 * Any right parenthesis ')' must have a corresponding left parenthesis '('.
 * Left parenthesis '(' must go before the corresponding right parenthesis ')'.
 * '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
 * An empty string is also valid.
 * Example 1:
 * Input: "()"
 * Output: True
 * Example 2:
 * Input: "(*)"
 * Output: True
 * Example 3:
 * Input: "(*))"
 * Output: True
 * <p>
 * 20190415
 */
public class ValidParenthesisString {

    /**
     * approach1. dfs，右括号的数量不能多余左括号，遇到星号就尝试每种可能(做括号+1，右括号加1或不动)，有一种成功即可
     * 以下是我的代码（超时了），短的可以过，长的TLE
     */

    public boolean checkValidString(String s) {
        return helper(s, 0, 0);
    }

    private boolean helper(String s, int index, int cnt) {
        if (cnt < 0) return false;
        if (index == s.length()) return cnt == 0;
        char c = s.charAt(index);
        if (c == '*' && (helper(s, index + 1, cnt + 1) || helper(s, index + 1, cnt - 1) || helper(s, index + 1, cnt))) return true;
        else if (c == '(') cnt++;
        else if (c == ')') cnt--;
        return helper(s, index + 1, cnt);
    }

    /**
     * 以下是好的写法，用for循环，优势就是对于不是*的情况可以不去递归
     */
    public boolean checkValidString__(String s) {
        return check(s, 0, 0);
    }

    private boolean check(String s, int start, int count) {
        if (count < 0) return false;

        for (int i = start; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                count++;
            } else if (c == ')') {
                if (count <= 0) return false;
                count--;
            } else if (c == '*') {
                return check(s, i + 1, count + 1) || check(s, i + 1, count - 1) || check(s, i + 1, count);
            }
        }
        return count == 0;
    }

    /**
     * approach2 O(n)解法
     * <p>
     * 在评论区看到一个O(n)解法，找nl和nr之差的上限和下限，如果把*都算成)，upper还是小于0，说明)太多了。
     * 值得注意的是lower = max(lower, 0)的操作， 似乎是应对"* ("这种情况
     */
    boolean checkValidString(char[] s) {
        int lower = 0, upper = 0;
        for (char c : s) {
            if (c == '(') {
                lower++;
                upper++;
            } else if (c == ')') {
                lower--;
                upper--;
            } else { // * encountered
                lower--;
                upper++;
            }
            lower = Math.max(lower, 0);//这句不太懂
            if (upper < 0) // unmatched ')' found in the middle of string
                return false;
        }
        return lower == 0;
    }

}
