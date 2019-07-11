package binarysearch;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given two arrays, write a function to compute their intersection.
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2]
 * Example 2:
 * <p>
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [9,4]
 * Note:
 * <p>
 * Each element in the result must be unique.
 * The result can be in any order.
 * 20190711
 */
public class IntersectionofTwoArrays {
    /**
     * 这题我没太读懂intersection的意思，以为是两个链表的intersection那种交点，其实这题求的是交集∩，题目要求结果中不含重复元素。
     * 那么大致有三种方式，
     * 1. 利用两个set对比
     * 2. 分别sort，然后用2 pointers
     * 3. sort其中一个，然而对另一个的每个数做binary search（下方展示这种做法）
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums2);
        for (Integer num : nums1) {
            if (binarySearch(nums2, num)) {
                set.add(num);
            }
        }
        int i = 0;
        int[] result = new int[set.size()];
        for (Integer num : set) {
            result[i++] = num;
        }
        return result;
    }

    public boolean binarySearch(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return false;
    }

    /**
     * 一种用Stream的做法：
     * Set<Integer> set = Arrays.stream(nums2).boxed().collect(Collectors.toSet());
     * return Arrays.stream(nums1).distinct().filter(e-> set.contains(e)).toArray();
     */
}
