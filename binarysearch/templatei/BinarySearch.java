package binarysearch.templatei;

/**
 * Given a sorted (in ascending order) integer array nums of n elements and a target value, write a function to search target in nums. If target exists, then return its index, otherwise return -1.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-1,0,3,5,9,12], target = 9
 * Output: 4
 * Explanation: 9 exists in nums and its index is 4
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [-1,0,3,5,9,12], target = 2
 * Output: -1
 * Explanation: 2 does not exist in nums so return -1
 * Note:
 * <p>
 * You may assume that all elements in nums are unique.
 * n will be in the range [1, 10000].
 * The value of each element in nums will be in the range [-9999, 9999].
 * 20190627
 */
public class BinarySearch {
    /**
     * 课本算法，找的是target，可以运用模板1，nums[mid]和target对比
     */
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] < target) l = mid + 1;
            else r = mid - 1;
        }
        return -1;
    }

    /**
     * 递归写法
     */
    public int search__(int[] nums, int target) {
        return helper(nums, 0, nums.length - 1, target);
    }

    private int helper(int[] nums, int l, int r, int target) {
        if (l > r) {
            return -1;//只有nums中真的没有target时才会从这里返回
        }
        int mid = l + (r - l) / 2;
        if (nums[mid] == target)
            return mid;
        else if (nums[mid] > target)
            return helper(nums, l, mid - 1, target);
        return helper(nums, mid + 1, r, target);
    }

    public static void main(String args[]) {
        int[] nums = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        new BinarySearch().search__(nums, 7);
    }
}
