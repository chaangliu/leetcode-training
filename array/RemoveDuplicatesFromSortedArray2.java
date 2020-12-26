package array;

/**
 * Follow up for "Remove Duplicates":
 * What if duplicates are allowed at most twice?
 * <p>
 * For example,
 * Given sorted array nums = [1,1,1,2,2,3],
 * <p>
 * Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the new length.
 * Created by DrunkPiano on 2017/2/9.
 */

public class RemoveDuplicatesFromSortedArray2 {

    /**
     * 题意：给你一个sorted array，让你把多余2个连续的数字都删掉。返回最终数组的长度。
     * 解法：two pointers覆盖法。
     */
        public int removeDuplicates(int[] nums) {
            int j = 1, count = 1; // Initialize the counter and the second pointer.
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] == nums[i - 1]) {
                    count++;
                } else {
                    count = 1; // Reset the count since we encountered a different element than the previous one.
                }
                if (count <= 2) {
                    nums[j++] = nums[i];
                }
            }
            return j;
        }



    public static void main(String args[]) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        RemoveDuplicatesFromSortedArray2 removeDuplicatesFromSortedArray2 = new RemoveDuplicatesFromSortedArray2();
        System.out.println(removeDuplicatesFromSortedArray2.removeDuplicates(nums));
    }
}
