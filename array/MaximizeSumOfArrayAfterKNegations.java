package array;

import java.util.Arrays;

/**
 * 1005. Maximize Sum Of Array After K Negations My SubmissionsBack to Contest
 * Given an array A of integers, we must modify the array in the following way: we choose an i and replace A[i] with -A[i], and we repeat this process K times in total.  (We may choose the same index i multiple times.)
 * Return the largest possible sum of the array after modifying it in this way.
 * Example 1:
 * <p>
 * Input: A = [4,2,3], K = 1
 * Output: 5
 * Explanation: Choose indices (1,) and A becomes [4,-2,3].
 * Example 2:
 * <p>
 * Input: A = [3,-1,0,2], K = 3
 * Output: 6
 * Explanation: Choose indices (1, 2, 2) and A becomes [3,1,0,2].
 * Example 3:
 * <p>
 * Input: A = [2,-3,-1,5,-4], K = 2
 * Output: 13
 * Explanation: Choose indices (1, 4) and A becomes [2,3,-1,5,4].
 * Note:
 * <p>
 * 1 <= A.length <= 10000
 * 1 <= K <= 10000
 * -100 <= A[i] <= 100
 *
 * 20190310contest
 */
public class MaximizeSumOfArrayAfterKNegations {
    /**
     * 想到一个办法，效率可能比较低；每次反转sort后排在最前面的那个数字，然后再flip，repeat k times
     * (15min)
     */
    public int largestSumAfterKNegations(int[] A, int K) {
        if (A == null || A.length == 0 || K <= 0) return 0;
        for (int i = 0; i < K; i++) {
            Arrays.sort(A);
            A[0] = -A[0];
        }
        int sum = 0;
        for (Integer i : A) sum += i;
        return sum;
    }
}
