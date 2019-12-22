package easy;

/**
 * Given an array nums of integers, return how many of them contain an even number of digits.
 Example 1:
 Input: nums = [12,345,2,6,7896]
 Output: 2
 Explanation:
 12 contains 2 digits (even number of digits).
 345 contains 3 digits (odd number of digits).
 2 contains 1 digit (odd number of digits).
 6 contains 1 digit (odd number of digits).
 7896 contains 4 digits (even number of digits).
 Therefore only 12 and 7896 contain an even number of digits.
 Example 2:
 Input: nums = [555,901,482,1771]
 Output: 1
 Explanation:
 Only 1771 contains an even number of digits.
 Constraints:
 1 <= nums.length <= 500
 1 <= nums[i] <= 10^5
 20191222
 */
public class FindNumberswithEvenNumberofDigits {
    /**
     * 题意：找出数组中有偶数位数的数字个数。
     */
    public int findNumbers(int[] nums) {
        int res = 0;
        for(int n : nums){
            int cnt = 0;
            while(n > 0) {
                n /= 10;
                cnt ++;
            }
            if ((cnt & 1) == 0 ) res ++;
        }
        return res;
    }

    /**
     * 也可以转化成string
     */
    public int findNumbers_(int[] nums) {
        int cnt = 0;
        for (int n : nums)
            cnt += 1 - Integer.toString(n).length() % 2;
        return cnt;
    }
}
