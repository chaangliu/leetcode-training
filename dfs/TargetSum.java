package dfs;

import java.util.HashMap;
import java.util.Map;



/**
 * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
 * <p>
 * Find out how many ways to assign symbols to make sum of integers equal to target S.
 * <p>
 * Example 1:
 * Input: nums is [1, 1, 1, 1, 1], S is 3.
 * Output: 5
 * Explanation:
 * <p>
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * <p>
 * There are 5 ways to assign symbols to make the sum of nums be target 3.
 * <p>
 * 20190220
 */

public class TargetSum {
    public static void main(String args[]) {
        int nums[] = {1, 1, 1, 1, 1};
        new TargetSum().findTargetSumWays_MEMO(nums, 3);
    }

    /**
     * approach1, dfs，类似word search那种floodfill，只不过这个只有两种方向。
     * 执行顺序类似这样：+++，++-，+-+，+--，-++，-+-，--+，---
     */
    int res = 0;

    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0) return 0;
        helper(nums, S, 0, 0);
        return res;
    }

    private void helper(int[] nums, int S, int sum, int index) {
        if (index == nums.length) {
            if (sum == S) res++;
            return;
        }
        helper(nums, S, sum + nums[index], index + 1);
        helper(nums, S, sum - nums[index], index + 1);
    }


    //---------------------------------------------------------------------------------

    /**
     * approach1的剪枝
     */
    int result = 0;

    public int findTargetSumWaysPruning(int[] nums, int S) {
        if (nums == null || nums.length == 0) return result;

        int n = nums.length;
        int[] sums = new int[n];
        sums[n - 1] = nums[n - 1];
        //从后往前计算剩下的值的和
        for (int i = n - 2; i >= 0; i--)
            sums[i] = sums[i + 1] + nums[i];

        helper(nums, sums, S, 0);
        return result;
    }

    public void helper(int[] nums, int[] sums, int target, int pos) {
        if (pos == nums.length) {
            if (target == 0) result++;
            return;
        }

        if (sums[pos] < Math.abs(target)) return;

        helper(nums, sums, target + nums[pos], pos + 1);
        helper(nums, sums, target - nums[pos], pos + 1);
    }


    //---------------------------------------------------------------------------------

    /**
     * approach #2 Recursion with memoization
     */

    public int findTargetSumWays_MEMO(int[] nums, int S) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int ans = helper(nums, 0, 0, S, new HashMap(), "");
        System.out.println(ans);
        return ans;
    }

    //这个函数返回的是：当指针指到index了，之前遍历过的path的和是sum，要达到S有可能有几种组合。比如1，1，1，1，1；2->2的时候还有三种组合（剩下的三个1里面任意一个为负数）
    private int helper(int[] nums, int index, int sum, int S, Map<String, Integer> map, String path) {
        String encodeString = index + "->" + sum;
        if (map.containsKey(encodeString)) {
            return map.get(encodeString);
        }
        if (index == nums.length) {
            if (sum == S) {
                return 1;
            } else {
                return 0;
            }
        }
        int curNum = nums[index];
        int add = helper(nums, index + 1, sum + curNum, S, map, path + "+" + curNum);
        int minus = helper(nums, index + 1, sum - curNum, S, map, path + "-" + curNum);
        map.put(encodeString, add + minus);
        return add + minus;
    }
}
