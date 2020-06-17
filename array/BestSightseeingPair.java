package array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array A of positive integers, A[i] represents the value of the i-th sightseeing spot, and two sightseeing spots i and j have distance j - i between them.
 * The score of a pair (i < j) of sightseeing spots is (A[i] + A[j] + i - j) : the sum of the values of the sightseeing spots, minus the distance between them.
 * Return the maximum score of a pair of sightseeing spots.
 * Example 1:
 * Input: [8,1,5,2,6]
 * Output: 11
 * Explanation: i = 0, j = 2, A[i] + A[j] + i - j = 8 + 5 + 0 - 2 = 11
 */
public class BestSightseeingPair {
    /**
     * 题意：给一个array，让你求出最大的这样的值：A[i] + A[j] + i - j
     * 解法：我看了hint说可以one pass，就用了个map存储了一下A[i] + i的值和对应的i；这样我就能找出A[i] + i最大值对应的index。
     * 但其实不需要记录每个index，观察A[i] + A[j] + i - j => (A[i] + i) + A[j] - j；A[i] + i就在我们要的结果里面
     */
    public int maxScoreSightseeingPair(int[] A) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0, res = Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            if (i > 0) {
                int index = map.get(max);
                res = Math.max(res, A[i] + A[index] - (i - index));
            }
            map.put(A[i] + i, i);
            max = Math.max(max, A[i] + i);
        }
        return res;
    }

    public int maxScoreSightseeingPair_O1(int[] A) {
        int ans = 0, mx = A[0] + 0;
        for (int j = 1; j < A.length; ++j) {
            ans = Math.max(ans, mx + A[j] - j);
            // 边遍历边维护
            mx = Math.max(mx, A[j] + j);
        }
        return ans;
    }
}
