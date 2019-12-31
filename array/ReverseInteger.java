package array;

/**
 * Reverse digits of an integer.
 * <p>
 * Example1: x = 123, return 321
 * Example2: x = -123, return -321
 * <p>
 * Created by DrunkPiano on 2017/3/6.
 * 20191231 review
 */

public class ReverseInteger {
    /**
     * 题意：把整数翻转，符号不变。
     * 解法，这题用string比较容易，用math的话，要格外注意溢出，另外一个注意，java中负数的mod，-12 % 10 = -2而不是2。
     * string解法：
     */
    public int reverse(int x) {
        StringBuilder s = new StringBuilder(x + "");
        int sign = 1;
        if (s.charAt(0) == '-') sign = -1;
        s.reverse();
        int i = 0;
        while (s.charAt(i) == 0) s.deleteCharAt(0);
        int res = Integer.parseInt(s.toString());
        return sign * res;
    }

    /**
     * Math法
     */
    public int reverse_math(int x) {
        int result = 0;
        while (x != 0) {
            int tail = x % 10;
            int newResult = result * 10 + tail;
            if ((newResult - tail) / 10 != result) {
                return 0;
            }
            result = newResult;
            x = x / 10;
        }
        return result;
    }

    public static void main(String args[]) {
        ReverseInteger reverseInteger = new ReverseInteger();
        System.out.println(reverseInteger.reverse(-2147483412));
    }
}
