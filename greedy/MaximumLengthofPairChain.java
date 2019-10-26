package greedy;

import java.util.Arrays;

/**
 * You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.
 * <p>
 * Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs can be formed in this fashion.
 * <p>
 * Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs. You can select pairs in any order.
 * <p>
 * Example 1:
 * Input: [[1,2], [2,3], [3,4]]
 * Output: 2
 * Explanation: The longest chain is [1,2] -> [3,4]
 * Note:
 * The number of given pairs will be in the range [1, 1000].
 * 20191024
 */
public class MaximumLengthofPairChain {
    /**
     * 题意：每个Pair数组第一个元素代表start time,第二个元素代表end time；安排一个链条，要求下一个pair的start在前一个pair的end之后。求这个链条的最大长度。
     * 普通思路是类似LIS的那种O(n^2)的DP。我观察了一下，这里把array按照开始时间或者结束时间都能WORK。
     * Greedy思路是按照结束时间sort。
     * Approach1: DP
     * sort by start time
     **/
    public int findLongestChain(int[][] A) {
        Arrays.sort(A, (a, b) -> a[0] - b[0]);
        int len = A.length, res = 0;
        int[] dp = new int[len];
        //dp[0] = 1
        for (int i = 0; i < len; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (A[j][1] < A[i][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(dp[i], res);
        }
        return res;
    }


    /**
     * Approach2: DP
     * sort by end time
     **/
    public int findLongestChain__DP(int[][] A) {
        Arrays.sort(A, (a, b) -> a[1] - b[1]);
        int len = A.length, res = 0;
        int[] dp = new int[len];
        //dp[0] = 1
        for (int i = 0; i < len; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (A[j][1] < A[i][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(dp[i], res);
        }
        return res;
    }

    /**
     * Approach3: greedy , Time : O(n)
     * 比如对于[1,2][3,100][3,7][4,5]，greedy的话用start time来sort一定不合理（会挡住后面潜在的pair），很容易想到sort by end time;
     * [1,2][4,5][3,7][3,100] 这时候选择[4,5]固然会影响[3,7]，但是从后续来说，5结束比7结束更优秀。那么有没有可能优先选择[4,5]造成了两个区间的损失？没有可能，最多只能影响一个。
     **/
    public int findLongestChain__GREEDY(int[][] A) {
        Arrays.sort(A, (a, b) -> a[1] - b[1]);
        int len = A.length, res = 1, prev = 0;
        for (int i = 1; i < len; i++) {
            if (A[i][0] > A[prev][1]) {
                res++;
                prev = i;
            }
        }
        return res;
    }
}
