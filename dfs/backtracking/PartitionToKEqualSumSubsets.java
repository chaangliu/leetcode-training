package dfs.backtracking;

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

    //    public boolean canPartitionKSubsets(int[] nums, int k) {
//        if (nums == null || nums.length < k) return false;
//        int sum = 0;
//        for (int n : nums) {
//            sum += n;
//        }
//        if (sum % k != 0) return false;
//        return helper(nums, sum / k, new boolean[nums.length], 0, k);
//    }
//
//    private boolean helper(int[] nums, int target, boolean[] used, int index, int k) {
//        if (target == 0) return true;
//        if (k == 0) return true;
//        if (used[index]) return false;
//        used[index] = true;
//        helper(nums, target - nums[k], used, index + 1, k - 1);
//        used[index] = false;
//        return false;
//    }
}
