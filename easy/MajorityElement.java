package easy;

/**
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 * You may assume that the array is non-empty and the majority element always exist in the array.
 * Example 1:
 * Input: [3,2,3]
 * Output: 3
 * Example 2:
 * Input: [2,2,1,1,1,2,2]
 * Output: 2
 * 20200214
 */
public class MajorityElement {
    /**
     * 题意：找出出现次数超过1/2的那个number
     * 解法：摩尔投票法
     */
    public int majorityElement(int[] nums) {
        int res = nums[0], cnt = 0;
        for (int n : nums) {
            if (n == res) cnt++;
            else cnt--;
            if (cnt == 0) {
                res = n;
                cnt++;
            }
        }
        return res;
    }
}
