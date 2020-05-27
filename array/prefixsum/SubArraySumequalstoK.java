package array.prefixsum;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
 * <p>
 * Example 1:
 * Input:nums = [1,1,1], k = 2
 * Output: 2
 * <p>
 * dp[i+1] =
 * Created by DrunkPiano on 2017/4/30.
 */

public class SubArraySumequalstoK {

    /**
     * 题意：给你一个int数组和一个k，找出有多少个和=k的连续子串。
     * 解法：看着像sliding window，但其实是prefix sum，但是这个prefix sum不关心index，只关心过去是否有一段subarray的sum是k
     * preSum + HashMap，one pass
     */
    public int subarraySum(int[] nums, int k) {
        int sum = 0, result = 0;
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1); // 有1个和为0的子串""

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (preSum.containsKey(sum - k)) {// 意思是，在[sum - k, sum]这段区间内的sum是k
                result += preSum.get(sum - k);
            }
            preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
        }
        return result;
    }
}
