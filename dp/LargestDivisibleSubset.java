package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies:
 * <p>
 * Si % Sj = 0 or Sj % Si = 0.
 * <p>
 * If there are multiple solutions, return any subset is fine.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,3]
 * Output: [1,2] (of course, [1,3] will also be ok)
 * Example 2:
 * <p>
 * Input: [1,2,4,8]
 * Output: [1,2,4,8]
 * 20190913
 */
public class LargestDivisibleSubset {
    /**
     * 这题在飞机上想到是类似LIS，写了个转移方程，但是会来仔细看了下发现是求具体序列，这个就更难一些
     * 看到有人用的记录index的方法，有人用的保存当前字符串的方法，前者空间好点，值得复习
     * //1,2,4,6
     * //1,4,2,6
     * //  j   i
     * //dp[i] = A[i] % A[j] == 0 ? max(dp[j] + 1, dp[i]) : dp[j];
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int[] pre = new int[n];
        Arrays.sort(nums);//顺序无关
        int max = 0, index = -1;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            pre[i] = -1;//在这里初始化
            for (int j = i - 1; j >= 0; j--) {//技巧：从i往前，这样可以判断最大的数是否满足
                if (nums[i] % nums[j] == 0) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        pre[i] = j;
                    }
                }
            }
            if (max < dp[i]) {
                max = dp[i];
                index = i;
            }
        }
        List<Integer> res = new ArrayList<>();
        while (index != -1) {
            res.add(0, nums[index]);
            index = pre[index];
        }
        return res;
    }


    /**
     * Approach2, dfs with memo..参考一下
     * https://leetcode.com/problems/largest-divisible-subset/discuss/84013/Very-short-Java-DFS-solution-using-Memoization
     */
    public List<Integer> largestDivisibleSubset__(int[] nums) {
        Arrays.sort(nums);
        return helper(nums, 0);
    }

    Map<Integer, List<Integer>> mem = new HashMap<>();

    private List<Integer> helper(int[] nums, int i) {
        if (mem.containsKey(i))
            return mem.get(i);
        List<Integer> maxLenLst = new ArrayList<>();
        int div = i == 0 ? 1 : nums[i - 1];
        for (int k = i; k < nums.length; k++) {
            if (nums[k] % div == 0) {
                // make a copy is crucial here, so that we won't modify the returned List reference
                List<Integer> lst = new ArrayList<>(helper(nums, k + 1));
                lst.add(nums[k]);
                if (lst.size() > maxLenLst.size())
                    maxLenLst = lst;
            }
        }
        mem.put(i, maxLenLst);
        return maxLenLst;
    }

}
