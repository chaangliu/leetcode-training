package binarysearch.templatei;

/**
 * Implement int sqrt(int x).
 * <p>
 * Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
 * <p>
 * Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.
 * <p>
 * Example 1:
 * <p>
 * Input: 4
 * Output: 2
 * Example 2:
 * <p>
 * Input: 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since
 * the decimal part is truncated, 2 is returned.
 * Created by DrunkPiano on 2017/3/7.
 */

public class Sqrtx {
    /**
     * binary search, 运用模板1
     */
    public int mySqrt(int x) {
        long low = 0;
        long high = x;// or high = x/2 +1 ;

        while (low <= high) {
            long mid = (low + high) / 2;
            if (mid * mid <= x && x < (mid + 1) * (mid + 1)) {
                return (int) mid;
            } else if (mid * mid > x) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return 0;
    }

    public static void main(String args[]) {
        System.out.println(Math.sqrt(10));
        System.out.println(Float.MAX_VALUE);
        System.out.println(Double.MAX_VALUE);
    }
}
