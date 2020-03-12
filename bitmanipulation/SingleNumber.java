package bitmanipulation;

/**
 * Given a non-empty array of integers, every element appears twice except for one. Find that single one.
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * Example 1:
 * Input: [2,2,1]
 * Output: 1
 * Example 2:
 *
 * Input: [4,1,2,1,2]
 * Output: 4
 * 20200204
 */
public class SingleNumber {
    /**
     * 题意：一个array里面除了一个数字出现了一次之外其他每个数字都出现了两次，找出出现一次的数。
     * 解法：异或，0异或任何数都等于原来的数。
     */
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int n : nums){
            res ^= n;
        }
        return res;
    }
}
