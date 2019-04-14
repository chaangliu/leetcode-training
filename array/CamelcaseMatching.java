package array;

import java.util.ArrayList;
import java.util.List;

/**
 * A query word matches a given pattern if we can insert lowercase letters to the pattern word so that it equals the query. (We may insert each character at any position, and may insert 0 characters.)
 * <p>
 * Given a list of queries, and a pattern, return an answer list of booleans, where answer[i] is true if and only if queries[i] matches the pattern.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FB"
 * Output: [true,false,true,true,false]
 * Explanation:
 * "FooBar" can be generated like this "F" + "oo" + "B" + "ar".
 * "FootBall" can be generated like this "F" + "oot" + "B" + "all".
 * "FrameBuffer" can be generated like this "F" + "rame" + "B" + "uffer".
 * Example 2:
 * <p>
 * Input: queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBa"
 * Output: [true,false,true,false,false]
 * Explanation:
 * "FooBar" can be generated like this "Fo" + "o" + "Ba" + "r".
 * "FootBall" can be generated like this "Fo" + "ot" + "Ba" + "ll".
 * Example 3:
 * <p>
 * Input: queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBaT"
 * Output: [false,true,false,false,false]
 * Explanation:
 * "FooBarTest" can be generated like this "Fo" + "o" + "Ba" + "r" + "T" + "est".
 * <p>
 * 20190410
 */
public class CamelcaseMatching {
    /**
     * 四月第一周的contest的第三题；
     * 本身题目有点容易让人误解。
     * 解法用two pointers设定好条件即可，注意i，j指针不要指错
     */
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> res = new ArrayList<>();
        for (String q : queries) {
            res.add(valid(q, pattern));
        }
        return res;
    }

    private boolean valid(String q, String p) {
        int i = 0, j = 0;
        while (i < q.length() && j < p.length()) {
            char ci = q.charAt(i);
            char cj = p.charAt(j);
            if (isUpper(ci)) {
                if (ci == cj) {
                    i++;
                    j++;
                } else {
                    return false;
                }
            } else {
                while (i < q.length() && j < p.length() && !isUpper(q.charAt(i)) && q.charAt(i) != cj) {
                    i++;
                }
                if (i == q.length()) return false;
                if (q.charAt(i) != p.charAt(j)) return false;
                i++;
                j++;
            }
        }
        if (j == p.length()) {
            while (++i < q.length()) {
                if (isUpper(q.charAt(i))) return false;
            }
        }
        return true;
    }

    private boolean isUpper(char c) {
        return c <= 'Z' && c >= 'A';
    }
}
