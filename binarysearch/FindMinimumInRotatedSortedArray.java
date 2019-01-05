package binarysearch;

/**
 * 153. Find Minimum in Rotated Sorted Array
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 * Find the minimum element.
 * You may assume no duplicate exists in the array.
 * Example 1:
 * Input: [3,4,5,1,2]
 * Output: 1
 * <p>
 * Example 2:
 * Input: [4,5,6,7,0,1,2]
 * Output: 0
 * <p>
 * 20190105
 */
public class FindMinimumInRotatedSortedArray {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[low] < nums[high])
                return nums[low];
            if (nums[mid] >= nums[low])//不能是nums[mid] > nums[low]，否则无法通过case: [3,1,2]
                low = mid + 1;
            else
                high = mid;//不能是mid - 1，否则无法通过case: [2,1]
        }
        return nums[low];
    }
}
