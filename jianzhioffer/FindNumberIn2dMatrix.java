package jianzhioffer;

/**
 * 1234
 * 2345
 * 3456
 * 4567
 * <p>
 * 思路
 * 矩阵是有序的，从左下角来看，向上数字递减，向右数字递增，
 * 因此从左下角开始查找，当要查找数字比左下角数字大时。右移
 * 要查找数字比左下角数字小时，上移
 * <p>
 * 7,[
 * [1,2,8,9],
 * [2,4,9,12],
 * [4,7,10,13],
 * [6,8,11,15]
 * ]
 */

public class FindNumberIn2dMatrix {
    public boolean Find(int target, int[][] array) {
        if (array == null || array.length == 0 || array[0].length == 0) return false;
        //左下角(array[length - 1][0])
        int start_x = array.length - 1;
        int start_y = 0;
        while (start_x >= 0 && start_y < array.length) {
            if (array[start_x][start_y] == target) return true;
            if (array[start_x][start_y] < target) {
                start_y++;
            } else {
                start_x--;
            }
        }
        return false;
    }
}
