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
     * 注意这题不能和I一样判断A[low]和A[mid]的关系，无法处理递增的情况，比如[1,2,3]。
     * <p>
     * 思考： 是否可以用 numbers[m] 和 numbers[i] 比较做代替？
     * 解析： 不可以。因为做比较的目的是判断 m 在哪个排序数组中。但在 numbers[m] > numbers[i]情况下，无法判断 m 在哪个排序数组中。本质是因为 jj 初始值肯定在右排序数组中； ii 初始值无法确定在哪个排序数组中。
     * 示例： 当 i = 0, j = 4, m = 2i=0,j=4,m=2 时，有 numbers[m] > numbers[i] ，以下两示例得出不同结果。
     * numbers = [1, 2, 3, 4 ,5]numbers=[1,2,3,4,5] 旋转点 x = 0x=0 ： m 在右排序数组（此示例只有右排序数组）；
     * numbers = [3, 4, 5, 1 ,2]numbers=[3,4,5,1,2] 旋转点 x = 3x=3 ： m 在左排序数组。
     * <p>
     * 链接：https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/solution/mian-shi-ti-11-xuan-zhuan-shu-zu-de-zui-xiao-shu-3/
     */
    public int findMin(int[] num) {
        int lo = 0;
        int hi = num.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            // 这题的关键就是要跟右边界比，因为如果A[mid] > A[hi]，那我们一定可以抛弃右边；如果 A[mid] < A[hi]，一定可以抛弃左边；
            // 而A[mid] > A[lo] 不能判断抛弃哪边。https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/solution/mian-shi-ti-11-xuan-zhuan-shu-zu-de-zui-xiao-shu-3/
            if (num[mid] > num[hi]) {
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
