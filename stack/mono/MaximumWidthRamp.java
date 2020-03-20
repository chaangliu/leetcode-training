package stack.mono;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array A of integers, a ramp is a tuple (i, j) for which i < j and A[i] <= A[j].  The width of such a ramp is j - i.
 * Find the maximum width of a ramp in A.  If one doesn't exist, return 0.
 * Example 1:
 * Input: [6,0,8,2,1,5]
 * Output: 4
 * Explanation:
 * The maximum width ramp is achieved at (i, j) = (1, 5): A[1] = 0 and A[5] = 5.
 * Example 2:
 * Input: [9,8,1,0,1,9,4,0,4,1]
 * Output: 7
 * Explanation:
 * The maximum width ramp is achieved at (i, j) = (2, 9): A[2] = 1 and A[9] = 1.
 * Note:
 * 2 <= A.length <= 50000
 * 0 <= A[i] <= 50000
 * 20200320
 */
public class MaximumWidthRamp {
    /**
     * 题意：给你一个无序数组，找出最大的斜坡长度，也就是A[i]<=A[j]，i和j的距离最大值。
     * 解法：lee的单调栈写法；递减栈，遇到比stack.top大的，都去stack中binary search第一个比它小的数。
     * 时间：O(N log n)
     */
    public int maxWidthRamp(int[] A) {
        List<Integer> stack = new ArrayList<>();
        int res = 0, n = A.length;
        for (int i = 0; i < n; ++i) {
            if (stack.size() == 0 || A[i] < A[stack.get(stack.size() - 1)]) {
                stack.add(i);
            } else {
                int left = 0, right = stack.size() - 1, mid = 0;
                while (left < right) {
                    mid = (left + right) / 2;
                    if (A[stack.get(mid)] > A[i]) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                res = Math.max(res, i - stack.get(left));
            }
        }
        return res;
    }
}
