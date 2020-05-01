package math;

import java.util.Arrays;

/**
 * 给定一个非空整数数组，找到使所有数组元素相等所需的最小移动数，其中每次移动可将选定的一个元素加1或减1。 您可以假设数组的长度最多为10000。
 * 例如:
 * 输入:
 * [1,2,3]
 *
 * 输出:
 * 2
 *
 * 说明：
 * 只有两个动作是必要的（记得每一步仅可使其中一个元素加1或减1）：
 * [1,2,3]  =>  [2,2,3]  =>  [2,2,2]
 * 20200501
 */
public class MinimumMovestoEqualArrayElementsII {
    /**
     * 题意，一个数组中，每个数每次只能+1或者-1，求minimum moves to make them equal。
     * 解法：是个math题，答案就是，每个数距离中位数的差值之和。如果是偶数个数字，可以取中间两个数范围内的任意一个数
     * Mr.Hunter:
     * 对于偶数个数字，求中位点任选一个中间数的原因：
     * 分别标记两个点位mid1，mid2
     * 因为两个点是偶数个数的中间点，
     * 因此，mid1左边的数的个数=mid2右边的数的个数，记为n个
     * 将mid1左边的数全部变为mid1，将num2右边的数全部变为mid2，
     * 此时，如果选左边的数作为中位数，还需另外操作 (n+1)(mid2-mid1) 次
     * 如果选右边的数作为中位数，还需另外操作 (n+1)(mid2-mid1) 次
     */
    public int minMoves2(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;
        Arrays.sort(nums);
        // if ((n && 1) == 1)
        int mid = nums[n/2]; //如果是偶数个数字，可以取中间两个数范围内的任意一个数。
        int res = 0;
        for (int num : nums) {
            res += Math.abs(num - mid);
        }
        return res;
    }
}
