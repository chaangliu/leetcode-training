package array;

/**
 * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 * <p>
 * You may assume no duplicates in the array.
 * <p>
 * Here are few examples.
 * [1,3,5,6], 5 → 2
 * [1,3,5,6], 2 → 1
 * [1,3,5,6], 7 → 4
 * [1,3,5,6], 0 → 0
 * Created by DrunkPiano on 2016/12/29.
 */

public class SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        if (nums[0] >= target) return 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == nums.length - 1) return nums.length;
            if (nums[i] < target && nums[i + 1] >= target) {
                return i + 1;
            }
        }
        return -1;
    }
}
