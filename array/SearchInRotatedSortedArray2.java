package array;

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
 */

public class SearchInRotatedSortedArray2 {

    public static boolean search(int nums[], int target) {
        int len = nums.length;
        int left = 0, right = len - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) return true;
            if (nums[mid] > nums[left]) {
                if (target >= nums[left] && target < nums[mid])
                    right = mid - 1;
                else left = mid + 1;
            } else if (nums[mid] < nums[left]) {
                if (target > nums[mid] && target <= nums[right])
                    left = mid + 1;
                else right = mid - 1;
            } else {
                left++;
            }
        }
        return false;
    }

    public static void main(String args[]) {
        int a[] = {3, 1};
        System.out.println(search(a, 1));
    }
}
