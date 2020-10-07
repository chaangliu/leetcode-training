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
     * 题意：荷兰国旗问题；一个字符串里有乱序排列的0，1，2；把0，1，2 in place排序。
     * 最好的方法：双指针; low,high指向0，2；A[i] == 2的时候不需要i++
     * 为什么A[i]等于2的时候不需要i++，因为把2换到后面之后 ，A[i]并不一定就是正确的数字；而A[i]是0或1的时候都可以move on了。
     * [2,0,2,1,1,0]
     */
    public void sortColors_(int[] A) {
        if (A == null || A.length < 2) return;
        int low = 0;
        int high = A.length - 1;
        for (int i = low; i <= high; ) {
            if (A[i] == 0) {
                // swap A[i] and A[low] and i,low both ++
                swap(i++, low++, A);
            } else if (A[i] == 2) {
                // swap A[i] and A[high] and high--;
                // 为什么A[i]等于2的时候不需要i++，因为把2换到后面之后 ，A[i]并不一定就是正确的数字；而A[i]是0或1的时候都可以move on了。
                swap(i, high--, A);
            } else {
                i++;
            }
        }
    }

    private void swap(int a, int b, int[] A) {
        int tmp = A[a];
        A[a] = A[b];
        A[b] = tmp;
    }
}
