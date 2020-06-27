package array.prefixsum;

public class LongestSubarray {
    /**
     * 题意：一个array，必须去掉一个数字。问最长的连续是1的subarray长度。
     * 解法：我的想法就是prefix sum。技巧是从后再往前遍历一次，把所有连续的部分都set成连续1的长度。
     */
    public int longestSubarray(int[] A) {
        int n = A.length;
        int[] prefix = new int[n];
        int res = -1;
        prefix[0] = A[0];
        for (int i = 1; i < n; i++) {
            if (A[i] == 0) {
                prefix[i] = 0;
            } else {
                prefix[i] = 1 + prefix[i - 1];
            }
        }
        for (int i = n - 2; i >= 0; i--) {
            if (prefix[i + 1] > 0 && prefix[i] > 0) prefix[i] = prefix[i + 1];
        }
        for (int i = 1; i < n - 1; i++) {
            if (A[i] == 0 && A[i - 1] == 1 || A[i] == 0 && A[i + 1] == 1) {
                res = Math.max(res, prefix[i - 1] + prefix[i + 1]);
            }
        }
        if (A[0] == 0) res = Math.max(res, prefix[1]);
        if (A[n - 1] == 0) res = Math.max(res, prefix[n - 2]);
        return res == -1 ? n - 1 : res;
    }
}
