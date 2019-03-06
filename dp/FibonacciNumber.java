package dp;

import java.util.HashMap;

/**
 * The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,
 * <p>
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), for N > 1.
 * Given N, calculate F(N).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: 2
 * Output: 1
 * Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.
 * Example 2:
 * <p>
 * Input: 3
 * Output: 2
 * Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.
 * Example 3:
 * <p>
 * Input: 4
 * Output: 3
 * Explanation: F(4) = F(3) + F(2) = 2 + 1 = 3.
 * <p>
 * <p>
 * Note:
 * <p>
 * 0 ≤ N ≤ 30.
 * <p>
 * 20190305
 */
public class FibonacciNumber {

    /**
     * approach1. recursion with memorization, 其实就是dp
     * F(0) = 0,   F(1) = 1
     * F(N) = F(N - 1) + F(N - 2), for N > 1.
     */
    int pre = 0, cur = 1;

    public int fib(int N) {
        if (N == 0) return 0;
        if (N == 1) return 1;
        helper(2, N);
        return cur;
    }

    private void helper(int index, int N) {
        if (index > N) return;
        int tmp = cur;
        cur = cur + pre;
        pre = tmp;
        helper(index + 1, N);
    }


    /**
     * 另种写法
     */

    HashMap<Integer, Integer> cache = new HashMap<Integer, Integer>();

    private int fib__(int N) {
        if (cache.containsKey(N)) {
            return cache.get(N);
        }
        int result;
        if (N < 2) {
            result = N;
        } else {
            result = fib__(N - 1) + fib__(N - 2);
        }
        // keep the result in cache.
        cache.put(N, result);
        return result;
    }
}
