package dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DrunkPiano on 09/06/2017.
 */

public class LetterCombinationOfaPhoneNumber {
    private static final String[] KEYS = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    /**
     * 经典组合题，类似combinations，但又不同。要看到如何开启递归。
     * 类似题目1087 BraceExpansion
     */
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        dfs(res, digits, 0, "");
        return res;
    }

    private void dfs(List<String> res, String digits, int index, String item) {
        if (index == digits.length()) {
            res.add(item);
            return;
        }

        String s = KEYS[digits.charAt(index) - '0'];
        for (int i = 0; i < s.length(); i++) {
            dfs(res, digits, index + 1, item + s.charAt(i));
        }
    }

    public static void main(String args[]) {
        LetterCombinationOfaPhoneNumber lett = new LetterCombinationOfaPhoneNumber();
        System.out.println(lett.letterCombinations("234"));
    }
}
