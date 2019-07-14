package easy;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.
 * <p>
 * Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2.  Elements that don't appear in arr2 should be placed at the end of arr1 in ascending order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 * Output: [2,2,2,1,4,3,3,9,6,7,19]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * arr1.length, arr2.length <= 1000
 * 0 <= arr1[i], arr2[i] <= 1000
 * Each arr2[i] is distinct.
 * Each arr2[i] is in arr1.
 * 20190714
 */
public class RelativeSortArray {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr1.length; i++) {
            if (!map.containsKey(arr1[i])) map.put(arr1[i], 0);
            map.put(arr1[i], map.get(arr1[i]) + 1);
        }
        int[] res = new int[arr1.length];
        int index = 0;
        for (int i = 0; i < arr2.length; i++) {
            if (map.containsKey(arr2[i])) {
                int n = map.remove(arr2[i]);
                for (int j = 0; j < n; j++) {
                    res[index++] = arr2[i];
                }
            }
        }
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(((o1, o2) -> o1.getKey() - o2.getKey()));
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            queue.offer(entry);
        }
        while (!queue.isEmpty()) {
            Map.Entry<Integer, Integer> entry = queue.poll();
            for (int i = 0; i < entry.getValue(); i++) {
                res[index++] = entry.getKey();
            }
        }
        return res;
    }
}
