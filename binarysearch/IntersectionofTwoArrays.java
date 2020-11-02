package binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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
     * 题意：找出两个数组的交集，重复的只算一次。
     * 大致有三种方式，
     * 1. 利用两个set对比
     * 2. 分别sort，然后用2 pointers
     * 3. sort其中一个，然而对另一个的每个数做binary search（下方展示这种做法）
     */
    public int[] intersection(int[] A, int[] B) {
        if (A.length == 0 || B.length == 0) return new int[0];
        Arrays.sort(A);
        Arrays.sort(B);
        List<Integer> list = new ArrayList<>();
        Integer prev = null;
        for (int a : A) {
            if (prev != null && prev == a) continue;
            prev = a;
            int lo = 0, hi = B.length - 1; // n - 1, not n
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (B[mid] >= a) hi = mid; // lower_bound
                else lo = mid + 1;
            }
            if (B[lo] == a) list.add(a);
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

    /**
     * 一种用Stream的做法：
     * Set<Integer> set = Arrays.stream(nums2).boxed().collect(Collectors.toSet());
     * return Arrays.stream(nums1).distinct().filter(e-> set.contains(e)).toArray();
     */
}
