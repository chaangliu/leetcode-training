package easy;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Given an array of integers A, return the largest integer that only occurs once.
 * <p>
 * If no integer occurs once, return -1.
 * <p>
 * Example 1:
 * <p>
 * Input: [5,7,3,9,4,9,8,3,1]
 * Output: 8
 * Explanation:
 * The maximum integer in the array is 9 but it is repeated. The number 8 occurs only once, so it's the answer.
 * Example 2:
 * <p>
 * Input: [9,9,8,8]
 * Output: -1
 * Explanation:
 * There is no number that occurs only once.
 * Note:
 * <p>
 * 1 <= A.length <= 2000
 * 0 <= A[i] <= 1000
 * 20190728
 */
public class LargestUniqueNumber {
    public int largestUniqueNumber(int[] A) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : A) {
            if (!map.containsKey(num)) map.put(num, 0);
            map.put(num, map.get(num) + 1);
        }
        PriorityQueue<HashMap.Entry<Integer, Integer>> queue = new PriorityQueue<>((e1, e2) ->
                e2.getKey() - e1.getKey()
        );
        for (HashMap.Entry entry : map.entrySet()) {
            queue.add(entry);
        }
        while (!queue.isEmpty()) {
            HashMap.Entry<Integer, Integer> entry = queue.poll();
            if (entry.getValue() == 1) return entry.getKey();
        }
        return -1;
    }
}
