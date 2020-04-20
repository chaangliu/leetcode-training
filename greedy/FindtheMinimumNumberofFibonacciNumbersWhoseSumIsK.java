package greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * Given the number k, return the minimum number of Fibonacci numbers whose sum is equal to k, whether a Fibonacci number could be used multiple times.
 * The Fibonacci numbers are defined as:
 * F1 = 1
 * F2 = 1
 * Fn = Fn-1 + Fn-2 , for n > 2.
 * It is guaranteed that for the given constraints we can always find such fibonacci numbers that sum k.
 * Example 1:
 * Input: k = 7
 * Output: 2
 * Explanation: The Fibonacci numbers are: 1, 1, 2, 3, 5, 8, 13, ...
 * For k = 7 we can use 2 + 5 = 7.
 * Example 2:
 * <p>
 * Input: k = 10
 * Output: 2
 * Explanation: For k = 10 we can use 2 + 8 = 10.
 * Example 3:
 * <p>
 * Input: k = 19
 * Output: 3
 * Explanation: For k = 19 we can use 1 + 5 + 13 = 19.
 * Constraints:
 * 1 <= k <= 10^9
 * 20200420
 */
public class FindtheMinimumNumberofFibonacciNumbersWhoseSumIsK {
    /**
     * 题意：给你一个数字k，问最少可以用几个斐波那契数字组成k。
     * 解法：greedy；但是需要证明。
     * 我用了binary search找最大的，注意斐波那契数字大概只要几十个就会超过10^9，数据规模非常小，所以可以暴力求解
     */
    public int findMinFibonacciNumbers(int k) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        while (true) {
            int cur = list.get(list.size() - 1) + list.get(list.size() - 2);
            if (cur <= 0) break;
            list.add(cur);
        }
        int res = 1;
        while (k >= 0) {
            // 找第一个<= k的数
            int lo = 0, hi = list.size();
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (list.get(mid) >= k) hi = mid;
                else lo = mid + 1;
            }
            if (list.get(lo) == k) return res;
            System.out.println("最接近的：" + list.get(lo - 1));
            k -= list.get(lo - 1);
            res++;
        }
        return res;
    }

    public int findMinFibonacciNumbers_(int k) {
        if (k < 2) return k;
        int a = 1, b = 1, c = 2;
        while (b <= k) {
            c = a + b;
            a = b;
            b = c;
        }
        return 1 + findMinFibonacciNumbers(k - a);
    }
}
