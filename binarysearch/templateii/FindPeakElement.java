package binarysearch.templateii;

/**
 * A peak element is an element that is greater than its neighbors.
 * <p>
 * Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
 * <p>
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * <p>
 * You may imagine that nums[-1] = nums[n] = -∞.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index number 2.
 * Example 2:
 * <p>
 * Input: nums = [1,2,1,3,5,6,4]
 * Output: 1 or 5
 * Explanation: Your function can return either index number 1 where the peak element is 2,
 * or index number 5 where the peak element is 6.
 * 20190624 review
 */
public class FindPeakElement {

    /**
     * 20190624 review
     * 今天又看这道题，发现就是利用模板二的一道标准题；
     * 给定一个多峰的array，求其中任意一个峰；
     * 这题虽然不是一个sorted array，但是我们使用的搜索也不是通常的模板i；
     * 可以直接用应用模板ii，用单峰(PeakIndexInAMountainArray)的代码AC，因为总会在其中一个峰上停止搜索。
     * approach1. iteration
     */
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < nums[mid + 1])
                l = mid + 1;
            else
                r = mid;
        }
        return l;
    }

    /**
     * approach1的递归写法
     */
    public int findPeakElement__RECURSION1(int[] nums) {
        if (nums == null) return -1;
        return helper(nums, 0, nums.length - 1);
    }

    //[早期笔记]
    //因为不是找一个确定的值，所以终止条件是首位相遇(low == high)。
    //注意这里不能mid + 1、mid -1了，那样会漏解。此题我仍然有个疑问，为什么一定是low = mid + 1而不能是high = mid -1？似乎与int的除法取整方式有关(x)。应该是个固定套路吧(√)。
    private int helper(int[] nums, int low, int high) {
        if (low == high) return low;
        int mid = low + (high - low) / 2;
        return nums[mid] < nums[mid + 1] ?
                helper(nums, mid + 1, high) :
                helper(nums, low, mid);
    }
}
