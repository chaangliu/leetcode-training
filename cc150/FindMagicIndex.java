package cc150;

/**
 * 魔术索引。 在数组A[0...n-1]中，有所谓的魔术索引，满足条件A[i] = i。给定一个有序整数数组，编写一种方法找出魔术索引，若有的话，在数组A中找出一个魔术索引，如果没有，则返回-1。若有多个魔术索引，返回索引值最小的一个。
 * 示例1:
 * 输入：nums = [0, 2, 3, 4, 5]
 * 输出：0
 * 说明: 0下标的元素为0
 * 示例2:
 * <p>
 * 输入：nums = [1, 1, 1]
 * 输出：1
 * 说明:
 * <p>
 * 此题为原书中的 Follow-up，即数组中可能包含重复元素的版本
 * 提示:
 * nums长度在[1, 1000000]之间
 */
public class FindMagicIndex {
    /**
     * 题意：一个有序数组，如果A[i]和i相同那么i就是魔术索引。求最左边的魔术索引，如果没有，返回-1。
     * 解法：显然是二分；这题如果只有1个符合条件的i，那直接lower_bound，找A[i] == i的那个就行；
     * 但这题是可能有多个符合条件的，可以用dfs先在左边搜索，搜到就停止；
     * 搜不到的话可能在中间或者右边。
     * [1, 1, 3, 3, 5] 减去index=> [1, 0, 1, 0, 1]
     **/
    public int findMagicIndex(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }

    /**
     * 返回[lo, hi]范围内最小的魔术索引(A[i] == i)
     **/
    private int dfs(int[] A, int lo, int hi) {
        if (lo > hi) return -1;
        int mid = lo + (hi - lo) / 2;
        int left = dfs(A, lo, mid - 1);
        if (left != -1) return left;
        if (A[mid] == mid) return mid;
        return dfs(A, mid + 1, hi);
    }
}
