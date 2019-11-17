package math;

/**
 * Given a non-negative integer num, Return its encoding string.
 * The encoding is done by converting the integer to a string using a secret function that you should deduce from the following table:
 * ...图略
 * Example 1:
 * Input: num = 23
 * Output: "1000"
 * Example 2:
 * Input: num = 107
 * Output: "101100"
 * Constraints:
 * 0 <= num <= 10^9
 * 20191117
 */
public class EncodeNumber {
    /**
     * 题意：通过图片找规律，求f(n)的实现。还是lee的解法：
     * 观察1：f(n) has f((n - 1) / 2) as prefix
     */
    public String encode(int n) {
        return n > 0 ? encode((n - 1) / 2) + "10".charAt(n % 2) : "";
    }

    /**
     * 观察2：f(n) = binary(n + 1).substring(1)
     */
    public String encode_(int n) {
        return Integer.toBinaryString(n + 1).substring(1);
    }
}
