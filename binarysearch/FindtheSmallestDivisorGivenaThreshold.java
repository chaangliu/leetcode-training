package binarysearch;

/**
 * Given an array of integers nums and an integer threshold, we will choose a positive integer divisor and divide all the array by it and sum the result of the division. Find the smallest divisor such that the result mentioned above is less than or equal to threshold.
 * Each result of division is rounded to the nearest integer greater than or equal to that element. (For example: 7/3 = 3 and 10/2 = 5).
 * It is guaranteed that there will be an answer.
 * Example 1:
 * <p>
 * Input: nums = [1,2,5,9], threshold = 6
 * Output: 5
 * Explanation: We can get a sum to 17 (1+2+5+9) if the divisor is 1.
 * If the divisor is 4 we can get a sum to 7 (1+1+2+3) and if the divisor is 5 the sum will be 5 (1+1+1+2).
 * Example 2:
 * <p>
 * Input: nums = [2,3,5,7,11], threshold = 11
 * Output: 3
 * Example 3:
 * <p>
 * Input: nums = [19], threshold = 5
 * Output: 4
 * Constraints:
 * <p>
 * 1 <= nums.length <= 5 * 10^4
 * 1 <= nums[i] <= 10^6
 * nums.length <= threshold <= 10^6
 * 20191208
 */
public class FindtheSmallestDivisorGivenaThreshold {
    /**
     * 题意：找出一个数，数组中的每个数除以这个数之后向上取整不超过threshold，求这个数的最大值。
     * 看数据范围很容易想到二分搜索，配合Math.ceil。
     */
    public int smallestDivisor(int[] nums, int threshold) {
        int l = 1, r = (int) 1e6;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (!valid(mid, nums, threshold)) l = mid + 1;
            else r = mid;
        }
        return l;
    }

    private boolean valid(int mid, int[] nums, int threshold) {
        int sum = 0;
        for (int num : nums) {
            sum += Math.ceil(num * 1.0 / mid);//已犯错误：忘记* 1.0
            if (sum > threshold) return false;
        }
        return true;
    }

    /**
     * lee的代码，用了一个(i + m - 1) /m 来计算ceil
     */
    public int smallestDivisor_(int[] A, int threshold) {
        int left = 1, right = (int) 1e6;
        while (left < right) {
            int m = (left + right) / 2, sum = 0;
            for (int i : A)
                sum += (i + m - 1) / m;
            if (sum > threshold)
                left = m + 1;
            else
                right = m;
        }
        return left;
    }
}
