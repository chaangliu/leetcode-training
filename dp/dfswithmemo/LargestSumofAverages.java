package dp.dfswithmemo;

import java.util.ArrayList;

/**
 * We partition a row of numbers A into at most K adjacent (non-empty) groups, then our score is the sum of the average of each group. What is the largest score we can achieve?
 * <p>
 * Note that our partition must use every number in A, and that scores are not necessarily integers.
 * <p>
 * Example:
 * Input:
 * A = [9,1,2,3,9]
 * K = 3
 * Output: 20
 * Explanation:
 * The best choice is to partition A into [9], [1, 2, 3], [9]. The answer is 9 + (1 + 2 + 3) / 3 + 9 = 20.
 * We could have also partitioned A into [9, 1], [2], [3, 9], for example.
 * That partition would lead to a score of 5 + 2 + 6 = 13, which is worse.
 * Note:
 * <p>
 * 1 <= A.length <= 100.
 * 1 <= A[i] <= 10000.
 * 1 <= K <= A.length.
 * Answers within 10^-6 of the correct answer will be accepted as correct.
 * 20191018
 */
public class LargestSumofAverages {
    /**
     * 题意：给你一个数组，分成最多三个连续的sub array,每个sub Array求平均数相加得到sum，问sum的最小值。
     * 解法：我的想法，用prefix数组，一个dfs去记录目前有几个分割点，当达到k - 1 个的时候返回。每次递归，都按照分割点数组里的位置进行分割计算，由于使用了前缀数组，可以O(1)得到每一段的average sum。
     * 这样的做法是对的，但是仍然会超时。我又想，是否能用dp[i][j]代表前i个数字的subarray中有j个pivot的时候的最小average sum？但是如果这样的话，怎么能把dp[i][j]和dp[i+1][j]挂钩呢？
     * 然后我看答案了。
     * 我发现lee果然采用了一个memo[i][j]代表前i个数字的subarray中有j个pivot的时候的最小average sum。他的操作方式是，dfs函数返回memo[i][j]；
     * 技巧是[从后往前]，这样可以累加当前位置到断点处的sum。因为我们的memo[i][j]表示的是「前」i个数字组成的数组有j个断点，所以从后往前计算可以保证dfs总是从0开始，不用每次维护新的start了。
     * 那么要不要继续往下分割，比较一下就好了。这个思路很像是剪绳子(Integer Break)那题。
     * memo[i][j] = max(memo[i][j], dfs(i-1, j-1) + cur/(n-i))
     * <p>
     * 下面先展示我的答案，再展示lee的答案。
     * Approach1 我的答案，TLE
     */
    public double largestSumOfAverages__TLE(int[] A, int K) {
        int[] prefix = new int[A.length];
        prefix[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            prefix[i] = prefix[i - 1] + A[i];
        }
        dfs(prefix, 0, K, A, new ArrayList<Integer>());
        return res;
    }

    double res = 0;

    private void dfs(int[] prefix, int start, int K, int[] A, ArrayList<Integer> pivots) {
        if (pivots.size() >= K) {
            return;
        }
        double average = 0;
        int lastEnd = -1, end;
        for (int p : pivots) {//e.g. [1]->[9], [1, 2, 3]
            end = p - 1;
            average += (prefix[end] - (lastEnd == -1 ? 0 : prefix[lastEnd])) * 1.0 / (end - lastEnd);
            lastEnd = end;
        }
        average += (prefix[prefix.length - 1] - (lastEnd == -1 ? 0 : prefix[lastEnd])) * 1.0 / (prefix.length - 1 - lastEnd);
        res = Math.max(res, average);
        for (int i = start + 1; i < A.length; i++) {
            pivots.add(i);
            dfs(prefix, i, K, A, pivots);
            pivots.remove(pivots.size() - 1);
        }
    }


    /**
     * 下面是按照lee的思路写的答案，类似割绳子那题；
     * 一开始写错了好几个地方，检查了很久，尤其是cur / (n - i + 1)写成了cur/(A.length - i)
     * memo[i][j] represents largest sum of averages of first i elements of A split into at most j groups
     */
    public double largestSumOfAverages(int[] A, int K) {
        double[][] memo = new double[A.length][K + 1];
        double cur = 0;
        for (int i = 0; i < A.length; i++) {
            cur = cur + A[i];
            memo[i][1] = cur / (i + 1);
        }
        return dfs(A, A.length - 1, K, memo);
    }

    private double dfs(int[] A, int n, int k, double[][] memo) {
        if (n + 1 < k) return 0;
        if (memo[n][k] > 0) {
            return memo[n][k];
        }
        double cur = 0;
        for (int i = n; i > 0; i--) {
            cur += A[i];//这里不能放到递归函数中，否则会backtrack无法累加..
            memo[n][k] = Math.max(memo[n][k], cur / (n - i + 1) + dfs(A, i - 1, k - 1, memo));
        }
        return memo[n][k];
    }
}
