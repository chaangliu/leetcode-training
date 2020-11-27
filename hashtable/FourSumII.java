package hashtable;

import java.util.HashMap;

/**
 * Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.
 * To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500. All integers are in the range of -228 to 228 - 1 and the result is guaranteed to be at most 231 - 1.
 * Example:
 * Input:
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 * Output:
 * 2
 * Explanation:
 * The two tuples are:
 * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 * 20191226
 */
public class FourSumII {
    /**
     * 题意：在ABCD四个数组里分别找一个数字, 加起来等于0, 问一共有多少种组合.
     * 这题跟前几题不一样在于，这题是在每个数组里分别找1个，另外只要求数量。
     * 做法是把前(N + 1) /2个数组可能凑成的sum组合记录下来，然后用另外一半数组去碰(相当于乘法)，可以减少一半复杂度。
     * 时间: O(n^2)
     */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        HashMap<Integer, Integer> map1 = new HashMap<>(), map2 = new HashMap<>();
        int res = 0;
        for (int i = 0; i < A.length; i++)
            for (int j = 0; j < B.length; j++) {
                map1.put(A[i] + B[j], map1.getOrDefault(A[i] + B[j], 0) + 1);
            }

        for (int i = 0; i < C.length; i++)
            for (int j = 0; j < D.length; j++) {
                res += map1.getOrDefault(-C[i] - D[j], 0);
            }
        return res;
    }
}
