package dp;

/**
 * Given an array nums of integers, we need to find the maximum possible sum of elements of the array such that it is divisible by three.
 * Example 1:
 * Input: nums = [3,6,5,1,8]
 * Output: 18
 * Explanation: Pick numbers 3, 6, 1 and 8 their sum is 18 (maximum sum divisible by 3).
 * Example 2:
 * Input: nums = [4]
 * Output: 0
 * Explanation: Since 4 is not divisible by 3, do not pick any number.
 * Example 3:
 * Input: nums = [1,2,3,4,4]
 * Output: 12
 * Explanation: Pick numbers 1, 3, 4 and 4 their sum is 12 (maximum sum divisible by 3).
 * Constraints:
 * 1 <= nums.length <= 4 * 10^4
 * 1 <= nums[i] <= 10^4
 * 20191119
 */
public class GreatestSumDivisiblebyThree {
    /**
     * 题意：求最大的能被3整除的子数组的sum。
     * 解法：如果total sum不能被3整除，说明它会余1或者2；那么我们只要维护余1和2最小的两个数，从sum减去相应的即可。
     * 重点是，对于每个数，如果它余1，那么它加上当前最小的余1的数可能会更新当前最小的余2的数。
     **/
    public int maxSumDivThree(int[] nums) {
        int sum = 0, mod1 = 10000, mod2 = 10000;
        for (int n : nums) {
            sum += n;
            if (n % 3 == 1) {
                mod2 = Math.min(mod2, mod1 + n);//要先更新mod2，否则mod1会污染mod2
                mod1 = Math.min(mod1, n);
            } else if (n % 3 == 2) {
                mod1 = Math.min(mod1, mod2 + n);
                mod2 = Math.min(mod2, n);
            }
        }
        return sum % 3 == 0 ? sum : sum % 3 == 1 ? sum - mod1 : sum - mod2;
    }

    /**
     * 下面是lee的dp做法，太秀
     * 维护余0，1，2的最大sum。对于每个数，把它加入进来后现有的sum必然会更新余0，1，2的数的最大值中的某一个。
     * a = 1, i = 0
     * dp2[0 + 1] = max(dp[0 + 1], dp[0] + 1)
     * a = 1, i = 2
     * dp2[2 + 1] = max(dp[2 + 1], dp[2] + 1)
     */
    public int maxSumDivThree__DP(int[] A) {
        int[] dp = new int[]{0, Integer.MIN_VALUE, Integer.MIN_VALUE};
        for (int a : A) {
            int[] dp2 = new int[3];
            for (int i = 0; i < 3; ++i)
                dp2[(i + a) % 3] = Math.max(dp[(i + a) % 3], dp[i] + a);
            dp = dp2;
        }
        return dp[0];
    }
}
