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
//    public int removeDuplicates(int[] nums) {
//        Map<Integer , Integer> map = new HashMap<>();
//        int result = 1;
////        int currentAppearance = 0;
//        for (int i = 1; i < nums.length; i++) {
//            if (nums[i] != nums[i - 1]) {
//                nums[result] = nums[i];
//                result++;
//            } else if (nums[i] == nums[i - 1] && !map.containsKey(nums[i])) {
//                nums[result] = nums[i];
//                result++;
//                map.put(nums[i], 1);
//            }
//        }
//        return result;
//    }

    public int removeDuplicates(int[] nums) {
        int result = 1;
        int occur = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                if (occur >= 1) {
                    occur++;
                } else {
                    occur++;
                    nums[result] = nums[i];
                    result++;
                }
            } else {
                //这一步是把occur重新置0
                occur = 0;
                nums[result] = nums[i];
                result++;
            }
        }
        return result;
    }

    public static void main(String args[]) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        RemoveDuplicatesFromSortedArray2 removeDuplicatesFromSortedArray2 = new RemoveDuplicatesFromSortedArray2();
        System.out.println(removeDuplicatesFromSortedArray2.removeDuplicates(nums));
    }
}
