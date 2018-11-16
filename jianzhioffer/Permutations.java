package jianzhioffer;

import java.util.ArrayList;

/**
 * 题目描述
 * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
 * 输入描述:
 * 输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。
 */
public class Permutations {
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
            //这题的难点是处理重复字符，我能想到的是在递归的第一层，后一个char和前一个char相同就continue；
            //但条件应该是后一个char和前一个char相同并且前面的用过了。这样对于aaa亦行得通
            if (i > 0 && str.charAt(i) == str.charAt(i - 1) && !visited[i - 1]) continue;
            if (!visited[i]) {
                visited[i] = true;
                sb.append(str.charAt(i));
                permute(str, res, sb, visited);
                sb.deleteCharAt(sb.length() - 1);//这一句不要忘了
                visited[i] = false;
            }
        }
    }

    public static void main(String args[]) {
        new Permutations().Permutation("abc");
        int a;
    }
}
