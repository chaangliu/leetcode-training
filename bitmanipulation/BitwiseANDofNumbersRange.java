package bitmanipulation;

/**
 * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.
 * Example 1:
 * Input: [5,7]
 * Output: 4
 * Example 2:
 * Input: [0,1]
 * Output: 0
 * 20200424
 */
public class BitwiseANDofNumbersRange {
    /**
     * 题意：求[m,n]范围内所有数字相与的和。
     * 解法：brute force会超时；
     * 正确思路是，【奇偶数的最低位相与一定是0】last bit of (odd number & even number) is 0. 如果两个数字不相同，说明它们区间内至少有一个奇数和一个偶数，那么这个区间内的BitAnd 最低位一定是0；
     * 那么我们把最低位去掉：把两个数都左移（除以二），继续计算。最终要找到相同的公共前缀。
     **/
    public int rangeBitwiseAnd(int m, int n) {
        int i = 0;
        while (m != n) {
            m >>= 1;
            n >>= 1;
            i++;
        }
        return m << i;
    }
}
