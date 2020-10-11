package dfs.backtracking;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.
 * Example 1:
 * Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * Output: True
 * Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 * Note:
 * <p>
 * 1 <= k <= len(nums) <= 16.
 * 0 < nums[i] < 10000.
 * <p>
 * 20190203
 */
public class PartitionToKEqualSumSubsets {
    /**
     * 题意：问能否把数字的数字分为k个sum相同的小组。
     * 解法：backtrack
     */
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) sum += num;
        if (k <= 0 || sum % k != 0) return false;
        int[] visited = new int[nums.length];
        return canPartition(nums, visited, 0, k, 0, sum / k);
    }

    //这个写法跟[word search]那题很像，返回true的条件只有一个，就是只剩一组了（最后一组的sum必定是k）。
    //使用start_index防止下一次递归范文比index小的数
    public boolean canPartition(int[] nums, int[] visited, int start_index, int k, int cur_sum, int target) {
        if (k == 1) return true;
        if (cur_sum == target)
            //一旦找到了一组，start_index再次就从0开始；但是visited数组维持原来状态；再找下一组的时候不能使用v过的数
            return canPartition(nums, visited, 0, k - 1, 0, target);
        for (int i = start_index; i < nums.length; i++) {
            if (visited[i] == 0) {
                visited[i] = 1;
                if (canPartition(nums, visited, i + 1, k, cur_sum + nums[i], target)) return true;
                //如果使用nums[i]这步不能最终让canPartition返回true，那么就恢复visit状态
                visited[i] = 0;
            }
        }
        return false;
    }

    /**
     * 20201011review, 今天的每日一题；solutions都是bottom up;，我用top down, 思路很简单，找subsets sum，写了十几分钟，两个WA。
     */
    public boolean canPartition(int[] A) {
        int sum = 0, n = A.length;
        if (n == 0) return false;
        for (int i : A) sum += i;
        if (sum % 2 == 1) return false;
        int half = sum / 2;
        Arrays.sort(A);
        if (A[n - 1] > half) return false;
        return dfs(half, A, new HashMap<>(), n - 1);
    }

    private boolean dfs(int target, int[] A, HashMap<Integer, Boolean> memo, int end) {
        // System.out.println("target is " + target + ", end is " + end);
        if (memo.containsKey(target)) return memo.get(target);
        if (target == 0) {
            return true;
        } else {
            for (int i = end; i >= 0; i--) {
                if (target - A[i] < 0) {
                    // memo.put(target, false); // 已犯错误，这儿写成return false了；其实应该continue，因为前面可能有更小的数。
                    continue;
                }
                if (dfs(target - A[i], A, memo, i - 1)) { // 已犯错误：这儿写成end - 1了
                    memo.put(target, true);
                    return true;
                }
            }
        }
        memo.put(target, false);
        return false;
    }
}
