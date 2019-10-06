package dp;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer array arr and an integer difference, return the length of the longest subsequence in arr which is an arithmetic sequence such that the difference between adjacent elements in the subsequence equals difference.
 * Example 1:
 * <p>
 * Input: arr = [1,2,3,4], difference = 1
 * Output: 4
 * Explanation: The longest arithmetic subsequence is [1,2,3,4].
 * Example 2:
 * <p>
 * Input: arr = [1,3,5,7], difference = 1
 * Output: 1
 * Explanation: The longest arithmetic subsequence is any single element.
 * Example 3:
 * <p>
 * Input: arr = [1,5,7,8,5,3,4,2,1], difference = -2
 * Output: 4
 * Explanation: The longest arithmetic subsequence is [7,5,3,1].
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= arr.length <= 10^5
 * -10^4 <= arr[i], difference <= 10^4
 * 20191006
 */
public class LongestArithmeticSubsequenceofGivenDifference {
    /**
     * 题意：找最长的等差数列是difference的子序列的长度。
     * 那我立刻就想到O(n^2)的dp，dp[i] = arr[j] + difference == arr[i] ? Math.max(dp[j] + 1, dp[i]) : dp[i]
     * 但是，数据范围很大，提交了几发不WA了， 但是超时了。然后就gg了。看提交次数不少人应该都和我一样超时了。
     * ------------
     * 看了答案，用的是找A[i]-diff是否有出现过，用map记录以A[i] - diff为结尾的sequence的最大长度。
     * 这种思维方式我没有想到，而是只想到了A[j]+diff。我不确定这种题是否做过，但是很可惜，可能是练习不够。
     * dp[i] : max length of sequence ends with x
     * dp[x] = max(0, dp[x – diff]) + 1
     * map.put(A[i], map.containsKey(A[i] - diff)? map.get(A[i] - diff) + 1 : 1);
     **/
    public int longestSubsequence(int[] arr, int difference) {
        int len = arr.length;
        Map<Integer, Integer> map = new HashMap<>();
        int res = 1;
        for (int i = 0; i < len; i++) {
            map.put(arr[i], map.containsKey(arr[i] - difference) ? map.get(arr[i] - difference) + 1 : 1);
            res = Math.max(res, map.get(arr[i]));
        }
        return res;
    }
}
