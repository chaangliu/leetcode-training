package array;

/**
 * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
 * <p>
 * <p>
 * Created by DrunkPiano on 2016/12/4.
 */

public class RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums) {
        int result = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] != nums[i]) {
                result++;
                nums[result] = nums[i];
//                nums[result++] = nums[i];
            }
        }
        return result;
    }

    public static void main(String args){

    }
}
