package math;

/**
 * Given an integer array nums, return the sum of divisors of the integers in that array that have exactly four divisors.
 * If there is no such integer in the array, return 0.
 * Example 1:
 * Input: nums = [21,4,7]
 * Output: 32
 * Explanation:
 * 21 has 4 divisors: 1, 3, 7, 21
 * 4 has 3 divisors: 1, 2, 4
 * 7 has 2 divisors: 1, 7
 * The answer is the sum of divisors of 21 only.
 * Constraints:
 * 1 <= nums.length <= 10^4
 * 1 <= nums[i] <= 10^5
 * 20200324
 */
public class FourDivisors {
    /**
     * 题意：给你一个数组，让你在里面找出所有只有4个因子的数的因子之和。
     * 这题要注意，其实完全平方数也可以有超过3个因子，比如16，81，就有5个因子。所以要i*i<=num
     */
    public int sumFourDivisors(int[] nums) {
        int res = 0;
        for (int num : nums) {
            int lastD = 0;
            for (int i = 2; i * i <= num; i++) {
                if (num % i == 0) {
                    if (lastD == 0) {
                        lastD = num / i;
                    } else {
                        lastD = 0;
                        break;
                    }
                }
            }
            if (lastD != 0 && lastD * lastD != num) res += 1 + num + lastD + num / lastD;
        }
        return res;
    }
}
