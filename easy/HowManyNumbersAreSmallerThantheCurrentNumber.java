package easy;

import java.util.Arrays;

/**
 * Given the array nums, for each nums[i] find out how many numbers in the array are smaller than it. That is, for each nums[i] you have to count the number of valid j's such that j != i and nums[j] < nums[i].
 * Return the answer in an array.
 * Example 1:
 * Input: nums = [8,1,2,2,3]
 * Output: [4,0,1,1,3]
 * Explanation:
 * For nums[0]=8 there exist four smaller numbers than it (1, 2, 2 and 3).
 * For nums[1]=1 does not exist any smaller number than it.
 * For nums[2]=2 there exist one smaller number than it (1).
 * For nums[3]=2 there exist one smaller number than it (1).
 * For nums[4]=3 there exist three smaller numbers than it (1, 2 and 2).
 * Example 2:
 * <p>
 * Input: nums = [6,5,4,8]
 * Output: [2,1,0,3]
 * Example 3:
 * <p>
 * Input: nums = [7,7,7,7]
 * Output: [0,0,0,0]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= nums.length <= 500
 * 0 <= nums[i] <= 100
 */
public class HowManyNumbersAreSmallerThantheCurrentNumber {
    /**
     * 题意：计算出小于当前数的数字有多少。
     * 解法：sort，或者map
     */
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] A = nums.clone();
        Arrays.sort(A);
        int n = nums.length;
        int[] res = new int[n];
        int index = 0;
        for (int num : nums) {
            for (int i = 0; i < A.length; i++) {
                if (A[i] >= num) {
                    res[index++] = i;
                    break;
                }
            }
        }
        return res;
    }
}
