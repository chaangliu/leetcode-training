package binarysearch.templateii;

/**
 * Let's call an array A a mountain if the following properties hold:
 * A.length >= 3
 * There exists some 0 < i < A.length - 1 such that A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1]
 * Given an array that is definitely a mountain, return any i such that A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1].
 * Example 1:
 * Input: [0,1,0]
 * Output: 1
 * Example 2:
 * Input: [0,2,1,0]
 * Output: 1
 * Note:
 * 3 <= A.length <= 10000
 * 0 <= A[i] <= 10^6
 * A is a mountain, as defined above.
 * 20190624
 */

/**
 * 题目：在一个山峰数组里找到峰值的index。
 * 因为需要访问当前元素的immediate right neighbor，所以用模板ii。
 * 模板ii的标准情况是r = A.length, 不过在这题是A.length - 1.
 * <p>
 * 相似题目：PeakIndexInAMountainArray
 */
public class PeakIndexInAMountainArray {
    public int peakIndexInMountainArray(int[] A) {
        int l = 0, r = A.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (A[mid] < A[mid + 1]) {
                l = mid + 1;//peak在mid右边
            } else {
                r = mid;
            }
        }
        return l;
    }
}
