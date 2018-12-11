package jianzhioffer;

import java.util.ArrayList;

/**
 * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。
 * 输出描述:
 * 对应每个测试案例，输出两个数，小的先输出。
 */
public class SortedTwoSum {

    //这题跟two sum的区别就是这题是sorted array，所以可以用two pointers
    public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        ArrayList<Integer> res = new ArrayList<>();
        if (array == null || array.length == 0) return res;
        int left = 0, right = array.length - 1;
        while (left < right) {
            int tmp = array[left] + array[right];
            if (tmp < sum) {
                left++;
            } else if (tmp > sum) {
                right--;
            } else {
                res.add(array[left]);
                res.add(array[right]);
                break;
            }
        }
        return res;
    }
}
