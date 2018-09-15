package array;

/**
 * Reverse digits of an integer.
 * <p>
 * Example1: x = 123, return 321
 * Example2: x = -123, return -321
 * <p>
 * Created by DrunkPiano on 2017/3/6.
 */

public class ReverseInteger {
    public int reverse(int x) {
        boolean flag = x > 0;
        if (!flag) {
            x = 0 - x;
        }
        int res = 0, mod = 0;
        while (x > 0) {
            mod = x % 10;
            if (res > (Integer.MAX_VALUE - mod )/ 10) {
                return 0;
            }
            res = res * 10 + mod;
            x = x / 10;
        }
        if (!flag) {
            return 0 - res;
        } else return res;
    }

    public static void main(String args[]) {
        ReverseInteger reverseInteger = new ReverseInteger();
        System.out.println(reverseInteger.reverse(-2147483412));
    }
}
