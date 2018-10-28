package dp;

import java.util.HashMap;
import java.util.Map;

/**
 * In an array A of 0s and 1s, how many non-empty subarrays have sum S?
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: A = [1,0,1,0,1], S = 2
 * Output: 4
 * Explanation:
 * The 4 subarrays are bolded below:
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * <p>
 * <p>
 * Note:
 * <p>
 * A.length <= 30000
 * 0 <= S <= A.length
 * A[i] is either 0 or 1.
 * <p>
 * <p>
 * 2018-10-28
 */
public class BinarySubarrayWithSum {
    //two dimensional dp: Memory Limit Exceeded。这题dp真是多此一举。。。
//    public int numSubarraysWithSum(int[] A, int S) {
//        if (A == null || A.length == 0) return 0;
//        //dp[i][j] = dp[i][j-1] + nums[j], dp[i][j]代表i到j位的sum
//        int res = 0;
//        int len = A.length;
//        int[][] dp = new int[len][len];
//        for (int i = 0; i < len; i++) {
//            dp[i][i] = A[i];//dp[i][i]表示[i][i]一定sum是当前数
//            if (A[i] == S) {
//                res++;
//            }
//            for (int j = i + 1; j < len; j++) {
//                dp[i][j] = dp[i][j - 1] + A[j];
//                if (dp[i][j] == S) res++;
//            }
//        }
//        return res;
//    }


    //brute force，这题brute force没写出来竟然。。
//    public int numSubarraysWithSum(int[] A, int S) {
//        if (A == null || A.length == 0) return 0;
//        int len = A.length;
//        int res = 0, sum;
//        for (int i = 0; i < len; i++) {
//            sum = 0;
//            for (int j = i; j < len; j++) {
//                //wrong 会有重复
//                //                for (int k = i; k <= j; k++) {
//                //                    sum += A[k];
//                //                    if (sum == S) res++;
//                //                }
//
//                //right
//                sum += A[j];
//                if (sum == S) res++;
//                else if (sum > S) break;//剪枝1 sum已经大于S，可以结束了
//            }
//            if (sum < S) break; //剪枝2 j走到最后了还小于S，那i再往后也没意义
//        }
//        return res;
//    }

    //two pointers 难写


    //Prefix sum
    //    Let P[i] = A[0] + A[1] + ... + A[i-1]. Then P[j+1] - P[i] = A[i] + A[i+1] + ... + A[j]
    public int numSubarraysWithSum(int[] A, int S) {
        int N = A.length;
        int[] P = new int[N + 1];//需要声明为N + 1 ，为了A[0] == S的情况
        for (int i = 0; i < N; ++i)
            P[i + 1] = P[i] + A[i];

        Map<Integer, Integer> count = new HashMap<>();
        int ans = 0;
        for (int x : P) {
            ans += count.getOrDefault(x, 0);
            //预测到后面可能有和为x + S的P[i], 所以提前 + 1
            count.put(x + S, count.getOrDefault(x + S, 0) + 1);
        }
        return ans;
    }


    public static void main(String args[]) {
        int[] ss = {1, 0, 1, 0, 1};
//        int[] ss = {0, 0, 0, 0, 0};
//        int res = new BinarySubarrayWithSum().numSubarraysWithSum(ss, 0);
        int aa = new BinarySubarrayWithSum().numSubarraysWithSum(ss, 2);

    }
}
