package binarysearch.templateiii;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a sorted array, two integers k and x, find the k closest elements to x in the array. The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.
 * <p>
 * Example 1:
 * Input: [1,2,3,4,5], k=4, x=3
 * Output: [1,2,3,4]
 * Example 2:
 * Input: [1,2,3,4,5], k=4, x=-1
 * Output: [1,2,3,4]
 * Note:
 * The value k is positive and will always be smaller than the length of the sorted array.
 * Length of the given array is positive and will not exceed 104
 * Absolute value of elements in the array and x will not exceed 10^4
 */
public class FindKClosestElements {
    /**
     * 学习lee的做法
     */
    public List<Integer> findClosestElements(int[] A, int k, int x) {
        int left = 0, right = A.length - k;
        while (left < right) {
            int mid = (left + right) / 2;
            if (x - A[mid] > A[mid + k] - x)// A[mid] <--距离大-- x <--距离小-- A[mid + k]；means A[mid + 1] ~ A[mid + k] is better than A[mid] ~ A[mid + k - 1]
                left = mid + 1;
            else
                right = mid;//即便 x距离两边距离相等，也向左收缩 => smaller elements are always preferred（同样这里可以总结规律：≤或≥的那一侧不用mid - 1或mid + 1
        }
        return Arrays.stream(A, left, left + k).boxed().collect(Collectors.toList());
    }

    public static void main(String args[]) {
        int[] arr = {1, 2, 3, 4, 5};
        new FindKClosestElements().findClosestElements(arr, 4, 3);
    }
}
