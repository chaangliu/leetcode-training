package array;

/**
 * We are given an array A of positive integers, and two positive integers L and R (L <= R).
 * <p>
 * Return the number of (contiguous, non-empty) subarrays such that the value of the maximum array element in that subarray is at least L and at most R.
 * <p>
 * Example :
 * Input:
 * A = [2, 1, 4, 3]
 * L = 2
 * R = 3
 * Output: 3
 * Explanation: There are three subarrays that meet the requirements: [2], [2, 1], [3].
 * Note:
 * L, R  and A[i] will be an integer in the range [0, 10^9].
 * The length of A will be in the range of [1, 50000].
 * 20191129
 */
public class NumberofSubarrayswithBoundedMaximum {
    /**
     * 题意：给你一个数组，求子序列中最大的数在[L,R]范围内的subarray的数量
     * 这题我以为是那种类似subarraysWithKDistinct那样的滑动窗口解法，但是由于是求范围所以不一样。
     * 看了答案，依旧是用两个指针i,j记录新增的subarray的数量，有一定技巧。
     * For example, for input A = [0, 1, 2, -1], L = 2, R = 3:
     * (1)for 0, no valid subarray ends at 0;
     * (2)for 1, no valid subarray ends at 1;
     * (3)for 2, three valid subarrays end at 2, which are [0, 1, 2], [1, 2], [2];
     * (4)for -1, the number of valid subarrays is the same as 2, which are [0, 1, 2, -1], [1, 2, -1], [2, -1];
     */
    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        int j = 0, count = 0, res = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] >= L && A[i] <= R) {
                count = i - j + 1;
                res += count;//情况(3)，A[i]合法，那么更新count
            } else if (A[i] < L) {
                res += count;//情况(4)，A[i]不合法但是<L，那么count维持上一次的数量
            } else {
                j = i + 1;
                count = 0;
            }
        }
        return res;
    }

}
