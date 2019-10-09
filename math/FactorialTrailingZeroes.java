package math;

/**
 * Given an integer n, return the number of trailing zeroes in n!.
 * Example 1:
 * Input: 3
 * Output: 0
 * Explanation: 3! = 6, no trailing zero.
 * Example 2:
 * <p>
 * Input: 5
 * Output: 1
 * Explanation: 5! = 120, one trailing zero.
 * Note: Your solution should be in logarithmic time complexity.
 * 20191009
 */
public class FactorialTrailingZeroes {
    class Solution {
        /**
         * 题意:求n!最后有几个零。
         * 一道数学题，思路是，0只能由5 * 2得到；另外2总是充足的，2的因子数量肯定>5的因子数量
         * 所以只要数n!有几个5的factor(因子)
         * 注意，25、50这样的数可以贡献2个5，所以要除以5之后再做一次累加，直到把5分解完毕为止
         * ..n/125 + n/25 + n/5 + 0
         **/
        public int trailingZeroes(int n) {
            return n == 0 ? 0 : n / 5 + trailingZeroes(n / 5);
        }
    }
}
