package hashtable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an array of integers A with even length, return true if and only if it is possible to reorder it such that A[2 * i + 1] = 2 * A[2 * i] for every 0 <= i < len(A) / 2.
 * Example 1:
 * <p>
 * Input: [3,1,3,6]
 * Output: false
 * Example 2:
 * <p>
 * Input: [2,1,2,6]
 * Output: false
 * Example 3:
 * <p>
 * Input: [4,-2,2,-4]
 * Output: true
 * Explanation: We can take two groups, [-2,-4] and [2,4] to form [-2,-4,2,4] or [2,4,-2,-4].
 * Example 4:
 * <p>
 * Input: [1,2,4,16,8,4]
 * Output: false
 * Note:
 * <p>
 * 0 <= A.length <= 30000
 * A.length is even
 * -100000 <= A[i] <= 100000
 * 20200514
 */
public class ArrayofDoubledPairs {
    /**
     * 题意：给你一个数组，问能不能两两凑对，使得一个是另一个的两倍。
     * 解法：我看了Tag是Hashtable，就用了set。后来遇到一个case[0,0,0,0]，就用了一个map记录数字的index这样。
     */
    public boolean canReorderDoubled(int[] A) {
        HashMap<Integer, Queue<Integer>> map = new HashMap<>();
        HashSet<Integer> visited = new HashSet<>();
        Arrays.sort(A);
        for (int i = 0; i < A.length; i++) {
            map.putIfAbsent(A[i], new LinkedList<>());
            map.get(A[i]).add(i);
        }
        for (int i = 0; i < A.length; i++) {
            if (visited.contains(i)) continue;
            int target = A[i] < 0 ? A[i] / 2 : A[i] * 2;
            if (!map.containsKey(target)) return false;
            visited.add(i);
            if (map.containsKey(A[i]) && map.get(A[i]).size() > 0) map.get(A[i]).poll();
            if (map.get(target).size() == 0) return false;
            visited.add(map.get(target).poll());
            if (map.containsKey(target) && map.get(target).size() <= 0) map.remove(target);

            if (map.containsKey(A[i]) && map.get(A[i]).size() <= 0) map.remove(A[i]);
        }
        return true;
    }
}
