package greedy;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into sets of k consecutive numbers
 * Return True if its possible otherwise return False.
 * Example 1:
 * Input: nums = [1,2,3,3,4,4,5,6], k = 4
 * Output: true
 * Explanation: Array can be divided into [1,2,3,4] and [3,4,5,6].
 * Example 2:
 * <p>
 * Input: nums = [3,2,1,2,3,4,3,4,5,9,10,11], k = 3
 * Output: true
 * Explanation: Array can be divided into [1,2,3] , [2,3,4] , [3,4,5] and [9,10,11].
 * Example 3:
 * <p>
 * Input: nums = [3,3,2,2,1,1], k = 3
 * Output: true
 * Example 4:
 * <p>
 * Input: nums = [1,2,3,4], k = 3
 * Output: false
 * Explanation: Each array should be divided in subarrays of size 3.
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= k <= nums.length
 * 20191222
 */
public class DivideArrayinSetsofKConsecutiveNumbers {
    /**
     * 题意：给你一个无序的可能有重复数字的数组，和一个k，问你能不能正好把数组里的所有数字分成一些长度为k的顺子。
     * 我的做法，贪心地去取，每次取最小的。
     */
    public boolean isPossibleDivide(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        if (nums.length % k != 0) return false;
        if (k == 1) return true;
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            min = Math.max(min, num);
        }
        while (map.size() != 0) {
            for (int i = min; i < min + k; i++) {
                if (!map.containsKey(i)) return false;
                map.put(i, map.get(i) - 1);
                if (map.get(i) == 0) map.remove(i);
            }
            min = Integer.MAX_VALUE;
            for (int i : map.keySet()) {
                min = Math.min(i, min);
            }
        }
        return map.size() == 0;
    }

    /**
     * 也可以用一个pq来维护最小的
     */
    public boolean isPossibleDivide_(int[] nums, int k) {
        int len = nums.length;
        if (len % k != 0) return false;
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int n : nums)
            map.put(n, map.getOrDefault(n, 0) + 1);
        for (int n : map.keySet())
            pq.add(n);
        while (!pq.isEmpty()) {
            int cur = pq.poll();
            if (map.get(cur) == 0) continue;
            int times = map.get(cur);
            for (int i = 0; i < k; i++) {
                if (!map.containsKey(cur + i) || map.get(cur + i) < times)
                    return false;
                map.put(cur + i, map.get(cur + i) - times);
            }
            len -= k * times;
        }
        return len == 0;
    }
}
