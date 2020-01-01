package dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 * Example:
 * Input: "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * Note:
 * Although the above answer is in lexicographical order, your answer could be in any order you want.
 * Created by DrunkPiano on 09/06/2017.
 * 20200101 --review
 */

public class LetterCombinationOfaPhoneNumber {

    /**
     * 题意：给你一串数字代表电话号码按键顺序，求所有可能的字母组合。
     * 解法：dfs。
     * 经典组合题，类似combinations，但又不同。要看到如何开启递归。
     * 类似题目1087 BraceExpansion
     */
    String[] s = new String[]{"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;
        dfs(res, "", 0, digits);
        return res;
    }

    private void dfs(List<String> res, String cell, int i, String digits) {
        if (i == digits.length()) {//已犯错误：这儿写成了i == cell.length()
            res.add(cell);
            return;
        }
        String pattern = s[digits.charAt(i) - '0' - 2];
        for (int j = 0; j < pattern.length(); j++) {
            dfs(res, cell + pattern.charAt(j), i + 1, digits);
        }
    }
}
