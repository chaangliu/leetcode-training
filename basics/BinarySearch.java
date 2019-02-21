package basics;

/**
 * Created by DrunkPiano on 2016/12/29.
 */

public class BinarySearch {

    private static int search(int[] nums, int target) {
     return binarySearch(nums, target, 0, nums.length - 1);
    }

    private static int binarySearch(int[] nums, int target, int start, int end) {
        int mid = -1;
        while (start <= end-1) {
            mid = (start + end) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return mid;
    }

    public static void main(String args[]) {
        int [] nums = {1,2,2,2,3};
        System.out.println(search(nums,2));
    }
}
