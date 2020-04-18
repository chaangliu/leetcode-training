package binarysearch;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class StroyPlotTriggerTime {
    /**
     * 题意：一个游戏场景，给你一个increase三元数组代表index时间点资源增加的情况；requirements三元数组代表每个剧情触发的资源条件。
     * 力扣春季赛第三题；
     * 问各个剧情在什么时间触发。如果永远无法触发，填上-1.
     * 一开始看数据量，感觉O(mn)的话是10^10，有可能在TLE的边缘，就侥幸写了下，结果发现果然TLE。。发现写TLE的解法还是很浪费时间的
     * 然后优化了下，用binary search，对于每个剧情，找最早三个资源都满足的时间点，这个很像之前做过的一些题，记得lee解答过。
     * Time: O(n * log (m))
     */
    public int[] getTriggerTime(int[][] increase, int[][] requirements) {
        int[][] prefix = new int[increase.length + 1][3];
        for (int i = 1; i < prefix.length; i++) {
            prefix[i][0] = increase[i - 1][0] + prefix[i - 1][0];
            prefix[i][1] = increase[i - 1][1] + prefix[i - 1][1];
            prefix[i][2] = increase[i - 1][2] + prefix[i - 1][2];
        }
        int[] res = new int[requirements.length];
        for (int i = 0; i < requirements.length; i++) {
            int[] r = requirements[i];
            int lo = 0, hi = prefix.length;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (prefix[mid][0] >= r[0] && prefix[mid][1] >= r[1] && prefix[mid][2] >= r[2]) hi = mid;
                else lo = mid + 1;
            }
            res[i] = lo == prefix.length ? -1 : lo;
        }
        return res;
    }

    /**
     * TLE brute force
     * Time: O(m * n)
     */
    public int[] getTriggerTime_TLE(int[][] increase, int[][] requirements) {
        HashMap<int[], Integer> map = new HashMap<>();
        int[] cnt = {0, 0, 0}, res = new int[requirements.length];
        Arrays.fill(res, -1);
        for (int i = 0; i < requirements.length; i++) {
            if (requirements[i][0] == 0 && requirements[i][1] == 0 && requirements[i][2] == 0) res[i] = 0;
            map.put(requirements[i], i);
        }
        for (int i = 0; i < increase.length; i++) {
            int[] arr = increase[i];
            cnt[0] += arr[0];
            cnt[1] += arr[1];
            cnt[2] += arr[2];
            Iterator<int[]> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                int[] s = iterator.next();
                if (cnt[0] >= s[0] && cnt[1] >= s[1] && cnt[2] >= s[2]) {
                    res[map.get(s)] = i + 1;
                    iterator.remove();
                }
            }
        }
        return res;
    }
}
