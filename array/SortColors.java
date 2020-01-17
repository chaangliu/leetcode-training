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
    /**
     * 题意：一个字符串里有乱序排列的0，1，2；把0，1，2 in place排序。
     * 解法：这种要求in place的题一般就是swap。这题的技巧是，
     */
    public void sortColors(int[] A) {
        int left = 0, right = A.length - 1, n = A.length;
        for (int i = 0; i <= right; i++) {

            // 如果先把0换到前面去，过不了case：[1,2,0] -> [1,0,2], 挺难想的，大致就是因为是从左往右遍历的
            while (A[i] == 2 && i < right) {
                swap(right, i, A);
                right--;
            }
            while (A[i] == 0 && i > left) {
                swap(left, i, A);
                left++;
            }
        }
    }

    private void swap(int a, int b, int[] A) {
        int tmp = A[a];
        A[a] = A[b];
        A[b] = tmp;
    }

    /**
     * 第二种方法，i是iterator，low,high指向0，2
     */
    public void sortColors_(int[] A) {
        if (A == null || A.length < 2) return;
        int low = 0;
        int high = A.length - 1;
        for (int i = low; i <= high; ) {
            if (A[i] == 0) {
                // swap A[i] and A[low] and i,low both ++
                int temp = A[i];
                A[i] = A[low];
                A[low] = temp;
                i++;
                low++;
            } else if (A[i] == 2) {
                //swap A[i] and A[high] and high--;
                int temp = A[i];
                A[i] = A[high];
                A[high] = temp;
                high--;
            } else {
                i++;
            }
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

