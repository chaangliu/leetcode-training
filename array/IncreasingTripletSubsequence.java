package array;

/**
 * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
 * Formally the function should:
 * Return true if there exists i, j, k
 * such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
 * Note: Your algorithm should run in O(n) time complexity and O(1) space complexity.
 * Example 1:
 * Input: [1,2,3,4,5]
 * Output: true
 * Example 2:
 * Input: [5,4,3,2,1]
 * Output: false
 * 20200307
 */
public class IncreasingTripletSubsequence {
    /**
     * 题意：在数组中寻找一个triplet subsequence，要求O(1) time O(1) space
     * 乍看跟LIS DP思路很像，但是因为只有3个数字所以可以轮替查找
     */
    public boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n < first) first = n;
            else if (n < second) second = n;
            else return true;
        }
        return false;
    }
}
