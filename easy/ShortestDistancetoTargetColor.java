package easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * You are given an array colors, in which there are three colors: 1, 2 and 3.
 * <p>
 * You are also given some queries. Each query consists of two integers i and c, return the shortest distance between the given index i and the target color c. If there is no solution return -1.
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
public class ShortestDistancetoTargetColor {

    /**
     * 看数据量只知道要预处理。
     * 做法就是从左往右扫一遍，然后从右往左扫一遍，找dist from cur_index to 1/2/3的最小距离。
     * 也可以把1，2，3的index分别保存然后二分搜索
     * <p>
     * 这题我写得很长，也可以不用set的。。
     * 另外这题题干要求返回int []，但实际上要返回List<Integer>，不然OJ会报错
     */
    public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        HashMap<Integer, Integer> dist1 = new HashMap<>();//dist from index to 1
        HashMap<Integer, Integer> dist2 = new HashMap<>();
        HashMap<Integer, Integer> dist3 = new HashMap<>();
        Set<Integer> need1 = new HashSet<>();
        Set<Integer> need2 = new HashSet<>();
        Set<Integer> need3 = new HashSet<>();
        for (int i = 0; i < colors.length; i++) {
            if (colors[i] == 1) {
                dist1.put(i, 0);
                need2.add(i);//第i位(1)需要找2
                need3.add(i);//第i位需要找3
                for (int idx : need1) {
                    dist1.put(idx, i - idx);//更新从idx到右边最近的1的距离
                }
                need1.clear();
            } else if (colors[i] == 2) {
                dist2.put(i, 0);
                need1.add(i);//第i位需要找1
                need3.add(i);//第i位需要找3
                for (int idx : need2) {
                    dist2.put(idx, i - idx);
                }
                need2.clear();
            }
            if (colors[i] == 3) {
                dist3.put(i, 0);
                need1.add(i);
                need2.add(i);
                for (int idx : need3) {
                    dist3.put(idx, i - idx);
                }
                need3.clear();
            }

        }
        need1.clear();
        need2.clear();
        need3.clear();
        for (int i = colors.length - 1; i >= 0; i--) {
            if (colors[i] == 1) {
                dist1.put(i, 0);
                need2.add(i);//第i位(1)需要找2
                need3.add(i);//第i位需要找3
                for (int idx : need1) {
                    dist1.put(idx, Math.min(idx - i, dist1.getOrDefault(idx, Integer.MAX_VALUE)));//更新从idx到右边最近的1的距离
                }
                need1.clear();
            } else if (colors[i] == 2) {
                dist2.put(i, 0);
                need1.add(i);//第i位需要找1
                need3.add(i);//第i位需要找3
                for (int idx : need2) {
                    dist2.put(idx, Math.min(idx - i, dist2.getOrDefault(idx, Integer.MAX_VALUE)));//更新从idx到右边最近的1的距离
                }
                need2.clear();
            }
            if (colors[i] == 3) {
                dist3.put(i, 0);
                need1.add(i);
                need2.add(i);
                for (int idx : need3) {
                    dist3.put(idx, Math.min(idx - i, dist3.getOrDefault(idx, Integer.MAX_VALUE)));//更新从idx到右边最近的1的距离
                }
                need3.clear();
            }
        }
        //int[] res = new int[queries.length];
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            if (queries[i][1] == 1) {
                //res[i] = dist1.getOrDefault(queries[i][0], -1);
                res.add(dist1.getOrDefault(queries[i][0], -1));
            } else if (queries[i][1] == 2) {
                //res[i] = dist2.getOrDefault(queries[i][0], -1);
                res.add(dist2.getOrDefault(queries[i][0], -1));
            } else if (queries[i][1] == 3) {
                //res[i] = dist3.getOrDefault(queries[i][0], -1);
                res.add(dist3.getOrDefault(queries[i][0], -1));
            }
        }
        return res;
    }
}
