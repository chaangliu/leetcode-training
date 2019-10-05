package easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given three integer arrays arr1, arr2 and arr3 sorted in strictly increasing order, return a sorted array of only the integers that appeared in all three arrays.
 * Example 1:
 * <p>
 * Input: arr1 = [1,2,3,4,5], arr2 = [1,2,5,7,9], arr3 = [1,3,4,5,8]
 * Output: [1,5]
 * Explanation: Only 1 and 5 appeared in the three arrays.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= arr1.length, arr2.length, arr3.length <= 1000
 * 1 <= arr1[i], arr2[i], arr3[i] <= 2000
 * 20191006
 */
public class IntersectionofThreeSortedArrays {
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : arr1) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        for (int n : arr2) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        for (int n : arr3) {
            if (map.containsKey(n) && map.get(n) == 2) res.add(n);
        }
        return res;
    }
}
