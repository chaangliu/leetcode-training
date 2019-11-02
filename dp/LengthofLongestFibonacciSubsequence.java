package dp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A sequence X_1, X_2, ..., X_n is fibonacci-like if:
 * n >= 3
 * X_i + X_{i+1} = X_{i+2} for all i + 2 <= n
 * Given a strictly increasing array A of positive integers forming a sequence, find the length of the longest fibonacci-like subsequence of A.  If one does not exist, return 0.
 * (Recall that a subsequence is derived from another sequence A by deleting any number of elements (including none) from A, without changing the order of the remaining elements.  For example, [3, 5, 8] is a subsequence of [3, 4, 5, 6, 7, 8].)
 * Example 1:
 * Input: [1,2,3,4,5,6,7,8]
 * Output: 5
 * Explanation:
 * The longest subsequence that is fibonacci-like: [1,2,3,5,8].
 * Example 2:
 * Input: [1,3,7,11,12,14,18]
 * Output: 3
 * Explanation:
 * The longest subsequence that is fibonacci-like:
 * [1,11,12], [3,11,14] or [7,11,18].
 * Note:
 * 3 <= A.length <= 1000
 * 1 <= A[0] < A[1] < ... < A[A.length - 1] <= 10^9
 * (The time limit has been reduced by 50% for submissions in Java, C, and C++.)
 * 20191102
 */
public class LengthofLongestFibonacciSubsequence {
    /**
     * 题意：给你一个递增的数组，求最长等差子序列的长度（长度至少是3的才能称为等差数列）。
     * 我找了一下转移方程的规律，发现对于1，3，7，11，12，14这样的序列，以3，11开头的序列长度就等于11,14开头的序列长度+1。
     * 这样就可以用LIS那种两层循环去从后往前枚举最长数列。
     * 但是我一开始用map保存i#j => len这种形式去做dp发现超时，可见Map用string作key效率真的低。
     * 看了答案发现别有人存储的是下标，于是改写了一下通过了。
     * dp[i][j]代表A[i],A[j]开头的fib seq的最大长度，dp[j][i] = dp[i][map.get(next)] + 1;
     * 当然，记录A[i],A[j]结尾的seq也行，答案区就有那样的答案。
     * 下面是我的代码。
     */
    public int lenLongestFibSubseq(int[] A) {
        int res = 0, len = A.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            map.put(A[i], i);
        }
        int[][] dp = new int[len][len];//dp[i][j]代表A[i],A[j]开头的fib seq的最大长度
        for (int i = len - 1; i > 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (dp[j][i] == 0) {
                    dp[j][i] = 2;
                }
                int next = A[j] + A[i];
                if (map.containsKey(next)) {
                    dp[j][i] = dp[i][map.get(next)] + 1;
                    res = Math.max(res, dp[j][i]);
                }
            }
        }
        return res >= 3 ? res : 0;
    }

    /**
     * lee的代码1
     * O(N^2logM), where M is the max(A).
     */
    public int lenLongestFibSubseq__(int[] A) {
        Set<Integer> s = new HashSet<Integer>();
        for (int x : A) s.add(x);
        int res = 2;
        for (int i = 0; i < A.length; ++i)
            for (int j = i + 1; j < A.length; ++j) {
                int a = A[i], b = A[j], l = 2;
                while (s.contains(a + b)) {
                    b = a + b;
                    a = b - a;
                    l++;
                }
                res = Math.max(res, l);
            }
        return res > 2 ? res : 0;
    }

    /**
     * lee的代码2
     * O(N^2)
     */
    public int lenLongestFibSubseq_(int[] A) {
        int res = 0;
        int[][] dp = new int[A.length][A.length];
        Map<Integer, Integer> index = new HashMap<>();
        for (int j = 0; j < A.length; j++) {
            index.put(A[j], j);
            for (int i = 0; i < j; i++) {
                int k = index.getOrDefault(A[j] - A[i], -1);
                dp[i][j] = (A[j] - A[i] < A[i] && k >= 0) ? dp[k][i] + 1 : 2;
                res = Math.max(res, dp[i][j]);
            }
        }
        return res > 2 ? res : 0;
    }
}
