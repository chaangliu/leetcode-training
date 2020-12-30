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
     * 题意：找出两个有序数列的中位数，要求时间：O(log (m+n))
     * 解法: 折半查找，每次取两个数组的第k/2个数字，比较大小，然后缩小搜索范围，直到k == 1。
     * 这题我感觉递归比较容易理解，官方题解用了迭代。
     * 参考：https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/zhen-zheng-ologmnde-jie-fa-na-xie-shuo-gui-bing-pa/
     * 也可以参考：https://www.geeksforgeeks.org/k-th-element-two-sorted-arrays/
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m == 0)
            return n % 2 != 0 ? 1.0 * nums2[n / 2] : (nums2[n / 2] + nums2[n / 2 - 1]) / 2.0;
        if (n == 0)
            return m % 2 != 0 ? 1.0 * nums1[m / 2] : (nums1[m / 2] + nums1[m / 2 - 1]) / 2.0;
        int total = m + n;
        return (total & 1) == 1 ?
                find_kth(nums1, 0, nums2, 0, total / 2 + 1) :
                (find_kth(nums1, 0, nums2, 0, total / 2) + find_kth(nums1, 0, nums2, 0, total / 2 + 1)) / 2.0;
    }

    // 寻找a 和 b 数组中，第k小的数字
    double find_kth(int[] a, int a_begin, int[] b, int b_begin, int k) {
        if (a_begin >= a.length) return b[b_begin + k - 1];
        if (b_begin >= b.length) return a[a_begin + k - 1];
        if (k == 1) return Math.min(a[a_begin], b[b_begin]);
        int mid_a = Integer.MAX_VALUE;
        int mid_b = Integer.MAX_VALUE;
        // mid_a / mid_b 分别表示 a数组、b数组中第 k / 2 个数
        if (a_begin + k / 2 - 1 < a.length) mid_a = a[a_begin + k / 2 - 1];
        if (b_begin + k / 2 - 1 < b.length) mid_b = b[b_begin + k / 2 - 1];
        // 如果a数组的第 k / 2 个数小于b数组的第 k / 2 个数，表示总的第 k 个数位于a的第k / 2个数的后半段，或者是b的第 k / 2个数的前半段
        // 由于范围缩小了 k / 2 个数，此时总的第 k 个数实际上等于新的范围内的第 k - k / 2个数，依次递归
        return mid_a < mid_b ?
                find_kth(a, a_begin + k / 2, b, b_begin, k - k / 2) :
                find_kth(a, a_begin, b, b_begin + k / 2, k - k / 2);
    }

    /**
     * 解法2：binary search
     * 中位数的意思是，如果有n个有序的数，n是奇数，就取第n/2个；n是偶数，就取第n/2 - 1 和 n/2 个数的平均值；
     * 那么，这题是两个有序数列，如何让它等效于一个有序数列？可以把arr1/arr2的前半部分和后半部分分成一组(记为left，right)，假设用i,j来分割arr1和arr2，
     * 那么只要保证arr1[i-1] <= arr2[j] && arr2[j - 1] <= arr1[i], 就可以保证left部分总小于等于right部分；
     * 其次就是要保证left部分和right部分的数字数量一样或者相差1，这样接缝处就可以找到中位数。也就是i + j = (m + n) / 2
     * 操作起来就是相当于两个滑块，如果i需要往左，那j就需要往右；反之亦然。
     * *       left_part          |        right_part
     * * A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]
     * * B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]
     **/
    public double findMedianSortedArrays__(int[] nums1, int[] nums2) {
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
