package binarysearch.templatei;

/**
 * Total Accepted: 84001
 * Total Submissions: 255234
 * Difficulty: Medium
 * Contributors: Admin
 * Follow up for "Search in Rotated Sorted Array":
 * What if duplicates are allowed?
 * <p>
 * Would this affect the run-time complexity? How and why?
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * <p>
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * <p>
 * Write a function to determine if a given target is in the array.
 * <p>
 * The array may contain duplicates.
 * //20181027 review
 * //20200104 review
 */

public class SearchInRotatedSortedArrayII {
    /**
     * 题意：在一个rotated的sorted array里搜索一个数。
     * 还是按照SearchInRotatedSortedArray1的写法来做，只是要把nums[mid] == nums[lo]的情况单独拎出来
     */
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) return true;
            if (nums[mid] > nums[lo]) {
                if (target < nums[mid] && target >= nums[lo]) hi = mid - 1;
                else lo = mid + 1;
            } else if (nums[mid] < nums[lo]) {
                if (target > nums[mid] && target <= nums[hi]) lo = mid + 1;
                else hi = mid - 1;
            } else {
                // for (int i = 0; i < nums.length; i++) {
                //    if (nums[i] == target) return true;
                // }
                //注意这里不要像剑指offer上那样直接遍历，否则会TLE。应该lo ++ 或者 hi--,因为后面可能有机会再次二分
                lo++;
            }
        }
        return false;
    }
}
