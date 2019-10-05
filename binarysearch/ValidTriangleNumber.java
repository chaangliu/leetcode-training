package binarysearch;

import java.util.Arrays;

/**
 * Given an array consists of non-negative integers, your task is to count the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.
 * Example 1:
 * Input: [2,2,3,4]
 * Output: 3
 * Explanation:
 * Valid combinations are:
 * 2,3,4 (using the first 2)
 * 2,3,4 (using the second 2)
 * 2,2,3
 * Note:
 * The length of the given array won't exceed 1000.
 * The integers in the given array are in the range of [0, 1000].
 * 20191005
 */
public class ValidTriangleNumber {
    /**
     * 题意：找三个数作为边长构成三角形，问一共有多少种组合。允许重复。
     * 我的解法：binary search
     * 这题拿到之后发现相似题目是3 sum, 就想到先sort然后固定一个最左边的数字，剩下两个数字做2pointers的2sum。
     * 另外，我想到的既然是顺序数组，那么可以用upper_bound二分查找第一个大于两条短边之和的边长的index，那么对于每两条短边，就有r - l个组合。
     * 复杂度: O(n^2 * logn)
     */
    //2,2,2,2,2..3,4, p + l > r, upper_bound of (p + l - 1)
    public int triangleNumber(int[] nums) {
        int res = 0, len = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < len - 2; i++) {
            for (int l = i + 1; l < len - 1; l++) {
                int pivot = nums[i];
                int r = upperBound(pivot + nums[l] - 1, nums);
                if (r - 1 - l > 0)
                    res += r - 1 - l;
            }
        }
        return res;
    }

    int upperBound(int target, int[] nums) {
        int len = nums.length;
        int l = 0, r = len;//r = len而不是len - 1
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] > target) {
                r = mid;
            } else l = mid + 1;
        }
        return l;
    }

    /**
     * solution from discuss
     * two pointers, O(n^2)
     * 我的想法是每次固定了两个短边，但是这题可以模仿3 sum，固定最长边，然后两个短边做2 pointers的移动；
     * 当找到A[l] + A[r] > A[pivot]，就代表l<r时l每向右移动一次就产生一个满足条件的答案。
     */
    public static int triangleNumber_(int[] A) {
        Arrays.sort(A);
        int count = 0, n = A.length;
        for (int i = n - 1; i >= 2; i--) {
            int l = 0, r = i - 1;
            while (l < r) {
                if (A[l] + A[r] > A[i]) {
                    count += r - l;
                    r--;
                } else l++;
            }
        }
        return count;
    }
}
