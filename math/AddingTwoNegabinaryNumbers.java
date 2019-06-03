package math;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two numbers arr1 and arr2 in base -2, return the result of adding them together.
 * <p>
 * Each number is given in array format:  as an array of 0s and 1s, from most significant bit to least significant bit.  For example, arr = [1,1,0,1] represents the number (-2)^3 + (-2)^2 + (-2)^0 = -3.  A number arr in array format is also guaranteed to have no leading zeros: either arr == [0] or arr[0] == 1.
 * <p>
 * Return the result of adding arr1 and arr2 in the same format: as an array of 0s and 1s with no leading zeros.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr1 = [1,1,1,1,1], arr2 = [1,0,1]
 * Output: [1,0,0,0,0]
 * Explanation: arr1 represents 11, arr2 represents 5, the output represents 16.
 * Note:
 * <p>
 * 1 <= arr1.length <= 1000
 * 1 <= arr2.length <= 1000
 * arr1 and arr2 have no leading zeros
 * arr1[i] is 0 or 1
 * arr2[i] is 0 or 1
 * <p>
 * 20190604
 */
public class AddingTwoNegabinaryNumbers {

    /**
     * 先看一下二进制相加的做法
     */
    public int[] addBinary(int[] arr1, int[] arr2) {
        List<Integer> list = new ArrayList<>();
        int carry = 0, i = arr1.length - 1, j = arr2.length - 1;
        while (i >= 0 || j >= 0 || carry != 0) {
            if (i >= 0) carry += arr1[i--];
            if (j >= 0) carry += arr2[j--];
            list.add(carry & 1);
            carry = (carry >> 1);
        }
        int[] res = new int[list.size()];
        for (int k = 0; k < list.size(); k++) {
            res[k] = list.get(list.size() - k - 1);
        }
        return res;
    }

    /**
     * -2进制的做法，还是每次改变carry的值，跟1017. Convert to Base -2那题一样，电脑会帮你处理原反补码。
     * 最后要处理一下多个0开头的情况。
     */
    public int[] addNegabinary(int[] arr1, int[] arr2) {
        List<Integer> list = new ArrayList<>();
        int carry = 0, i = arr1.length - 1, j = arr2.length - 1;
        while (i >= 0 || j >= 0 || carry != 0) {
            if (i >= 0) carry += arr1[i--];
            if (j >= 0) carry += arr2[j--];
            list.add(carry & 1);
            carry = -(carry >> 1);
        }
        while (list.get(list.size() - 1) == 0) {
            list.remove(list.size() - 1);
        }
        int[] res = new int[list.size()];
        for (int k = 0; k < list.size(); k++) {
            res[k] = list.get(list.size() - k - 1);
        }
        return res;
    }
}
