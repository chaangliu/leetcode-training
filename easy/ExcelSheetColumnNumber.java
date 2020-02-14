package easy;

/**
 * Given a column title as appear in an Excel sheet, return its corresponding column number.
 * For example:
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 * Example 1:
 * Input: "A"
 * Output: 1
 * Example 2:
 * Input: "AB"
 * Output: 28
 * Example 3:
 * Input: "ZY"
 * Output: 701
 * 20200214
 */
public class ExcelSheetColumnNumber {
    /**
     * 题意：根据字母计算EXCEL的列数。
     * 解法：26进制数计算。
     */
    public int titleToNumber(String s) {
        int res = 0;
        for (char c : s.toCharArray()) {
            res *= 26;
            res += c - 'A' + 1;
        }
        return res;
    }
}
