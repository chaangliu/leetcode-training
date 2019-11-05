package easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an initial array arr, every day you produce a new array using the array of the previous day.
 * On the i-th day, you do the following operations on the array of day i-1 to produce the array of day i:
 * If an element is smaller than both its left neighbor and its right neighbor, then this element is incremented.
 * If an element is bigger than both its left neighbor and its right neighbor, then this element is decremented.
 * The first and last elements never change.
 * After some days, the array does not change. Return that final array.
 * Example 1:
 * Input: arr = [6,2,3,4]
 * Output: [6,3,3,4]
 * Explanation:
 * On the first day, the array is changed from [6,2,3,4] to [6,3,3,4].
 * No more operations can be done to this array.
 * Example 2:
 * Input: arr = [1,6,3,4,3,5]
 * Output: [1,4,4,4,4,5]
 * Explanation:
 * On the first day, the array is changed from [1,6,3,4,3,5] to [1,5,4,3,4,5].
 * On the second day, the array is changed from [1,5,4,3,4,5] to [1,4,4,4,4,5].
 * No more operations can be done to this array.
 * Constraints:
 * 1 <= arr.length <= 100
 * 1 <= arr[i] <= 100
 * 20191105
 */
public class ArrayTransformation {
    /**
     * 题意：给你一个数组，如果中间的数大于两边的，就要减小；小于两边的就要增大。求最后稳定的数组。
     * 双周赛签到题。easy题一般都是没什么套路的，都不属于某一类算法。这题也不例外，模拟一下就行了。这里我借助了clone。
     */
    public List<Integer> transformArray(int[] arr) {
        int[] tmp = arr.clone();
        while (true) {
            boolean changed = false;
            for (int i = 1; i < arr.length - 1; i++) {
                if (arr[i - 1] < arr[i] && arr[i] > arr[i + 1]) {
                    tmp[i]--;
                    changed = true;
                } else if (arr[i - 1] > arr[i] && arr[i] < arr[i + 1]) {
                    tmp[i]++;
                    changed = true;
                }
            }
            arr = tmp.clone();//已犯错误：这里轮换的时候没有clone，指向了同一个引用
            if (!changed) break;
        }
        List<Integer> res = new ArrayList<>();
        for (int num : tmp) res.add(num);
        return res;
    }
}
