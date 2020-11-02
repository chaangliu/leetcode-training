package slidingwindow;

import java.util.Arrays;

/**
 * Given an array of integers nums and an integer target.
 * <p>
 * Return the number of non-empty subsequences of nums such that the sum of the minimum and maximum element on it is less or equal than target.
 * <p>
 * Since the answer may be too large, return it modulo 10^9 + 7.
 * Example 1:
 * <p>
 * Input: nums = [3,5,6,7], target = 9
 * Output: 4
 * Explanation: There are 4 subsequences that satisfy the condition.
 * [3] -> Min value + max value <= target (3 + 3 <= 9)
 * [3,5] -> (3 + 5 <= 9)
 * [3,5,6] -> (3 + 6 <= 9)
 * [3,6] -> (3 + 6 <= 9)
 * Example 2:
 * <p>
 * Input: nums = [3,3,6,8], target = 10
 * Output: 6
 * Explanation: There are 6 subsequences that satisfy the condition. (nums can have repeated numbers).
 * [3] , [3] , [3,3], [3,6] , [3,6] , [3,3,6]
 * Example 3:
 * <p>
 * Input: nums = [2,3,3,4,6,7], target = 12
 * Output: 61
 * Explanation: There are 63 non-empty subsequences, two of them don't satisfy the condition ([6,7], [7]).
 * Number of valid subsequences (63 - 2 = 61).
 * Example 4:
 * <p>
 * Input: nums = [5,2,4,1,7,6,8], target = 16
 * Output: 127
 * Explanation: All non-empty subset satisfy the condition (2^7 - 1) = 127
 * Constraints:
 * <p>
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^6
 * 1 <= target <= 10^6
 * 20200630
 */
public class NumberofSubsequencesThatSatisfytheGivenSumCondition {
    /**
     * 题意：给你一个Array和一个target，求子序列中最小和最大数之和<= target的子序列的数量。
     * 思路：这题我拿到就觉得是sliding window，感觉要维护最大最小值，有点无从下手。
     * 看了lee的答案发现有两个要点，
     * 第一，因为是求子序列的数量，所以可以sort。
     * 第二，用two pointers的形式，固定最小的数，去匹配最大的数，A[i]固定必选，那么A[i+1] ~ A[j]里面每个数字都可以被选或者不选，一共有2 ^ (j - i)种组合
     */
    public int numSubseq(int[] A, int target) {
        Arrays.sort(A);
        int n = A.length, l = 0, r = n - 1, res = 0, mod = (int) 1e9 + 7;
        int[] pows = new int[n]; // A[i]固定必选，那么A[i+1] ~ A[j]里面每个数字都可以被选或者不选，一共有2 ^ (j - i)种组合
        pows[0] = 1; // pows[len]中的len代表A[i]后面的后缀的长度；如果len = 0，表示i == j，那么只有一个子序列，就是[A[i]]
        for (int len = 1; len < n; len++)
            pows[len] = (2 * pows[len - 1]) % mod; // 技巧，不能用Math.pow(2, r - l)，因为pow返回的是double，范围比long都大。
        while (l <= r) {
            if (A[l] + A[r] > target) {
                r--;
            } else {
                res = (res + pows[r - l++]) % mod;
            }
        }
        return res;
    }

    /**
     * binary search写法，看着像O(nlogn)，但lo = i的，所以可能会快于O(nlogn)
     */
    public int numSubseq_(int[] A, int target) {
        Arrays.sort(A);
        int n = A.length, res = 0, mod = (int) 1e9 + 7;
        int[] pows = new int[n];
        pows[0] = 1;
        for (int len = 1; len < n; len++)
            pows[len] = (2 * pows[len - 1]) % mod;
        for (int i = 0; i < n; i++) {
            int lo = i, hi = n; // 这儿hi不能写成n-1，因为upper_bound的i是可以=n的，比如要找的target>A[n-1]的时候，就需要返回n，如果写成n-1，就只能返回n-1了
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (A[i] + A[mid] > target) { // A[mid] > target - A[i]，找到第一个严格大于target - A[i]的mid的下标
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }
            lo--;
            if (lo < i) return res;
            res = (res + pows[lo - i]) % mod;
        }
        return res;
    }
}
