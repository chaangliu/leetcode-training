package easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * You are given an array colors, in which there are three colors: 1, 2 and 3.
 * <p>
 * You are also given some queries. Each query consists of two integers i and c, return the shortest distance between the given index i and the target color c. If there is no solution return -1.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: colors = [1,1,2,1,3,2,2,3,3], queries = [[1,3],[2,2],[6,1]]
 * Output: [3,0,3]
 * Explanation:
 * The nearest 3 from index 1 is at index 4 (3 steps away).
 * The nearest 2 from index 2 is at index 2 itself (0 steps away).
 * The nearest 1 from index 6 is at index 3 (3 steps away).
 * Example 2:
 * <p>
 * Input: colors = [1,2], queries = [[0,3]]
 * Output: [-1]
 * Explanation: There is no 3 in the array.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= colors.length <= 5*10^4
 * 1 <= colors[i] <= 3
 * 1 <= queries.length <= 5*10^4
 * queries[i].length == 2
 * 0 <= queries[i][0] < colors.length
 * 1 <= queries[i][1] <= 3
 * 20190908
 */
public class BeforeandAfterPuzzle {
    /**
     * 这题也没什么意思，又臭又长，又没有机巧，就是字符串处理
     */
    public List<String> beforeAndAfterPuzzles(String[] phrases) {
        Map<String, ArrayList<String>> startWith = new HashMap<>();
        Map<String, ArrayList<String>> endWith = new HashMap<>();
        for (String p : phrases) {
            String[] arr = p.split(" ");
            int len = arr.length;
            if (!startWith.containsKey(arr[0])) startWith.put(arr[0], new ArrayList<>());
            if (!endWith.containsKey(arr[len - 1])) endWith.put(arr[len - 1], new ArrayList<>());
            startWith.get(arr[0]).add(p);
            endWith.get(arr[len - 1]).add(p);
        }
        HashSet<String> res = new HashSet<>();
        for (String key : endWith.keySet()) {
            if (startWith.containsKey(key)) {
                ArrayList<String> startList = startWith.get(key);
                ArrayList<String> endList = endWith.get(key);
                for (String s : startList) {
                    for (String e : endList) {
                        //if (s.equals(e)) continue;
                        //"a bad", "bad day"
                        int cnt = 0;
                        if (s.equals(e)) {
                            for (String ph : phrases) {
                                if (ph.equals(s)) cnt++;
                            }
                        }
                        if (!s.equals(e) || cnt > 1)
                            res.add(e.substring(0, e.length() - key.length()) + s);
                    }
                }
            }
        }
        ArrayList<String> ret = new ArrayList<>(res);
        Collections.sort(ret);
        return ret;
    }
}
