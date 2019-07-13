package greedy;

/**
 * Given a non-decreasing array of positive integers nums and an integer K, find out if this array can be divided into one or more disjoint increasing subsequences of length at least K.
 * Example 1:
 * Input: nums = [1,2,2,3,3,4,4], K = 3
 * Output: true
 * Explanation:
 * The array can be divided into the two subsequences [1,2,3,4] and [2,3,4] with lengths at least 3 each.
 * Example 2:
 * <p>
 * Input: nums = [5,6,6,7,8], K = 3
 * Output: false
 * Explanation:
 * There is no way to divide the array using the conditions required.
 * Note:
 * 1 <= nums.length <= 10^5
 * 1 <= K <= nums.length
 * 1 <= nums[i] <= 10^5
 * 20190714
 * 相关题目:659,767
 */
public class DivideArrayIntoIncreasingSequences {
    /**
     * 这题我一开始理解为659题那样的公差1的子序列了。
     * 其实是个简单的brain teaser。
     * 所以对于leetcode上的easy tag，一定非常easy；对于hard，有时候可能是easy+一点脑筋急转弯。
     * 另外 lee提供了一种分割的方法，对于最大重复数量groups，可以把A[i]分配到i % groups那一组。感觉很简单但是想不到啊。。
     */
    public boolean canDivideIntoSubsequences(int[] A, int K) {
        int cur = 1, groups = 1, n = A.length;
        for (int i = 1; i < n; ++i) {
            cur = A[i - 1] < A[i] ? 1 : cur + 1;
            groups = Math.max(groups, cur);
        }
        return n >= K * groups;
    }
}
