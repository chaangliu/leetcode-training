package dp.dfswithmemo;

/**
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
 * <p>
 * Find the maximum coins you can collect by bursting the balloons wisely.
 * <p>
 * Note:
 * <p>
 * You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * Example:
 * <p>
 * Input: [3,1,5,8]
 * Output: 167
 * Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 * coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 * 20200402
 */
public class BurstBalloons {
    /**
     * 题意：一排气球上面有数字，戳破一个就能得到它左边和它自己和它右边三个数字的乘积这么多分数，戳破之后右边的气球就会移动到左边。问最多可以得到多少分数。
     * 解法：top down dp，我们设想，保留第k个气球，把[l,k-1]和[k+1,r]这些气球都戳破，最后再戳破k, 得分(l-1） * k * (r + 1)
     * 说实话这题在后期算不上hard了。不过，边界处理还是有一定技巧的，比如要先给它两侧加一个1；
     * 另外断开条件是l + 1 == r，也比较有讲究。
     * <p>
     * 这题也可以用bottom up写，三重循环。可以看花花的视频。
     */
    public int maxCoins(int[] nums) {
        int[] arr = new int[nums.length + 2];
        System.arraycopy(nums, 0, arr, 1, nums.length);
        arr[0] = 1;
        arr[arr.length - 1] = 1;
        return dfs(0, arr.length - 1, arr, new int[arr.length][arr.length]);
    }

    /**
     * max points you can score from [l,r]，l和r是不能戳的，因为是虚拟气球，已经临界了
     **/
    private int dfs(int l, int r, int[] nums, int[][] memo) {
        if (l + 1 == r) return 0;
        if (memo[l][r] != 0) return memo[l][r];
        int res = 0;
        for (int i = l + 1; i < r; i++) {
            res = Math.max(res, dfs(l, i, nums, memo) + dfs(i, r, nums, memo) + nums[l] * nums[i] * nums[r]); // 不要写成dfs(l, i - 1, nums , memo)
        }
        memo[l][r] = res;
        return res;
    }
}
