package binarysearch.templateii;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 * Find the minimum element.
 * The array may contain duplicates.
 * Example 1:
 * Input: [1,3,5]
 * Output: 1
 * Example 2:
 * <p>
 * Input: [2,2,2,0,1]
 * Output: 0
 * 20190711
 */
public class FindMinimuminRotatedSortedArrayII {
    /**
     * 这题主要处理[10,1,10,10,10]这种case
     * 当nums[mid]和nums[high]相等时，无法判断cliff在哪一侧，所以直接从右边缩小范围
     * 跟Search in rotated sorted array ii 一样
     */
    public int findMin(int[] num) {
        int lo = 0;
        int hi = num.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (num[mid] > num[hi]) {//注意这里只能跟num[hi]对比而不能跟num[lo]对比，因为如果num[mid]>num[lo]，无法确定这个数组有本身有没有cliff
                lo = mid + 1;
            } else if (num[mid] < num[hi]) {
                hi = mid;
            } else { // when num[mid] and num[hi] are same
                hi--;// 这里对比的是hi, 不要lo++..
            }
        }
        return num[lo];
    }

    public static void main(String args[]) {
        int[] arr = {1, 0, 1, 1, 1};
        int[] arr2 = {1, 1, 1, 0, 1};
        new FindMinimuminRotatedSortedArrayII().findMin(arr);
    }
}
