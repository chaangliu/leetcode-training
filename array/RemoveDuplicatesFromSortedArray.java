package array;

/**
 * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
 * Created by DrunkPiano on 2016/12/4.
 */

public class RemoveDuplicatesFromSortedArray {

    /**
     * 题意：inplace地将有序数组内的重复数字去掉，要求space是O(1)
     * 这题算是two pointers，有两种思路，一种是slow指针指向下一个非重复数字的slot，fast指针去找一个不重复的数字。
     * 另一种思路跟这个相反，i遍历过程中，统计一个有多少个重复的数字cnt，然后i - cnt就是下一个应该放置不重复数字的地方
     */
    public int removeDuplicates(int[] nums) {
        int result = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] != nums[i]) {
                result++;
                nums[result] = nums[i];
            }
        }
        return result;
    }

    public int removeDuplicates_(int[] A) {
        int count = 0, n = A.length;
        for (int i = 1; i < n; i++) {
            if (A[i] == A[i - 1]) count++;
            else A[i - count] = A[i];
        }
        return n - count;
    }

    public static void main(String args) {

    }
}
