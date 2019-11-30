package array;

/**
 * Given an array A, partition it into two (contiguous) subarrays left and right so that:
 * Every element in left is less than or equal to every element in right.
 * left and right are non-empty.
 * left has the smallest possible size.
 * Return the length of left after such a partitioning.  It is guaranteed that such a partitioning exists.
 * Example 1:
 * Input: [5,0,3,8,6]
 * Output: 3
 * Explanation: left = [5,0,3], right = [8,6]
 * Example 2:
 * Input: [1,1,1,0,6,12]
 * Output: 4
 * Explanation: left = [1,1,1,0], right = [6,12]
 * Note:
 * 2 <= A.length <= 30000
 * 0 <= A[i] <= 10^6
 * It is guaranteed there is at least one way to partition A as described.
 * 20191130
 */
public class PartitionArrayintoDisjointIntervals {
    /**
     * 题意：给你一个int array，让你找出一个最小的pivot，pivot左边的数字都小于等于右边
     * 解法：观察了一下就发现只要从左往右维护一个最大值，再从右往左维护一个最小值即可。
     * 我的代码：
     */
    public int partitionDisjoint(int[] nums) {
        int len = nums.length, max = 0, min = 1000000;
        int[] mins = new int[len], maxes = new int[len];
        for (int i = len - 1; i >= 0; i--) {
            min = Math.min(min, nums[i]);
            mins[i] = min;
        }
        for (int i = 0; i < len; i++) {
            max = Math.max(max, nums[i]);
            maxes[i] = max;
        }
        for (int i = 0; i < len - 1; i++) {
            if (maxes[i] <= mins[i + 1])
                return i + 1;
        }
        return -1;
    }

    /**
     * 讨论区的解法，One Pass，O(1)
     * 用一个localMax变量记录[0, partitionIdx]的最大值；那如果在右边找到了a[i]比localMax还小，就要重新计算localMax
     */
    public int partitionDisjoint_(int[] a) {
        int localMax = a[0], partitionIdx = 0, max = localMax;
        for (int i = 1; i < a.length; i++)
            if (localMax > a[i]) {
                localMax = max;
                partitionIdx = i;
            } else
                max = Math.max(max, a[i]);
        return partitionIdx + 1;
    }
}
