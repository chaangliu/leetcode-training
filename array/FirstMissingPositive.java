package array;

import java.util.HashSet;

/**
 * Given an unsorted integer array, find the smallest missing positive integer.
 * Example 1:
 * Input: [1,2,0]
 * Output: 3
 * Example 2:
 * Input: [3,4,-1,1]
 * Output: 2
 * Example 3:
 * Input: [7,8,9,11,12]
 * Output: 1
 * Note:
 * <p>
 * Your algorithm should run in O(n) time and uses constant extra space.
 * 20191221
 * 20200107 --review
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
     * 题意：找出第一个丢失的正整数
     * 思路：最常规的想法就是用一个hashset来做，但是要求只能用contant space所以不行；
     * 然后我想到了swap，但是不知道怎么处理越界。
     * 看答案发现越界的数不用处理，如果检测到一个正整数没有越界，就把它换到自己该有的位置即可。
     * 注意不能检查当前坑的数字是否正确，而是要检查[A[i] - 1的那个坑是否正确，否则[1,1]这样的情况会死循环。
     **/
    public int firstMissingPositive(int[] A) {
        for (int i = 0; i < A.length; i++) {
            while (A[i] > 0 && A[i] <= A.length && A[A[i] - 1] != A[i]) {// 重点：这里是while，不是if。考虑[-1,4,3,1]，当你把4和1交换，1并没有在正确位置上，这时候应该继续换，否则1将永远无法到index=0上
                int tmp = A[A[i] - 1];
                A[A[i] - 1] = A[i];
                A[i] = tmp;
            }
        }
        for (int i = 0; i < A.length; i++) {
            if (i + 1 != A[i]) return i + 1;
        }
        return A.length + 1;
    }
}
