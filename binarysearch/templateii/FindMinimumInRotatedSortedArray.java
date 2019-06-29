package binarysearch.templateii;

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
    /**
     * 这题可以看出模板二不一定是连续两个数对比
     */
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            //[low, high]中间无cliff
            if (nums[low] < nums[high])
                return nums[low];
            //[low, high]中间有cliff
            //cliff在mid右边
            if (nums[mid] >= nums[low])//不能是nums[mid] > nums[low]，否则无法通过case: [3,1,2]
                low = mid + 1;
            else//cliff在mid左边
                high = mid;//模板二。不能是mid - 1，否则无法通过case: [2,1]
        }
        return nums[low];
    }

    /**
     * Solutions里的做法，模板一。
     */
    public int findMin_(int[] nums) {
        if (nums.length == 1) return nums[0];
        int left = 0, right = nums.length - 1;
        if (nums[right] > nums[left]) return nums[left];
        while (right >= left) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            }
            if (nums[mid - 1] > nums[mid]) {
                return nums[mid];
            }
            if (nums[mid] > nums[0]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
