package array;

import java.util.HashSet;

/**
 * Given an unsorted integer array, find the smallest missing positive integer.
 Example 1:
 Input: [1,2,0]
 Output: 3
 Example 2:
 Input: [3,4,-1,1]
 Output: 2
 Example 3:
 Input: [7,8,9,11,12]
 Output: 1
 Note:

 Your algorithm should run in O(n) time and uses constant extra space.
 20191221
 */
public class FirstMissingPositive {
    /**
     * 题意：给你一个int数组，让你找出最小的缺失的(missing)正整数。要求用O(1) space O(n)time。
     * 解法，我先写了一个O(n) space的写法，能过，但是慢而且空间O(n)。
     * 这题好的解法是swap。
     */
    public int firstMissingPositive_ON(int[] nums) {
        int max = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int i : nums) {
            if (i > 0) set.add(i);
            max = Math.max(max, i);
        }
        for (int i = 1; i <= max; i++) {
            if (!set.contains(i)) return i;
        }
        return max + 1;
    }

    /**
     * Swap解法，检查当前正整数对应的那个坑的数字是否正确，如果不正确，就把自己换过去。
     * 注意不能检查当前坑的数字是否正确，而是要检查[A[i] - 1的那个坑是否正确，否则[1,1]这样的情况会死循环。
     */
    public int firstMissingPositive(int[] A) {
        int n = A.length;
        for (int i = 0; i < n; i++) {
            // 要点：A[A[i] - 1] != A[i]不能写成A[i] != i + 1, 否则case[1,1]会死循环
            while (A[i] > 0 && A[i] <= n && A[A[i] - 1] != A[i]) {
                int tmp = A[A[i] - 1];//swap
                A[A[i] - 1] = A[i];
                A[i] = tmp;
            }
        }
        for (int i = 0; i < n; i++) {
            if (A[i] != i + 1) return i + 1;
        }
        return n + 1;//[],[1]
    }
}
