package dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * A string S represents a list of words.
 * <p>
 * Each letter in the word has 1 or more options.  If there is one option, the letter is represented as is.  If there is more than one option, then curly braces delimit the options.  For example, "{a,b,c}" represents options ["a", "b", "c"].
 * <p>
 * For example, "{a,b,c}d{e,f}" represents the list ["ade", "adf", "bde", "bdf", "cde", "cdf"].
 * <p>
 * Return all words that can be formed in this manner, in lexicographical order.
 * Example 1:
 * <p>
 * Input: "{a,b}c{d,e}f"
 * Output: ["acdf","acef","bcdf","bcef"]
 * Example 2:
 * <p>
 * Input: "abcd"
 * Output: ["abcd"]
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= S.length <= 50
 * There are no nested curly brackets.
 * All characters inside a pair of consecutive opening and ending curly brackets are different.
 * 20190615
 */
public class BraceExpansion {
    /**
     * 昨晚的biweekly contest的第三题，当时名字叫1087. Permutation of Letters My SubmissionsBack to Contest，现在改名了
     * 锁上了，没法提交，暂时不确定能否AC，不过给的那个case："{a,b}c{d,e}f"能过
     * 这题我感觉思路很清楚，图都画出来了，四层不停往下递归，但当时就是没写出来；今天看了下，跟17题一样。要看到如何开始递归。
     */
    public String[] permute(String S) {
        List<String> res = new ArrayList<>();
        boolean closure = true;
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (char c : S.toCharArray()) {
            if (c == ',') continue;
            if (c == '{') {
                closure = false;
                continue;
            }
            if (c == '}') {
                closure = true;
                list.add(sb.toString());
                sb = new StringBuilder();
                continue;
            }
            if (!closure) {
                sb.append(c);
                continue;
            }
            list.add(c + "");
        }
        dfs(res, list, "", 0);
        String[] result = new String[res.size()];
        for (int i = 0; i < res.size(); i++) {
            result[i] = res.get(i);
        }
        return result;
    }

    private void dfs(List<String> res, List<String> layer, String tmp, int depth) {
        if (depth >= layer.size()) {
            res.add(tmp);
            return;
        }
        String s = layer.get(depth);
        for (char c : s.toCharArray()) {
            dfs(res, layer, tmp + c, depth + 1);
        }
    }
}
