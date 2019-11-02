package binarysearch;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * You may assume nums1 and nums2 cannot be both empty.
 * Example 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * The median is 2.0
 * Example 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * The median is (2 + 3)/2 = 2.5
 * Created by DrunkPiano on 2017/3/25.
 * 20191101
 */

public class MedianofTwoSortedArrays {
    /**
     * 题意：两个递增的数组，让你找中位数，要求时间是O(log(m+n))。
     * 解法：leetcode最高赞的解法是，BinarySearch；在A数组和B数组分别找i，j；然后把AB的前半部分和后半部分分成一组。具体做法是，二分寻找i，然后利用i + j = len/2这个公式找j。
     *       left_part          |        right_part
     * A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]
     * B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m > n) return findMedianSortedArrays(nums2, nums1);//保证m <= n
        int i = 0, j = 0, imin = 0, imax = m, half = (m + n + 1) / 2;
        double maxLeft = 0, minRight;
        while (imin <= imax) {
            i = (imin + imax) / 2;
            j = half - i;
            if (j > 0 && i < m && nums2[j - 1] > nums1[i]) {
                imin = i + 1;// i is too small, must increase it
            } else if (i > 0 && j < n && nums1[i - 1] > nums2[j]) {
                imax = i - 1;// i is too big
            } else {// i is perfect =>  B[j-1] <= A[i] and A[i-1] <= B[j], ( where j = (m + n + 1)/2 - i )
                if (i == 0) {
                    maxLeft = (double) nums2[j - 1];
                } else if (j == 0) {
                    maxLeft = (double) nums1[i - 1];
                } else {
                    maxLeft = (double) Math.max(nums1[i - 1], nums2[j - 1]);
                }
                break;
            }
        }
        if ((m + n) % 2 == 1) {
            return maxLeft;
        }
        if (i == m) {
            minRight = (double) nums2[j];
        } else if (j == n) {
            minRight = (double) nums1[i];
        } else {
            minRight = (double) Math.min(nums1[i], nums2[j]);
        }
        return (maxLeft + minRight) / 2;
    }

    /**
     * O(n)
     */
    public double findMedianSortedArrays__ON(int[] nums1, int[] nums2) {
        int totalLen = nums1.length + nums2.length;
        int[] sum = new int[totalLen];
        int i = nums1.length - 1;
        int j = nums2.length - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] <= nums2[j]) {
                sum[totalLen - 1] = nums2[j];
                totalLen--;
                j--;
            } else {
                sum[totalLen - 1] = nums1[i];
                totalLen--;
                i--;
            }
        }
        if (i >= 0) {
            for (int index = 0; index <= i; index++)
                sum[index] = nums1[index];
        }
        if (j >= 0) {
            for (int index = 0; index <= j; index++)
                sum[index] = nums2[index];
        }
        totalLen = nums1.length + nums2.length;
        if (totalLen % 2 == 0) {
            double res = (sum[totalLen / 2 - 1] + sum[totalLen / 2]) / 2.0;
            return res;
        } else {
            return sum[totalLen / 2];
        }
    }

    public static void main(String args[]) {
        MedianofTwoSortedArrays medianofTwoSortedArrays = new MedianofTwoSortedArrays();
        int nums1[] = {1, 2, 3, 4, 5, 6};
        int nums2[] = {7, 8};
        double res = medianofTwoSortedArrays.findMedianSortedArrays(nums1, nums2);
        System.out.println(res);
    }
}
