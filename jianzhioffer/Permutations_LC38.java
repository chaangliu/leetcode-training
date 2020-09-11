package jianzhioffer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 题目描述
 * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
 * 输入描述:
 * 输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。
 */
public class Permutations_LC38 {
    /**
     * 题意：给你一个String，求它的全排列，顺序无所谓，不能有重复。
     * 解法：backtrack；这题我大意了，没注意到不能有重复这个条件(比如aab)；其实这题是LC的permutations ii，需要去重；但是用HashSet去重的话很慢。
     * 参考CombinationSum2的处理方法。
     */
    public ArrayList<String> Permutation(String str) {
        ArrayList<String> res = new ArrayList<>();
        if (str == null || str.length() == 0) return res;
        permute(str, res, new StringBuilder(str.length()), new boolean[str.length()]);
        return res;
    }

    private void permute(String str, ArrayList<String> res, StringBuilder sb, boolean[] visited) {
        if (sb.length() == str.length()) {
            res.add(sb.toString());
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            // 这题的难点是处理重复字符，我能想到的是在递归的第一层，后一个char和前一个char相同就continue；
            // 但条件应该是后一个char和前一个char相同并且前面的【没用过】。这样对于aaa亦行得通
            if (i > 0 && str.charAt(i) == str.charAt(i - 1) && !visited[i - 1]) continue;
            if (!visited[i]) {
                visited[i] = true;
                sb.append(str.charAt(i));
                permute(str, res, sb, visited);
                sb.deleteCharAt(sb.length() - 1);
                visited[i] = false;
            }
        }
    }

    /**
     * 以下做法WA了；不知何故过不了一个很长的case
     */
    public String[] permutation(String s) {
        List<String> res = new ArrayList<>();
        dfs(res, "", s, new HashSet<>());
        String[] result = new String[res.size()];
        int idx = 0;
        for (String it : res) result[idx++] = it;
        return result;
    }

    private void dfs(List<String> res, String cur, String s, HashSet<Integer> visited) {
        if (cur.length() == s.length()) {
            res.add(cur);
            return;
        }
        char removed = '?';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (visited.contains(i) || removed == c) continue;
            visited.add(i);
            dfs(res, cur + c, s, visited);
            removed = c;
            visited.remove(i);
        }
    }
}
