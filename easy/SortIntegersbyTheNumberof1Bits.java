package easy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given an integer array arr. You have to sort the integers in the array in ascending order by the number of 1's in their binary representation and in case of two or more integers have the same number of 1's you have to sort them in ascending order.
 * <p>
 * Return the sorted array.
 * Example 1:
 * <p>
 * Input: arr = [0,1,2,3,4,5,6,7,8]
 * Output: [0,1,2,4,8,3,5,6,7]
 * Explantion: [0] is the only integer with 0 bits.
 * [1,2,4,8] all have 1 bit.
 * [3,5,6] have 2 bits.
 * [7] has 3 bits.
 * The sorted array by bits is [0,1,2,4,8,3,5,6,7]
 * Example 2:
 * <p>
 * Input: arr = [1024,512,256,128,64,32,16,8,4,2,1]
 * Output: [1,2,4,8,16,32,64,128,256,512,1024]
 * Explantion: All integers have 1 bit in the binary representation, you should just sort them in ascending order.
 * Example 3:
 * <p>
 * Input: arr = [10000,10000]
 * Output: [10000,10000]
 * Example 4:
 * <p>
 * Input: arr = [2,3,5,7,11,13,17,19]
 * Output: [2,3,5,17,7,11,13,19]
 * Example 5:
 * <p>
 * Input: arr = [10,100,1000,10000]
 * Output: [10,100,10000,1000]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= arr.length <= 500
 * 0 <= arr[i] <= 10^4
 */
public class SortIntegersbyTheNumberof1Bits {
    /**
     * 题意：把数组里的数字按照二进制1的个数ascending排序。如果个数相同，按照数字大小ascending排序。
     * 解法：Integer.bitCount(i)可以找1的个数。第二，相同的情况，可以*10000+i把i放到低位来比较(因为0 <= arr[i] <= 10^4)
     */
    public int[] sortByBits(int[] arr) {
        Integer[] a = new Integer[arr.length];
        for (int i = 0; i < a.length; ++i)
            a[i] = arr[i];
        Arrays.sort(a, Comparator.comparing(i -> Integer.bitCount(i) * 10000 + i));
        for (int i = 0; i < a.length; ++i)
            arr[i] = a[i];
        return arr;
    }

    /**
     * 1 liner
     */
    public int[] sortByBits__(int[] arr) {
        return Arrays.stream(arr).boxed().sorted(Comparator.comparingInt(i -> Integer.bitCount(i) * 10000 + i)).mapToInt(i -> i).toArray();
    }

    public int[] sortByBits_(int[] arr) {
        int[][] pairs = new int[arr.length][2];
        int indx = 0;
        for (int i : arr) {
            String s = Integer.toBinaryString(i);
            int cnt = 0;
            for (char c : s.toCharArray()) {
                if (c == '1') cnt++;
            }
            pairs[indx][0] = i;
            pairs[indx][1] = cnt;
            indx++;
        }
        Arrays.sort(pairs, (a, b) -> a[1] - b[1] != 0 ? a[1] - b[1] : a[0] - b[0]);
        indx = 0;
        for (int[] a : pairs) {
            arr[indx++] = a[0];
        }
        return arr;
    }
}
