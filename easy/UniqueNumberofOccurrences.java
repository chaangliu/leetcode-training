package easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given an array of integers arr, write a function that returns true if and only if the number of occurrences of each value in the array is unique.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [1,2,2,1,1,3]
 * Output: true
 * Explanation: The value 1 has 3 occurrences, 2 has 2 and 3 has 1. No two values have the same number of occurrences.
 * Example 2:
 * <p>
 * Input: arr = [1,2]
 * Output: false
 * Example 3:
 * <p>
 * Input: arr = [-3,0,1,-3,1,1,1,-3,10,0]
 * Output: true
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= arr.length <= 1000
 * -1000 <= arr[i] <= 1000
 * 20190929
 */
public class UniqueNumberofOccurrences {
    /**
     * 这题是问出现次数不重复的数字有多少个，我用了两个set。
     */
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> numsSet = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
            numsSet.add(arr[i]);
        }
        Set<Integer> freqSet = new HashSet<>(map.values());
        return freqSet.size() == numsSet.size();
    }
}
