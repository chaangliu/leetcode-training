package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a digit string, return all possible letter combinations that the number could represent.
 * <p>
 * <p>
 * <p>
 * Created by DrunkPiano on 2017/2/15.
 */

public class LetterCombinationsOfAPhoneNumber {

    /**
     * 题意：给你几个数字代表拨号键盘的按键顺序，求所有的字母组合。
     * 解法：DFS。
     * 时间：所有组合都visit一遍，O(3^m * 4^n)，m是3字母的数字个数，n是4字母的数字个数
     * 空间：O(m + n),间复杂度主要取决于哈希表以及回溯过程中的递归调用层数，哈希表的大小与输入无关，可以看成常数，递归调用层数最大为 m+n。
     */
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.length() == 0) return res;
        dfs(0, "", digits, res);
        return res;
    }

    private void dfs(int index, String cur, String digits, List<String> res) {
        if (cur.length() == digits.length()) {
            res.add(cur);
            return;
        }
        String options = phoneMap.get(digits.charAt(index));
        for (int i = 0; i < options.length(); i++) {
            dfs(index + 1, cur + options.charAt(i), digits, res);
        }
    }

    Map<Character, String> phoneMap = new HashMap<Character, String>() {{
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};

    /**
     * 迭代方法
     */
    public ArrayList<String> letterCombinations_ITERATIVE(String digits) {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> newResult = new ArrayList<>();
        if (digits.length() == 0) return result;
        result.add("");
        int digitLen = digits.length();
        for (int i = 0; i < digitLen; i++) {
            String letter = phoneMap.get(digits.charAt(i));
            newResult = new ArrayList<>();
            //1st :1
            for (int j = 0; j < result.size(); j++) {
                for (int k = 0; k < letter.length(); k++) {
                    newResult.add(result.get(j) + letter.charAt(k));
                }
            }
            result = newResult;
        }
        return result;
    }

    public static void main(String args[]) {
        LetterCombinationsOfAPhoneNumber letterCombinationsOfAPhoneNumber = new LetterCombinationsOfAPhoneNumber();
        System.out.println(letterCombinationsOfAPhoneNumber.letterCombinations("23"));
    }
}
