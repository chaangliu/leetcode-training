package binarysearch;

/**
 * Given an integer array arr and a target value target, return the integer value such that when we change all the integers larger than value in the given array to be equal to value, the sum of the array gets as close as possible (in absolute difference) to target.
 * In case of a tie, return the minimum such integer.
 * Notice that the answer is not neccesarilly a number from arr.
 * Example 1:
 * Input: arr = [4,9,3], target = 10
 * Output: 3
 * Explanation: When using 3 arr converts to [3, 3, 3] which sums 9 and that's the optimal answer.
 * Example 2:
 * Input: arr = [2,3,5], target = 10
 * Output: 5
 * Example 3:
 * Input: arr = [60864,25176,27249,21296,20204], target = 56803
 * Output: 11361
 * Constraints:
 * 1 <= arr.length <= 10^4
 * 1 <= arr[i], target <= 10^5
 * 20191229
 */
public class SumofMutatedArrayClosesttoTarget {
    /**
     * 题意：给你一个数组和一个target，让你把所有大于某个数val的数字换成val，最后再加起来能最接近target，问这个val最小是多少。
     * 这题一眼就看出来是二分搜索，但是由于要求一个最小的val，所以细节有些多，WA了很多次。
     * 注意，这个代码虽然能过，但是没有处理[1,2,3] 7这种情况，leetcode的test case太弱了
     */
    public int findBestValue(int[] arr, int target) {
        int lo = 0, hi = (int) 1e5, dist = Integer.MAX_VALUE, res = hi;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int sum = 0;
            for (int num : arr) {
                sum = sum + (num > mid ? mid : num);
            }
            if (Math.abs(sum - target) <= dist) {
                if (Math.abs(sum - target) < dist) {//如果严格小于dist，那么一定选新的res
                    res = mid;
                } else {
                    res = Math.min(res, mid);//如果等于dist，那么选最小的res
                }
                dist = Math.abs(sum - target);
            }
            if (sum >= target) hi = mid;
            else lo = mid + 1;
        }
        return res;
    }

    /**
     * 摘抄一个，能通过[1,2,3] 7的case
     */
    public int findBestValue__(int[] arr, int target) {
        int l, r, mi, s = 0, m = -1;
        for (int v : arr) {
            s += v;
            m = Math.max(m, v);
        }
        if (s == target) return m; // return the max value since we will keep all nums as is
        for (l = 1, r = m; l < r; ) {
            mi = (l + r) / 2;
            s = 0;
            for (int v : arr) s += (v > mi) ? mi : v;
            if (s >= target) r = mi;
            else l = mi + 1;
        }
        // check if we are 1 step off the target
        int s1 = 0, s2 = 0;
        for (int v : arr) {
            s1 += (v > l) ? (l) : v;
            s2 += (v > l - 1) ? (l - 1) : v;
        }
        return (Math.abs(s2 - target) <= Math.abs(s1 - target)) ? l - 1 : l;
    }
}
