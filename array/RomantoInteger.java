package array;

import java.util.HashMap;
import java.util.Map;

/**
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.
 * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.
 * Example 1:
 * Input: "III"
 * Output: 3
 * Example 2:
 * Input: "IV"
 * Output: 4
 * Example 3:
 * Input: "IX"
 * Output: 9
 * Example 4:
 * Input: "LVIII"
 * Output: 58
 * Explanation: L = 50, V= 5, III = 3.
 * Example 5:
 * Input: "MCMXCIV"
 * Output: 1994
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 * 20200101
 */

public class RomantoInteger {
    /**
     * 题意：罗马字转整数数字。
     * 解法：linear scan，如果A[i]<A[i+1]，res-=A[i].val，否则res+=A[i].val
     */
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        char[] chars = s.toCharArray();
        int result = 0;
        int i = 0;
        for (; i + 1 < chars.length; i++) {
            if (map.get(chars[i]) >= map.get(chars[i + 1])) {//>或者=都可以先加上
                result += map.get(chars[i]);
            } else {
                result -= map.get(chars[i]);//<，提前减去左边
            }
        }
        result += map.get(chars[i]);
        return result;
    }
}
