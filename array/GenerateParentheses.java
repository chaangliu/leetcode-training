package array;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * <p>
 * For example, given n = 3, a solution set is:
 * <p>
 * [
 * "( ( () ) )",
 * "( () () )",
 * "( () ) ()",
 * "() ( () )",
 * "() () ()"
 * ]
 * Created by DrunkPiano on 2017/2/18.
 * 20190103 review
 */

public class GenerateParentheses {
    /**
     * 题意：给你一个数字n代表n对括号，让你生成所有可能的括号组合
     * 当左括号出现次数<n时，就可以放置新的左括号。当右括号出现次数小于左括号出现次数时，就可以放置新的右括号。
     * n=3时下面解法生成的括号：["((()))","(()())","(())()","()(())","()()()"]
     */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        dfs(res, "", 0, 0, n);
        return res;
    }

    private void dfs(List<String> res, String cur, int l, int r, int n) {
        if (l > n || l < r) return;//已犯错误：这个条件写在了添加res的后面
        if (cur.length() == n * 2) {
            res.add(cur);
            return;
        }
        dfs(res, cur + "(", l + 1, r, n);
        dfs(res, cur + ")", l, r + 1, n);
    }
}
