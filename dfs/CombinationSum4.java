package dfs;

import java.util.Arrays;

/**
 * Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.
 * <p>
 * Example:
 * <p>
 * nums = [1, 2, 3]
 * target = 4
 * <p>
 * The possible combination ways are:
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * <p>
 * Note that different sequences are counted as different combinations.
 * <p>
 * Therefore the output is 7.
 * Follow up:
 * What if negative numbers are allowed in the given array?
 * How does it change the problem?
 * What limitation we need to add to the question to allow negative numbers?
 * <p>
 * <p>
 * Created by liu chang on Sept 23, 2018.
 */
public class CombinationSum4 {

    /**
     * 题意：给定一个由正整数组成且不存在重复数字的数组，找出和为给定目标正整数的组合的个数。
     * 用DFS会超时。正确解法是DP，
     * 和322. coin change 类似，都是bottom up想法，假设还有一步就能达到target；
     * 那么最后一次pick：对于[1,2,3]和target=4，f(4) = f(4-3) + f(4-2) + f(4 - 1), 这么看其实有点像青蛙跳台阶或者斐波那契数列，哈哈
     * -----------------------------------------------------------------
     * 摘录一段别人的讲解，我觉得总结得很好：
     * 对于组合选取类问题都可以使用动态规划。即由最后一次选取的数值（上个状态）递推当前状态，如[1,2,4]和32，
     * 最后一次选1意味着组合种类有dp[32-1]种，同理选2有dp[32-2]种，选4有dp[32-4]种。即dp[i]=sum(dp[i-p]),
     * p=nums[j],p<=i。但是这种自底向上的递推过程存在一个问题，即某些dp[i]不是最终求取dp[target]所需要的，也
     * 就是评论中大家反应的超int范围问题，解决方法可以不需要使用long，unsigned long，unsigned long long等大类型，
     * 而是在求和时先比较dp[i]与INT_MAX-dp[i-p]的大小，因为没有求和，所以在没有超int范围的情况下判断了dp[i]是否超
     * int范围。若dp[i]>INT_MAX-dp[i-p],则说明本次求和之后，dp[i]会超范围，直接置为0或者跳过当前i即可，因为与最终
     * 的dp[target]无关。时间复杂度最好情况0ms，100%。
     * 作者：ankang0320
     * 链接：https://leetcode-cn.com/problems/two-sum/solution/dong-tai-gui-hua-zi-ding-xiang-xia-de-bei-wang-lu-/
     */
    public int combinationSum4_(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i < num) continue;
                dp[i] += dp[i - num];
            }
        }
        return dp[target];
    }

    /**
     * top down, dfs with memo
     * //Imagine we only need one more number to reach target, this number can be any one in the array.
     * //comb[target] = sum(comb[target - nums[i]]), where 0 <= i < nums.length, and target >= nums[i].
     */
    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0 || target < 1) return 0;
        int[] dp = new int[target + 1];
        Arrays.fill(dp, -1);
        dp[0] = 1;//In how many ways is it possible to get zero count given that all the elements are positive?" One 1 way - to not choose any element n choose 0
        return helper(dp, nums, target);
    }

    // 构成target的方案 = sum(构成 target - num的方案)
    private int helper(int[] dp, int[] nums, int target) {
        if (dp[target] != -1) return dp[target];
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (target >= nums[i]) {
                res += helper(dp, nums, target - nums[i]);
            }
        }
        dp[target] = res;
        return res;
    }

    public static void main(String args[]) {
        int[] nums = {1, 2, 3};
        int res = new CombinationSum4().combinationSum4_(nums, 4);
        System.out.println();
    }
}
