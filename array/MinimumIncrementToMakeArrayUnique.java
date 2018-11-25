package array;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an array of integers A, a move consists of choosing any A[i], and incrementing it by 1.
 * <p>
 * Return the least number of moves to make every value in A unique.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,2]
 * Output: 1
 * Explanation:  After 1 move, the array could be [1, 2, 3].
 * Example 2:
 * <p>
 * Input: [3,2,1,2,1,7]
 * Output: 6
 * Explanation:  After 6 moves, the array could be [3, 4, 1, 2, 5, 7].
 * It can be shown with 5 or less moves that it is impossible for the array to have all unique values.
 */
public class MinimumIncrementToMakeArrayUnique {

    //brute force, TLE
    public int minIncrementForUnique(int[] A) {
        if (A == null || A.length == 0) return 0;
        int res = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < A.length; i++) {
            if (!set.contains(A[i])) {
                set.add(A[i]);
            } else {
                int tmp = A[i];
                while (set.contains(tmp)) {
                    tmp++;
                    res++;
                }
                set.add(tmp);
            }
        }
        return res;
    }

    public static void main(String args[]) {
//        int nums[] = {1, 2, 2};
        int nums[] = {3,2,1,2,1,7};
        new MinimumIncrementToMakeArrayUnique().minIncrementForUnique(nums);
    }
}
