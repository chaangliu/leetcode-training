package dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 力扣春季赛第二题
 * 20200418
 */
public class NumOfWaystoDeliverMessages {
    /**
     * 题意：给你一些单向传递的二元组，从0开始传递，问你经过k次能传递到n-1的线路有几条。
     * 力扣春季赛第二题，tag是easy；写了个dfs就AC了。
     */
    public int numWays(int n, int[][] relation, int k) {
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int[] r : relation) {
            map.putIfAbsent(r[0], new ArrayList<>());
            map.get(r[0]).add(r[1]);
        }
        dfs(0, n, map, k);
        return res;
    }

    int res = 0;

    private void dfs(int u, int n, Map<Integer, ArrayList<Integer>> map, int k) {
        if (k < 0) return;
        if (u == n - 1 && k == 0) {
            res++;
            return;
        }
        if (map.containsKey(u))
            for (int v : map.get(u)) {
                dfs(v, n, map, k - 1);
            }
    }
}
