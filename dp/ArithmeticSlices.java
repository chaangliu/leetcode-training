package dp;

/**
 * A sequence of number is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.
 * For example, these are arithmetic sequence:
 * 1, 3, 5, 7, 9
 * 7, 7, 7, 7
 * 3, -1, -5, -9
 * The following sequence is not arithmetic.
 * 1, 1, 2, 5, 7
 * A zero-indexed array A consisting of N numbers is given. A slice of that array is any pair of integers (P, Q) such that 0 <= P < Q < N.
 * A slice (P, Q) of array A is called arithmetic if the sequence:
 * A[P], A[p + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.
 * The function should return the number of arithmetic slices in the array A.
 * Example:
 * A = [1, 2, 3, 4]
 * return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.
 * 20190917
 * 20191101
 */
public class ArithmeticSlices {
    /**
     * 题意：给你一个数组，让你找出等差数列slice（连续）的个数。P + 1 < Q暗示我们slice最短是3. 例如A = [1, 2, 3, 4]，返回3.
     * 这题我自己写出来了O(n^2)解法，但是有O(n)解法的。。中途WA了几次。有个case没考虑到[1,2,3,8,9,10]
     * 我的思路是：dp[i][j] = j - i == 2 ? [判断A[i,j]是否等差] : dp[i + 1][j] && nums[i] - nums[i + 1] == nums[i + 1] - nums[i + 2]
     */
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        if (n <= 2) return 0;
        int res = 0;
        boolean[][] dp = new boolean[n][n];
        for (int j = 2; j < n; j++) {
            for (int i = j - 2; i >= 0; i--) {
                if (i == j - 2) {
                    if (nums[i] - nums[i + 1] == nums[i + 1] - nums[i + 2]) {
                        dp[i][j] = true;
                    }
                } else {
                    dp[i][j] = dp[i + 1][j] && nums[i] - nums[i + 1] == nums[i + 1] - nums[i + 2];
                }
                if (dp[i][j]) {
                    System.out.println("i" + i + ", j" + j + ", " + dp[i][j]);
                    res++;
                }
            }
        }
        return res;
    }

    /**
     * O(n)做法，
     * [1, 2, 3, 4, 5]
     * 连续的arr里面如果当前加入的num符合，一定会在当前num的等差数列数量里面+1；如果当前已经是长度4了，那就要+2（234和1234）；如果长度是5了那就要+3(345,2345,12345);
     * 如果当前数字打断了等差数列就把cur reset到0.
     * Any time we find ith index does not form arith seq, make currently running no of seqs to zero.
     */
    public int numberOfArithmeticSlices_(int[] A) {
        int curr = 0, sum = 0;
        for (int i = 2; i < A.length; i++)
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                curr += 1;
                sum += curr;
            } else {
                curr = 0;
            }
        return sum;
    }
}
