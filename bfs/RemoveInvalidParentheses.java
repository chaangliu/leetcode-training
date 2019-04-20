package bfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
 * <p>
 * Note: The input string may contain letters other than the parentheses ( and ).
 * <p>
 * Example 1:
 * <p>
 * Input: "()())()"
 * Output: ["()()()", "(())()"]
 * Example 2:
 * <p>
 * Input: "(a)())()"
 * Output: ["(a)()()", "(a())()"]
 * Example 3:
 * <p>
 * Input: ")("
 * Output: [""]
 * <p>
 * 20190420
 */
public class RemoveInvalidParentheses {

    /**
     * approach 1. bfs
     * 思路：每个level尝试把每个括号都移走然后加入queue，如果仍不满足；进入下一个level，继续remove。
     * 需要一个visited，不然TLE，而且无法去掉重复ANSWER。有点像OPEN THE LOCK那题。
     */
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null) return res;
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        q.offer(s);
        boolean found = false;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String tmp = q.poll();
                if (isValid(tmp)) {
                    res.add(tmp);
                    found = true;
                }
                if (found) continue;
                for (int j = 0; j < tmp.length(); j++) {
                    if (tmp.charAt(j) == '(' || tmp.charAt(j) == ')') {
                        String t = tmp.substring(0, j) + tmp.substring(j + 1);
                        if (!visited.contains(t)) {
                            q.offer(t);
                            visited.add(t);
                        }
                    }
                }
            }
        }
        return res;
    }

    //helper function checks if string s contains valid parentheses
    boolean isValid(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') count++;
            if (c == ')' && count-- == 0) return false;
        }
        return count == 0;
    }


    /**
     * approach 1. dfs
     * 下面的代码摘抄自目前最高票答案；
     * 递归操作有点风骚，尤其是还要反过来这一点，我觉得很难写出来
     */
    public List<String> removeInvalidParentheses___DFS(String s) {
        List<String> ans = new ArrayList<>();
        remove(s, ans, 0, 0, new char[]{'(', ')'});
        return ans;
    }

    public void remove(String s, List<String> ans, int last_i, int last_j, char[] par) {
        for (int stack = 0, i = last_i; i < s.length(); ++i) {
            if (s.charAt(i) == par[0]) stack++;
            if (s.charAt(i) == par[1]) stack--;
            if (stack >= 0) continue;
            for (int j = last_j; j <= i; ++j)
                if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1]))
                    remove(s.substring(0, j) + s.substring(j + 1, s.length()), ans, i, j, par);
            return;
        }
        String reversed = new StringBuilder(s).reverse().toString();
        if (par[0] == '(') // finished left to right
            remove(reversed, ans, 0, 0, new char[]{')', '('});
        else // finished right to left
            ans.add(reversed);
    }
}
