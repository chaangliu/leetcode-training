package twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given two arrays, write a function to compute their intersection.
 * Example 1:
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2,2]
 * Example 2:
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [4,9]
 * Note:
 * Each element in the result should appear as many times as it shows in both arrays.
 * The result can be in any order.
 * Follow up:
 * What if the given array is already sorted? How would you optimize your algorithm?
 * What if nums1's size is small compared to nums2's size? Which algorithm is better?
 * What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 * 2019/07/12
 */
public class IntersectionofTwoArraysII {
    /**
     * 这题跟上一题的区别是，需要把解集中重复的数字也返回。
     * 同样可以用第一题中的2 pointers方法；第一种里的set的话改成map，记录次数。
     * <p>
     * 另外这题的follow up提到有趣的问题，如果nums1或者nums2数据量太大，无法一次读入内存怎么办？
     * 有人给出方案，如果仅仅nums2太大，就把nums1存入map，然后把nums2里的chunk读入内存做对比。
     * 如果两个字符串都很大，那么分别对它们做external sort，然后每次从两个array读出1个数字，利用类似下面的2 pointers方案处理。
     * 还有别人提出了map reduce之类的方案。
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> res = new ArrayList<Integer>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        for (int i = 0, j = 0; i < nums1.length && j < nums2.length; ) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] == nums2[j]) {
                res.add(nums1[i]);
                i++;
                j++;
            } else {
                j++;
            }
        }
        int[] result = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            result[i] = res.get(i);
        }
        return result;
    }
}
