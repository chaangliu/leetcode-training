package array;

import java.util.HashMap;
import java.util.HashSet;

public class CountUnhappyFriends {
    int cnt = 0;
    HashMap<Integer, Integer> map = new HashMap<>();
    HashSet<Integer> set = new HashSet<>();

    public int unhappyFriends(int n, int[][] pref, int[][] pairs) {
        for (int[] p : pairs) {
            map.put(p[0], p[1]);
            map.put(p[1], p[0]);
        }
        for (int[] p : pairs) {
            // check if: x 与 u 的亲近程度胜过 x 与 y
            int x = p[0], y = p[1];
            check(x, y, pref);
            check(y, x, pref);
        }
        return set.size();
    }

    private void check(int x, int y, int[][] pref) {
        for (int u : pref[x]) {
            if (u == map.get(x)) break;
            // check if: u 与 x 的亲近程度胜过 u 与 v
            for (int v : pref[u]) {
                if (v == map.get(u)) break;
                if (x == v) {
                    //System.out.println(x + " and= "  + u + " has closer relationship than"  + y);
                    //System.out.println(u + " and- "  + x + " has closer relationship than"  + v);
                    set.add(x);
                }
            }
        }
    }
}
