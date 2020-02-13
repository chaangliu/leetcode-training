package math;

import java.util.HashMap;

/**
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
 * <p>
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 * <p>
 * Example 1:
 * <p>
 * Input: numerator = 1, denominator = 2
 * Output: "0.5"
 * Example 2:
 * <p>
 * Input: numerator = 2, denominator = 1
 * Output: "2"
 * Example 3:
 * <p>
 * Input: numerator = 2, denominator = 3
 * Output: "0.(6)"
 * 20200213
 */
public class FractiontoRecurringDecimal {
    /**
     * 题意：给你两个数字作为分子分母，求他们的无限循环小数表示
     * 例子：71/330 = 0.2(15)
     * 解法：这题我一开始不知道怎么去找无限循环的重复循环体，看了答案发现是用map除数以及当前res长度。
     **/
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";// edge case
        StringBuilder res = new StringBuilder();
        // "+" or "-"
        res.append(((numerator > 0) ^ (denominator > 0)) ? "-" : "");// false ^ true = false
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);

        // integral part
        res.append(num / den);
        num %= den;
        if (num == 0) {
            return res.toString();
        }
        res.append('.');
        HashMap<String, Integer> map = new HashMap<>();// current str -> current length，这儿最好用Long而不是string，速度会快几倍(5ms->1ms)
        map.put(num + "", res.length());
        while (num != 0) {
            num *= 10;
            res.append(num / den);// 710/330 = 2
            num %= den; // 710 % 330 = 50
            if (map.containsKey(num + "")) {
                int index = map.get(num + "");
                res.insert(index, '(');
                res.append(')');
                break;
            } else {
                map.put(num + "", res.length());
            }
        }
        return res.toString();
    }
}
