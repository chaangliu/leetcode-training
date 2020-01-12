package easy;

import java.util.ArrayList;

/**
 * We are given a list nums of integers representing a list compressed with run-length encoding.
 * Consider each adjacent pair of elements [a, b] = [nums[2*i], nums[2*i+1]] (with i >= 0).  For each such pair, there are a elements with value b in the decompressed list.
 * Return the decompressed list.
 * Example 1:
 * Input: nums = [1,2,3,4]
 * Output: [2,4,4,4]
 * Constraints:
 * 2 <= nums.length <= 100
 * nums.length % 2 == 0
 * 1 <= nums[i] <= 100
 * 20200112
 */
public class DecompressRunLengthEncodedList {
    /**
     * 题意：按规律输出字符串。签到题。类似count and say。
     */
    public int[] decompressRLElist(int[] nums) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i += 2) {
            int cnt = nums[i];
            int num = nums[i + 1];
            for (int j = 0; j < cnt; j++) {
                res.add(num);
            }
        }
        int[] ret = new int[res.size()];
        int idx = 0;
        for (int i : res) {
            ret[idx++] = i;
        }
        return ret;
    }
}
