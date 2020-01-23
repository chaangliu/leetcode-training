package array;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s. Return all the words vertically in the same order in which they appear in s.
 * Words are returned as a list of strings, complete with spaces when is necessary. (Trailing spaces are not allowed).
 * Each word would be put on only one column and that in one column there will be only one word.
 * Example 1:
 * Input: s = "HOW ARE YOU"
 * Output: ["HAY","ORO","WEU"]
 * Explanation: Each word is printed vertically.
 * "HAY"
 * "ORO"
 * "WEU"
 * Example 2:
 * <p>
 * Input: s = "TO BE OR NOT TO BE"
 * Output: ["TBONTB","OEROOE","   T"]
 * Explanation: Trailing spaces is not allowed.
 * "TBONTB"
 * "OEROOE"
 * "   T"
 * Example 3:
 * <p>
 * Input: s = "CONTEST IS COMING"
 * Output: ["CIC","OSO","N M","T I","E N","S G","T"]
 * Constraints:
 * <p>
 * 1 <= s.length <= 200
 * s contains only upper case English letters.
 * It's guaranteed that there is only one space between 2 words.
 * 20200119
 */
public class PrintWordsVertically {
    /**
     * 题意：给你一个含有空格的string，让你把单词分割出来，然后垂直打印字母。
     * 解法：直接按题意做就行。稍微难考虑的地方是空格，这题要求trim trailing spaces, 开头的空格不要trim。但是java的trim会把所有的空格都trim掉，写来写去还是最后处理一遍比较好。
     */
    public List<String> printVertically(String s) {
        String[] list = s.split(" ");
        List<String> res = new ArrayList<>();
        int maxLen = 0;
        for (String str : list) maxLen = Math.max(maxLen, str.length());
        for (int i = 0; i < maxLen; i++) {
            StringBuilder sb = new StringBuilder();
            for (String str : list) {
                sb.append(i < str.length() ? str.charAt(i) : ' ');
            }
            String sb1 = "";
            for (int j = sb.length() - 1; j >= 0; j--) {
                if (sb1.length() == 0 && sb.charAt(j) == ' ') continue;
                sb1 = sb.charAt(j) + sb1;
            }
            res.add(sb1);
        }
        return res;
    }
}
