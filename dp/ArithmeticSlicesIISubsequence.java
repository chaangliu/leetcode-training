package dp;

import java.util.HashMap;
import java.util.Map;

/**
 * A sequence of numbers is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.
 * <p>
 * For example, these are arithmetic sequences:
 * <p>
 * 1, 3, 5, 7, 9
 * 7, 7, 7, 7
 * 3, -1, -5, -9
 * The following sequence is not arithmetic.
 * <p>
 * 1, 1, 2, 5, 7
 * <p>
 * A zero-indexed array A consisting of N numbers is given. A subsequence slice of that array is any sequence of integers (P0, P1, ..., Pk) such that 0 ≤ P0 < P1 < ... < Pk < N.
 * <p>
 * A subsequence slice (P0, P1, ..., Pk) of array A is called arithmetic if the sequence A[P0], A[P1], ..., A[Pk-1], A[Pk] is arithmetic. In particular, this means that k ≥ 2.
 * <p>
 * The function should return the number of arithmetic subsequence slices in the array A.
 * <p>
 * The input contains N integers. Every integer is in the range of -231 and 231-1 and 0 ≤ N ≤ 1000. The output is guaranteed to be less than 231-1.
 * <p>
 * <p>
 * Example:
 * <p>
 * Input: [2, 4, 6, 8, 10]
 * <p>
 * Output: 7
 * <p>
 * Explanation:
 * All arithmetic subsequence slices are:
 * [2,4,6]
 * [4,6,8]
 * [6,8,10]
 * [2,4,6,8]
 * [4,6,8,10]
 * [2,4,6,8,10]
 * [2,6,10]
 * 20190918
 */
public class ArithmeticSlicesIISubsequence {
    /**
     * 这题需要借助弱等差数列来计算等差数列数量，leetcode的官方题解讲得很好。
     * <p>
     * 我们定义弱等差数列：
     * 弱等差数列 是至少有两个元素的子序列，任意两个连续元素的差相等。
     * <p>
     * 弱等差数列有两个十分有用的性质？
     * <p>
     * 对于任何 i, j (i != j)，A[i] 和 A[j] 总能组成一个弱等差数列。
     * <p>
     * 若在弱等差数列后添加一个元素且保持等差，则一定得到一个等差数列。
     * <p>
     * dp[i][j] : 以i结尾 公差为j的弱等差数列(长度2)的数量
     * 转移方程：对于所有 j < i，dp[i][A[i] - A[j]] += (dp[j][A[i] - A[j]] + 1)
     */
    public int numberOfArithmeticSlices(int[] A) {
        int n = A.length;
        long res = 0;
        Map<Integer, Integer>[] dp = new Map[n];//delta有可能很大很稀疏，所以用map来压缩二维数组
        for (int i = 0; i < n; i++) {
            dp[i] = new HashMap<>();
            for (int j = 0; j < i; j++) {
                long delta = (long) A[i] - (long) A[j];
                if (delta > Integer.MAX_VALUE || delta < Integer.MIN_VALUE) continue;
                int d = (int) delta;
                int added = dp[j].getOrDefault(d, 0);
                int origin = dp[i].getOrDefault(d, 0);
                dp[i].put(d, origin + added + 1);//dp[i][A[i] - A[j]] += (dp[j][A[i] - A[j]] + 1), '1'代表[i,j]这一对弱等差数列
                res += added;// 新增的等差数列长度一定大于2的等差数列
            }
        }
        return (int) res;
    }
}
