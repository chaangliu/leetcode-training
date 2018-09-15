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
 * <p>
 * 当左括号出现次数<n时，就可以放置新的左括号。当右括号出现次数小于左括号出现次数时，就可以放置新的右括号。
 * <p>
 * Created by DrunkPiano on 2017/2/18.
 */

public class GenerateParentheses {
        public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfs(0, 0, result, sb, n);
        return result;
    }

    public void dfs(int left, int right, List<String> result, StringBuilder sb, int n) {

        if (left >= n && right >= n) {
            result.add(sb.toString());
            return;
        }
        if (left < n) {
            sb.append("(");
            dfs(left + 1, right, result, sb, n);
            sb.deleteCharAt(sb.length() - 1);

        }
        if (right < left) {
            sb.append((")"));
            dfs(left, right + 1, result, sb, n);
            sb.deleteCharAt(sb.length() - 1);
        }

    }
//    List<String> result = new ArrayList<>();
//    int num;
//    public List<String> generateParenthesis(int n) {
//        String sb = "";
//        num = n;
//        dfs(0, 0, sb);
//        return result;
//    }

//    public void dfs(int left, int right, String sb) {
//
//        if (left >= num && right >= num) {
//            result.add(sb);
//            return;
//        }
//        if (left < num) {
//            dfs(left + 1, right, sb + "(");
//        }
//        if (right < left) {
//            dfs(left, right + 1, sb + ")");
//        }
//    }

    public static void main(String args[]) {
        GenerateParentheses generateParentheses = new GenerateParentheses();
        System.out.println(generateParentheses.generateParenthesis(4));
    }
}
