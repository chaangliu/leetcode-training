package math;

/**
 * Given an array of integers arr. Return the number of sub-arrays with odd sum.
 * As the answer may grow large, the answer must be computed modulo 10^9 + 7.
 * Example 1:
 * Input: arr = [1,3,5]
 * Output: 4
 * Explanation: All sub-arrays are [[1],[1,3],[1,3,5],[3],[3,5],[5]]
 * All sub-arrays sum are [1,4,9,3,8,5].
 * Odd sums are [1,9,3,5] so the answer is 4.
 * Example 2:
 * Input: arr = [2,4,6]
 * Output: 0
 * Explanation: All sub-arrays are [[2],[2,4],[2,4,6],[4],[4,6],[6]]
 * All sub-arrays sum are [2,6,12,4,10,6].
 * All sub-arrays have even sum and the answer is 0.
 * 20200728
 */
public class NumberofSubarraysWithOddSum {
    /**
     * 题意：给你一个整数数组，求子数组（连续）的sum为奇数的数量。
     * 解法：前缀和，思路：
     * odd + even = odd
     * even + odd = odd
     * 意思就是，如果[ 0 , j ]这段的前缀和是偶数，假设前面有个位置 i < j ，[ 0 , i ]的前缀和是奇数，
     * 那么( i , j ]范围内的前缀和一定也是奇数；所以我们要加上(i, j] 这段范围的子数组；同时把[0, j]这段字数组加到偶数字数组里面去；反之亦然。
     **/
    public int numOfSubarrays(int[] arr) {
        int mod = (int) 1e9 + 7, sum = 0, even = 1, odd = 0, res = 0;
        for (int num : arr) {
            sum += num;
            if (sum % 2 == 1) {
                res = (res + even) % mod;
                odd++;
            } else {
                res = (res + odd) % mod;
                even++;
            }
        }
        return res;
    }
}
