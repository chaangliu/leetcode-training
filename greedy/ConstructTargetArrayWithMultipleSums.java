package greedy;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Given an array of integers target. From a starting array, A consisting of all 1's, you may perform the following procedure :
 * <p>
 * let x be the sum of all elements currently in your array.
 * choose index i, such that 0 <= i < target.size and set the value of A at index i to x.
 * You may repeat this procedure as many times as needed.
 * Return True if it is possible to construct the target array from A otherwise return False.
 * Example 1:
 * Input: target = [9,3,5]
 * Output: true
 * Explanation: Start with [1, 1, 1]
 * [1, 1, 1], sum = 3 choose index 1
 * [1, 3, 1], sum = 5 choose index 2
 * [1, 3, 5], sum = 9 choose index 0
 * [9, 3, 5] Done
 * Example 2:
 * <p>
 * Input: target = [1,1,1,2]
 * Output: false
 * Explanation: Impossible to create target array from [1,1,1,1].
 * Example 3:
 * <p>
 * Input: target = [8,5]
 * Output: true
 * <p>
 * <p>
 * Constraints:
 * <p>
 * N == target.length
 * 1 <= target.length <= 5 * 10^4
 * 1 <= target[i] <= 10^9
 * 20200118
 */
public class ConstructTargetArrayWithMultipleSums {
    /**
     * 题意：一个初始状态都是1的数组，每次可以选一个index让A[i]变成这个数组的和，问以此类推能否达到target数组。
     * 解法：下意识觉得需要递归，数据量很大肯定超时。我看了力扣中文版的讨论区，挺棒的。
     * 「最大的数等于上一轮所有数的和」，这样倒着模拟一下就行了。注意，可以使用PriorityQueue降低复杂度。
     */
    public boolean isPossible(int[] target) {
        int n = target.length, sum = 0, max = 1, idx = -1;
        while (true) {
            sum = 0;
            max = 0;
            for (int i = 0; i < n; i++) {
                if (target[i] > max) {
                    max = target[i];
                    idx = i;
                }
                sum += target[i];
            }
            if (sum == n) return true;
            if (sum < n) return false;
            int remain = sum - max;
            if (remain == 0) return false; // [2] 这种case
            int k = Math.max(1, max / remain - 1);
            int prev = max - remain * k; // [1, 1e9]这种case一并减齐
            if (prev < 1) return false;
            target[idx] = prev;
        }
    }

    /**
     * 讨论区解法，用PriorityQueue找最大和次大数。
     */
    public boolean isPossible_(int[] target) {
        if (target.length == 1) {
            return true;
        }
        PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
        long sum = 0;
        for (int i = 0; i < target.length; i++) {
            sum += target[i];
            pq.offer((long) target[i]);
        }
        //如果此时队列为空或者最大值就是1，直接return true
        if (pq.isEmpty() || pq.peek() == 1) {
            return true;
        }
        while (true) {
            //取出最大的那个
            Long poll = pq.poll();
            //如果此时堆中最大的为1
            if (pq.peek() == 1) {
                //直接看它满足或不满足公式
                return (poll - 1) % (sum - poll) == 0;
            } else {
                //需要计算多少轮才能比第二小的数小
                long n = (poll - pq.peek()) / (sum - poll) + 1;
                //得到这个数字
                long x = poll - n * (sum - poll);
                if (x <= 0) {
                    return false;
                }
                //更新sum
                sum = poll - (sum - poll) * (n - 1);
                pq.offer(x);
            }
        }
    }
}
