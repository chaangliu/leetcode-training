package easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Given an array of integers arr, replace each element with its rank.
 * The rank represents how large the element is. The rank has the following rules:
 * Rank is an integer starting from 1.
 * The larger the element, the larger the rank. If two elements are equal, their rank must be the same.
 * Rank should be as small as possible.
 * Example 1:
 * <p>
 * Input: arr = [40,10,20,30]
 * Output: [4,1,2,3]
 * Explanation: 40 is the largest element. 10 is the smallest. 20 is the second smallest. 30 is the third smallest.
 * Example 2:
 * Input: arr = [100,100,100]
 * Output: [1,1,1]
 * Explanation: Same elements share the same rank.
 * Example 3:
 * Input: arr = [37,12,28,9,100,56,80,5,12]
 * Output: [5,3,4,2,8,6,7,1,3]
 * Constraints:
 * <p>
 * 0 <= arr.length <= 105
 * -109 <= arr[i] <= 109
 * 20200126
 */
public class RankTransformofanArray {
    /**
     * 题意：把array中数字的大小进行排列，输出对应的大小。
     */
    public int[] arrayRankTransform(int[] arr) {
        HashSet<Integer> set = new HashSet<>();
        for (int i : arr) set.add(i);
        ArrayList<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            map.put(list.get(i), i + 1);
        }
        int[] res = new int[arr.length];
        int i = 0;
        for (int num : arr) {
            res[i++] = map.get(num);
        }
        return res;
    }
}
