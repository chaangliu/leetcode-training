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
     * 题意：找出两个有序数列的中位数，时间：O(log (m+n))
     * 解法：中位数的意思是，如果有n个有序的数，n是奇数，就取第n/2个；n是偶数，就取第n/2 - 1 和 n/2 个数的平均值；
     * 那么，这题是两个有序数列，如何让它等效于一个有序数列？可以把arr1/arr2的前半部分和后半部分分成一组(记为left，right)，假设用i,j来分割arr1和arr2，
     * 那么只要保证arr1[i-1] <= arr2[j] && arr2[j - 1] <= arr1[i], 就可以保证left部分总小于等于right部分；
     * 其次就是要保证left部分和right部分的数字数量一样或者相差1，这样接缝处就可以找到中位数。也就是i + j = (m + n) / 2
     * 操作起来就是相当于两个滑块，如果i需要往左，那j就需要往右；反之亦然。
     * *       left_part          |        right_part
     * * A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]
     * * B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]
     **/
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        if (m > n) return findMedianSortedArrays(nums2, nums1);//保证m <= n
        int i = 0, j = 0;
        int lo = 0, hi = m;
        double maxLeft = 0, minRight;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            i = mid;
            j = (m + n + 1) / 2 - i;//(m + n + 1)而不是(m + n)的这种划分是让总数是奇数的时候，左边比右边多一个
            if (i > 0 && j < n && nums1[i - 1] > nums2[j]) {
                hi = mid - 1;
            } else if (j > 0 && i < m && nums2[j - 1] > nums1[i]) {
                lo = mid + 1;
            } else {//已经满足条件，但是对于i = 0或者j = 0要特殊处理，这也是为什么把这种情况放在最后一个esle里
                if (i == 0) maxLeft = (double) nums2[j - 1];
                else if (j == 0) maxLeft = (double) nums1[i - 1];
                else {
                    maxLeft = (double) Math.max(nums1[i - 1], nums2[j - 1]);//左边界较大的那个
                }
                break;//important
            }
        }
        if (((m + n) & 1) == 1) {
            return maxLeft;
        }
        if (i == m) minRight = nums2[j];//[1,2][3,4]的case，m=2
        else if (j == n) minRight = nums1[i];
        else minRight = Math.min(nums1[i], nums2[j]);
        return (maxLeft + minRight) / 2.0;
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
