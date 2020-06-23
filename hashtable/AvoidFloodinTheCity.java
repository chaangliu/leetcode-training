package hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * Your country has an infinite number of lakes. Initially, all the lakes are empty, but when it rains over the nth lake, the nth lake becomes full of water. If it rains over a lake which is full of water, there will be a flood. Your goal is to avoid the flood in any lake.
 * <p>
 * Given an integer array rains where:
 * <p>
 * rains[i] > 0 means there will be rains over the rains[i] lake.
 * rains[i] == 0 means there are no rains this day and you can choose one lake this day and dry it.
 * Return an array ans where:
 * <p>
 * ans.length == rains.length
 * ans[i] == -1 if rains[i] > 0.
 * ans[i] is the lake you choose to dry in the ith day if rains[i] == 0.
 * If there are multiple valid answers return any of them. If it is impossible to avoid flood return an empty array.
 * Notice that if you chose to dry a full lake, it becomes empty, but if you chose to dry an empty lake, nothing changes. (see example 4)
 * Example 1:
 * Input: rains = [1,2,3,4]
 * Output: [-1,-1,-1,-1]
 * Explanation: After the first day full lakes are [1]
 * After the second day full lakes are [1,2]
 * After the third day full lakes are [1,2,3]
 * After the fourth day full lakes are [1,2,3,4]
 * There's no day to dry any lake and there is no flood in any lake.
 * Example 2:
 * Input: rains = [1,2,0,0,2,1]
 * Output: [-1,-1,2,1,-1,-1]
 * Explanation: After the first day full lakes are [1]
 * After the second day full lakes are [1,2]
 * After the third day, we dry lake 2. Full lakes are [1]
 * After the fourth day, we dry lake 1. There is no full lakes.
 * After the fifth day, full lakes are [2].
 * After the sixth day, full lakes are [1,2].
 * It is easy that this scenario is flood-free. [-1,-1,1,2,-1,-1] is another acceptable scenario.
 * 20200621
 */
public class AvoidFloodinTheCity {
    /**
     * 题意：给你一个arr，arr[i]代表第i天下雨的水池编号是arr[i]。如果arr[i]是0，代表不下雨，不下雨的日子可以挑一个水池排水。
     * 一旦再次下雨之前有水池没有排水，就代表无法处理了，返回空数组。请你返回一个处理雨水的方案。
     * 解法：这题我想着，要在遇到0的时候，去寻找下一个下雨的水池。这么做先不论是不是有问题，首先它是O(n^2)的，所以10^5的arr一定会超时。
     * 我看了一种解法，时遇到0的时候只记录0的index，不去处理；等遇到已满的水池需要排水的时候，就搜索当前水池右边最近的一个0day的index是几，
     * 然后征用这个日子（技巧：用treeSet.ceiling()可以找第一个>=当前数字的数字，时间是O(logn)）。
     * 总体时间O(n log n)。
     * 我看用c++的有用low_bound去找的，思路类似。
     */
    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] res = new int[n];
        TreeSet<Integer> zeros = new TreeSet<>();
        HashMap<Integer, Integer> map = new HashMap<>(); // 记录下雨的水池->下雨的日期
        for (int i = 0; i < n; i++) {
            if (rains[i] == 0) {
                zeros.add(i);
            } else {
                res[i] = -1;
                int pool = rains[i];
                if (map.containsKey(pool)) {
                    Integer zeroIdx = zeros.ceiling(map.get(pool));
                    if (zeroIdx != null) {
                        res[zeroIdx] = pool;
                        zeros.remove(zeroIdx);
                        map.put(pool, i);// 这里不能remove，因为虽然把前一个编号为pool的水池放干了，但是现在又下满了，所以要更新当前满了的Pool的index。
                    } else {
                        return new int[0];
                    }
                } else {
                    map.put(pool, i);
                }
            }
        }
        for (int i : zeros) res[i] = 1;
        return res;
    }

    /**
     * 二分做法：
     * 用List记录不下雨的日子的index，用Map记录下雨的水池的index；然后每当遇到一个已经满了的水池，就从它上一个位置的右边搜索一个不下雨的日子把它的水放掉。
     * 总体时间O(nlog(n))
     */
    public int[] avoidFlood_BinarySearch(int[] rains) {
        int n = rains.length;
        int[] res = new int[n];
        ArrayList<Integer> zeros = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>(); // 记录下雨的水池->下雨的日子
        for (int i = 0; i < n; i++) {
            if (rains[i] == 0) {
                zeros.add(i); // 记录不下雨的日子
            } else {
                res[i] = -1;
                int pool = rains[i];
                if (map.containsKey(pool)) {
                    // Integer zeroIdx = zeros.ceiling(map.get(pool));
                    int lastFull = map.get(pool);
                    int id = lower_bound(zeros, lastFull); // 前一个满了的pool右边的第一个0的index
                    int zeroIdx = id >= zeros.size() ? -1 : zeros.get(id);
                    if (zeroIdx > lastFull) {
                        res[zeroIdx] = pool;
                        zeros.remove(Integer.valueOf(zeroIdx)); // 这里要用Integer装箱；如果不装箱，会outOfBound，因为remove默认的参数代表index
                        map.put(pool, i);// 这里要更新当前满了的Pool的index，不能remove，因为虽然把前一个编号为pool的水池放干了，但是现在又下满了
                    } else {
                        return new int[0];
                    }
                } else {
                    map.put(pool, i);
                }
            }
        }
        for (int i : zeros) res[i] = 1; // 根据题意，把所有没有用到的0置为任意正数
        return res;
    }

    private int lower_bound(ArrayList<Integer> A, int target) {
        int lo = 0, hi = A.size();
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (A.get(mid) >= target) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }
}