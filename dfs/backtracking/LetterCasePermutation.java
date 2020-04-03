package dfs.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.  Return a list of all possible strings we could create.
 * Examples:
 * Input: S = "a1b2"
 * Output: ["a1b2", "a1B2", "A1b2", "A1B2"]
 * <p>
 * Input: S = "3z4"
 * Output: ["3z4", "3Z4"]
 * <p>
 * Input: S = "12345"
 * Output: ["12345"]
 * Note:
 * <p>
 * S will be a string with length between 1 and 12.
 * S will consist only of letters or digits.
 * 20200403
 */
public class LetterCasePermutation {
    /**
     * 题意：给你一个String里面有字母和数字，你可以把任意字母变成大/小写，输出所有可能的String。
     * 解法：是个easy题，但是是个不错的backtracking题，我还调试了好一会儿。
     * 这题还可以用BFS。
     */
    public List<String> letterCasePermutation(String S) {
        List<String> res = new ArrayList<>();
        dfs(S, res, 0, "");
        return res;
    }

    private void dfs(String s, List<String> res, int i, String cell) {
        if (cell.length() == s.length()) {
            res.add(cell);
            return;
        }
        char c = s.charAt(i);
        dfs(s, res, i + 1, cell + c);
        if (c <= 'Z' && c >= 'A') {
            dfs(s, res, i + 1, cell + (char) (c + ('a' - 'A')));
        } else if (c <= 'z' && c >= 'a') {
            dfs(s, res, i + 1, cell + (char) (c - ('a' - 'A')));
        }
    }


    /**
     * BFS解法，只要BFS s.length() 这么多层就可以终止
     */
    public List<String> letterCasePermutation_(String S) {
        if (S == null) {
            return new LinkedList<>();
        }
        Queue<String> queue = new LinkedList<>();
        queue.offer(S);

        for (int i = 0; i < S.length(); i++) {
            if (Character.isDigit(S.charAt(i))) continue;
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                String cur = queue.poll();
                char[] chs = cur.toCharArray();

                chs[i] = Character.toUpperCase(chs[i]);
                queue.offer(String.valueOf(chs));

                chs[i] = Character.toLowerCase(chs[i]);
                queue.offer(String.valueOf(chs));
            }
        }

        return new LinkedList<>(queue);
    }
}
