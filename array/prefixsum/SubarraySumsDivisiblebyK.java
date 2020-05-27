package array.prefixsum;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array A of integers, return the number of (contiguous, non-empty) subarrays that have a sum divisible by K.
 * Example 1:
 * Input: A = [4,5,0,-2,-3,1], K = 5
 * Output: 7
 * Explanation: There are 7 subarrays with a sum divisible by K = 5:
 * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= A.length <= 30000
 * -10000 <= A[i] <= 10000
 * 2 <= K <= 10000
 * 20200527
 */
public class SubarraySumsDivisiblebyK {
    /**
     * 题意：求subarray sum 的 mod是K的连续字数组的数量。
     * 暴力O(n^2)复杂度会超时；
     * 正确解法，不带index的prefixSum，很像SubArraySumequalstoK，但是这题涉及到同余定理：
     * 从0开始遍历，记录迄今为止的prefixSum，并且记录prefixSum % K出现过的次数；如果之前出现过，说明在那次出现之后到当前数字为止的这部分的和 % K == 0，正好符合要找的子数组。
     **/
    public int subarraysDivByK(int[] A, int K) {
        int n = A.length, sum = 0, res = 0;
        HashMap<Integer, Integer> map = new HashMap<>(); //prefixSum % K => 数量
        map.put(0, 0); // 不要漏了[5]K=5 这种情况
        for (int i = 0; i < n; i++) {
            sum += A[i];
            // int mod = Math.abs(sum % K); // wrong!
            int mod = (sum % K + K) % K; // 考虑[4, -5], Because -1 % 5 = -1, but we need the positive mod 4
            if (map.containsKey(mod)) {
                map.put(mod, map.get(mod) + 1);
                res += map.get(mod);
            } else {
                map.put(mod, 0);
            }
        }
        return res;
    }

    /**
     * 直接利用sum来当做mod
     */
    public int subarraysDivByK_(int[] A, int K) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int count = 0, sum = 0;
        for (int a : A) {
            sum = (sum + a) % K;
            if (sum < 0) sum += K;  // Because -1 % 5 = -1, but we need the positive mod 4
            count += map.getOrDefault(sum, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
