package twopointers;

/**
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * Example:
 * Input: [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * Note:
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 * 20200228
 */
public class MoveZeroes {
    /**
     * 题意：把所有的0移到nums最后，其他数字相对位置不变。要求O(n)time，inplace
     * 挺好的easy题。。我想了2min竟然没想到。就相当于把非0全部沉底到最前面，最后再补0。swap都不用。
     */
    public void moveZeroes(int[] nums) {
        int pos = 0;
        for (int num : nums) {
            if (num != 0) {
                nums[pos++] = num;
            }
        }
        while (pos < nums.length) nums[pos++] = 0;
    }
}
