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
    public int minIncrementForUniqueTLE(int[] A) {
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

    //solutions里的解法，记录先取走，遇到合适的位置就填充
    public int minIncrementForUnique(int[] A) {
        if (A == null || A.length == 0) return 0;
        //题目说数字不大于100000
        int[] map = new int[100000];
        //计算每个数字出现次数
        for (int i = 0; i < A.length; i++) {
            map[A[i]]++;
        }
        int ans = 0, taken = 0;
        for (int i = 0; i < 100000; i++) {
            if (map[i] > 1) {
                //拿走n-1个重复的数字
                taken += map[i] - 1;
                //提前减去n个对应的数，比如1,1,1,1,3,5，就提前减去3个1
                ans -= i * (map[i] - 1);
            } else if (map[i] == 0 && taken > 0) {
                taken--;
                ans += i;
            }
        }
        return ans;
    }

    public static void main(String args[]) {
        int nums[] = {1, 2, 2};
//        int nums[] = {3, 2, 1, 2, 1, 7};
        new MinimumIncrementToMakeArrayUnique().minIncrementForUnique(nums);
    }
}
