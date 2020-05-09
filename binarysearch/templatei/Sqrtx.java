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
     * 题意：给你一个非负整数x，求它的平方根，不保留小数。
     * 解法：因为不保留小数，所以可以统一求出upper_bound 然后 - 1
     * 202005review
     */
    public int mySqrt(int x) {
        long lo = 0, hi = (long) x + 1;// 注意这儿有两个要点，一个是不能直接x + 1，这样x = Integer.MAX_VALUE时仍然是会溢出的因为x仍然是int。 第二是要 + 1，否则对于x = 0, 1，会少计算一次。（其实所有二分搜索都可以hi+1吧
        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;
            if (mid * mid > x) hi = mid;
            else lo = mid + 1;
        }
        return (int) lo - 1;
    }

    /**
     * binary search, 运用模板1
     */
    public int mySqrt__(int x) {
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

    /**
     * 花花模板
     * 花花建议所有题目都用c++ stl的lower_bound/upper_bound那种左闭右开[l,r)的模板，也就是l = 0 r = len(但要考虑溢出问题)
     * 不要在中途返回，不会出现死循环
     * 用在这题的思路相当于是找upper_bound，第一个>=target的数，比如sqrt(9)，返回4；sqrt(10)也返回4，最后统一减去1.
     */
    public int mySqrt____(int x) {
        long low = 0 + 1; //trick + 1，让low最小 == 1
        long high = (long) x + 1; //要先把x转为long，否则仍会越界
        while (low < high) {
            long mid = low + (high - low) / 2;
            if (mid > x / mid) {//防止越界
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return (int) (low - 1);
    }

    public static void main(String args[]) {
        new Sqrtx().mySqrt(2147483647);
        System.out.println(Math.sqrt(10));
        System.out.println(Float.MAX_VALUE);
        System.out.println(Double.MAX_VALUE);
    }
}
