package math;

/**
 * Given an integer, write a function to determine if it is a power of two.
 * <p>
 * Example 1:
 * <p>
 * Input: 1
 * Output: true
 * Explanation: 20 = 1
 * Example 2:
 * <p>
 * Input: 16
 * Output: true
 * Explanation: 24 = 16
 * Example 3:
 * <p>
 * Input: 218
 * Output: false
 * 20191009
 */
public class PowerOfTwo {
    /**
     * power of 2 means only 1 bit is 1.
     * n & n - 1可以把最后一个1去掉
     **/
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        return (n & n - 1) == 0;
    }
}
