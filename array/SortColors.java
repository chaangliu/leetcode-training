package array;

/**
 * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * Note:
 * You are not suppose to use the library's sort function for this problem.
 * <p>
 * Follow up:
 * A rather straight forward solution is a two-pass algorithm using counting sort.
 * First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
 * Could you come up with an one-pass algorithm using only constant space?
 * <p>
 * Created by DrunkPiano on 2017/2/8.
 */

public class SortColors {
    public void sortColors(int[] nums) {
        int redIndex = 0, blueIndex = nums.length - 1;
        int i = 0;
        int tmp;
        while (i < blueIndex+1) {
            if (nums[i] == 0) {
//                swap(nums[i], nums[redIndex]);
                tmp = nums[redIndex];
                nums[redIndex] = nums[i];
                nums[i] = tmp;
                redIndex++;
                //swap之后，i位置必定是1，所以跟1同样的处理:i++。
                i++;
                continue;
            }
            if (nums[i] == 2) {
//                swap(nums[i], nums[blueIndex]);
                tmp = nums[blueIndex];
                nums[blueIndex] = nums[i];
                nums[i] = tmp;
                blueIndex--;
                continue;
            }
            i++;
        }
    }

    public static void main(String args[]) {
        int a[] = {2};
        SortColors sortColors = new SortColors();
        sortColors.sortColors(a);
    }
//    private void swap(Object a , Object b){
//        Object temp;
//        temp = a ;
//        a = b ;
//        b = temp;
//    }
}

//201021
//101022
//011022

