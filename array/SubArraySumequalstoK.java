package array;

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

    public int subarraySum(int[] nums, int k) {
        int sum = 0, result = 0;
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (preSum.containsKey(sum - k)) {
                result += preSum.get(sum - k);
            }
            preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
        }
        return result;
    }


    //20190204 review
    //approach 1. 累加
    //nums[i : j] = sum[j + 1] - sum[i], 用一个map记录<迄今为止>subarray的值；时间仍然是O(n * n)
    public int subarraySum__MAP(int[] nums, int k) {
        if (nums == null || nums.length < 1) return 0;
        int res = 0;
        int[] curSum = new int[nums.length + 1];
        curSum[1] = nums[0];
        for (int i = 0; i < nums.length; i++) {
            curSum[i + 1] = curSum[i] + nums[i];
        }
        for (int start = 0; start < nums.length; start++) {
            for (int end = start + 1; end <= nums.length; end++) {
                if (curSum[end] - curSum[start] == k)
                    res++;
            }
        }
        return res;
    }

    //approach2. preSum + HashMap，one pass
    //其实跟approach1的思路相同,nums[i : j] = sum[j + 1] - sum[i]，但用缓存保存当前的prefixSum，跟two sum思路很像，一边遍历一边在map中寻找target - nums[i]（因为可能有负数，所以可能存在多个target - nums[i]），找到了就+1
    public int subarraySum__preSum(int[] nums, int k) {
        if (nums == null || nums.length < 1) return 0;
        int res = 0;
        int curSum = 0;
        Map<Integer, Integer> preSumMap = new HashMap<>();
        preSumMap.put(0, 1);//corner case: nums长度是1的时候
        for (int i = 0; i < nums.length; i++) {
            curSum += nums[i];
            if (preSumMap.containsKey(curSum - k)) {
                res += preSumMap.get(curSum - k);//not res++
            }
            preSumMap.put(curSum, preSumMap.getOrDefault(curSum, 0) + 1);
        }
        return res;
    }

    public static void main(String args[]) {
//        int[] nums = {1, -1, 1, -1};
        int[] nums = {1, 2, 1, 2, 1};
        //int[] nums = {-92, -63, 75, -86, -58, 22, 31, -16, -66, -67, 420};
        System.out.println(new SubArraySumequalstoK().subarraySum__preSum(nums, 3));
    }

}
