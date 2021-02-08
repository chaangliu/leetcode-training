package dp;

/**
 * A subarray A[i], A[i+1], ..., A[j] of A is said to be turbulent if and only if:
 * For i <= k < j, A[k] > A[k+1] when k is odd, and A[k] < A[k+1] when k is even;
 * OR, for i <= k < j, A[k] > A[k+1] when k is even, and A[k] < A[k+1] when k is odd.
 * That is, the subarray is turbulent if the comparison sign flips between each adjacent pair of elements in the subarray.
 * Return the length of a maximum size turbulent subarray of A.
 * Example 1:
 * Input: [9,4,2,10,7,8,8,1,9]
 * Output: 5
 * Explanation: (A[1] > A[2] < A[3] > A[4] < A[5])
 * Example 2:
 * Input: [4,8,12,16]
 * Output: 2
 * Example 3:
 * Input: [100]
 * Output: 1
 * Note:
 * 1 <= A.length <= 40000
 * 0 <= A[i] <= 10^9
 * 20191030
 */
public class LongestTurbulentSubarray {
    /**
     * 题意：求turbulent subarray(也就是wiggle subarray)的最长长度。这题的转移方程就和Maximum Subarray类似。
     * 我的代码：dp[i]代表以i结尾的turbulent array的长度
     * O(n) Time, O(n) Space
     */
    public int maxTurbulenceSize(int[] A) {
        if (A.length < 2) return 1;
        int[] dp = new int[A.length];
        dp[0] = 1;
        dp[1] = A[1] != A[0] ? 2 : 1;
        int res = dp[1];
        for (int i = 2; i < A.length; i++) {
            dp[i] = A[i - 2] > A[i - 1] && A[i - 1] < A[i] || A[i - 2] < A[i - 1] && A[i - 1] > A[i] ? dp[i - 1] + 1 : A[i - 1] != A[i] ? 2 : 1; // 只需要判断倒数三个数字
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /**
     * 讨论区的代码，把升和降分开记录了，O(1)Space
     * inc: The length of current valid sequence which ends with two increasing numbers
     * dec: The length of current valid sequence which ends with two decreasing numbers
     */
    public int maxTurbulenceSize_(int[] A) {
        int inc = 1, dec = 1, result = 1;
        for (int i = 1; i < A.length; i++) {
            if (A[i] < A[i - 1]) {
                dec = inc + 1;
                inc = 1;
            } else if (A[i] > A[i - 1]) {
                inc = dec + 1;
                dec = 1;
            } else {
                inc = 1;
                dec = 1;
            }
            result = Math.max(result, Math.max(dec, inc));
        }
        return result;
    }

    /**
     * two pointers或者说是sliding window思路的代码
     */
    public int maxTurbulenceSize_two_pointers(int[] arr) {
        if (arr.length == 1) return 1;
        if (arr.length == 2) return arr[0] == arr[1] ? 1 : 2;
        int res = arr[0] == arr[1] ? 1 : 2;
        int l = 0, r = 2;
        while (r < arr.length) {
            if (arr[r] - arr[r - 1] > 0 && arr[r - 1] - arr[r - 2] < 0 || arr[r] - arr[r - 1] < 0 && arr[r - 1] - arr[r - 2] > 0) { // 只需要判断倒数三个数字是否符合
                res = Math.max(res, r - l + 1);
            } else {
                l = r - 1;
            }
            r++;
        }
        return res;
    }
}
