package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Given an array of integers arr and an integer k.
 * <p>
 * A value arr[i] is said to be stronger than a value arr[j] if |arr[i] - m| > |arr[j] - m| where m is the median of the array.
 * If |arr[i] - m| == |arr[j] - m|, then arr[i] is said to be stronger than arr[j] if arr[i] > arr[j].
 * <p>
 * Return a list of the strongest k values in the array. return the answer in any arbitrary order.
 * <p>
 * Median is the middle value in an ordered integer list. More formally, if the length of the list is n, the median is the element in position ((n - 1) / 2) in the sorted list (0-indexed).
 * <p>
 * For arr = [6, -3, 7, 2, 11], n = 5 and the median is obtained by sorting the array arr = [-3, 2, 6, 7, 11] and the median is arr[m] where m = ((5 - 1) / 2) = 2. The median is 6.
 * For arr = [-7, 22, 17, 3], n = 4 and the median is obtained by sorting the array arr = [-7, 3, 17, 22] and the median is arr[m] where m = ((4 - 1) / 2) = 1. The median is 3.
 * Example 1:
 * <p>
 * Input: arr = [1,2,3,4,5], k = 2
 * Output: [5,1]
 * Explanation: Median is 3, the elements of the array sorted by the strongest are [5,1,4,2,3]. The strongest 2 elements are [5, 1]. [1, 5] is also accepted answer.
 * Please note that although |5 - 3| == |1 - 3| but 5 is stronger than 1 because 5 > 1.
 * Example 2:
 * <p>
 * Input: arr = [1,1,3,5,5], k = 2
 * Output: [5,5]
 * Explanation: Median is 3, the elements of the array sorted by the strongest are [5,5,1,1,3]. The strongest 2 elements are [5, 5].
 * Example 3:
 * <p>
 * Input: arr = [6,7,11,7,6,8], k = 5
 * Output: [11,8,6,6,7]
 * Explanation: Median is 7, the elements of the array sorted by the strongest are [11,8,6,6,7,7].
 * Any permutation of [11,8,6,6,7] is accepted.
 * Example 4:
 * <p>
 * Input: arr = [6,-3,7,2,11], k = 3
 * Output: [-3,11,2]
 * Example 5:
 * <p>
 * Input: arr = [-7,22,17,3], k = 2
 * Output: [22,17]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= arr.length <= 10^5
 * -10^5 <= arr[i] <= 10^5
 * 1 <= k <= arr.length
 * 20200607
 */
public class ThekStrongestValuesinanArray {
    /**
     * 题意：如果|arr[i] - m| > |arr[j] - m| ，那么就说arr[i]比arr[j]强；否则如果arr[i] > arr[j]，那么arr[i]比arr[j]强。
     * 解法：我直接利用了sort的param，没想到可以自己实现。。
     * 好的解法，two pointers, O(n log k)
     */
    public int[] getStrongest(int[] arr, int k) {
        Arrays.sort(arr);
        int i = 0, j = arr.length - 1, p = 0;
        int median = arr[(arr.length - 1) / 2];
        int[] res = new int[k];
        while (p < k)
            if (median - arr[i] > arr[j] - median)
                res[p++] = arr[i++];
            else
                res[p++] = arr[j--];
        return res;
    }

    /**
     * 我的写法, O(n log n)
     */
    public int[] getStrongest_(int[] arr, int k) {
        Arrays.sort(arr);
        int m = arr[(arr.length - 1) / 2];
        ArrayList list = new ArrayList();
        for (int i = 0; i < arr.length; i++) list.add(arr[i]);
        Collections.sort(list, (a, b) -> {
            if (Math.abs((int) a - m) != Math.abs((int) b - m)) {
                return Math.abs((int) b - m) - Math.abs((int) a - m);
            } else {
                return (int) b - (int) a;
            }
        });
        int[] res = new int[k];
        for (int i = 0; i < k && i < list.size(); i++) {
            res[i] = (int) list.get(i);
        }
        return res;
    }
}
